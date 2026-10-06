
package Level;

import Builders.FrameBuilder;
import Enemies.WaveAttack;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

import Enemies.Fireball;

import static javax.swing.UIManager.put;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float gravity = 0;
    protected float jumpHeight = 0;
    protected float jumpDegrade = 0;
    protected float terminalVelocityY = 0;
    protected float momentumYIncrease = 0;
    protected int fireAnimationTimer = 0;
    protected int waveAnimationTimer = 0;

    // values used to handle player movement
    protected float jumpForce = 0;
    protected float momentumY = 0;
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // Element Powers
    protected float fireCoolDown = 0;
    protected float waveCoolDown;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected AirGroundState previousAirGroundState;
    protected LevelState levelState;

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key JUMP_KEY = Key.UP;
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key CROUCH_KEY = Key.DOWN;
    protected Key FIRE_KEY = Key.Z;
    protected Key WAVE_KEY = Key.X;

    private boolean frozen = false;

    // flags
    protected boolean isInvincible = false;

    // =====================================
    // PLAYER HEALTH SYSTEM
    // =====================================

    protected int health = 12;
    protected final int maxHealth = 12;

    // Damage cooldown prevents rapid repeated damage
    protected int damageCooldown = 0;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        airGroundState = AirGroundState.AIR;
        previousAirGroundState = airGroundState;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        levelState = LevelState.RUNNING;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;

        // Update damage cooldown
        if (damageCooldown > 0) {
            damageCooldown--;
        }

        if (fireCoolDown != 0) {
            fireCoolDown--;
        }

        if (waveCoolDown != 0) {
            waveCoolDown--;
        }

        // if player is currently playing through level
        if (levelState == LevelState.RUNNING) {
            applyGravity();

            // update player's state and current actions
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            previousAirGroundState = airGroundState;

            // move player with respect to map collisions
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            // Check if player has fallen below the map
            if (levelState == LevelState.RUNNING && getY() > map.getHeightPixels()) {
                health = 0;
                levelState = LevelState.PLAYER_DEAD;
            }

            

            handlePlayerAnimation();

            updateLockedKeys();

            // update player's animation
            super.update();
        }

        // if player has beaten level
        else if (levelState == LevelState.LEVEL_COMPLETED) {
            updateLevelCompleted();
        }

        // if player has lost level
        else if (levelState == LevelState.PLAYER_DEAD) {
            updatePlayerDead();
        }
    }

    // add gravity to player
    protected void applyGravity() {
        moveAmountY += gravity + momentumY;
    }

    // based on player's current state, call appropriate method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                playerShootFire();
                playerShootWave();
                break;

            case WALKING:
                playerWalking();
                playerShootFire();
                playerShootWave();
                break;

            case CROUCHING:
                playerCrouching();
                playerShootFire();
                playerShootWave();
                break;

            case JUMPING:
                playerJumping();
                playerShootFire();
                playerShootWave();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {

        if (frozen) {
            return;
        }

        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.WALKING;
        }

        else if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {

        if (frozen) {
            return;
        }

        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
        }

        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
        }

        else if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.STANDING;
        }

        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }
    }

    // player CROUCHING state logic
    protected void playerCrouching() {
        if (frozen) {
            return;
        }
        if (Keyboard.isKeyUp(CROUCH_KEY)) {
            playerState = PlayerState.STANDING;
        }

        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }
    }

    // player JUMPING state logic
    protected void playerJumping() {

        if (frozen) {
            return;
        }

        if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND) {

            currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";

            airGroundState = AirGroundState.AIR;
            jumpForce = jumpHeight;

            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;

                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }
        }

        else if (airGroundState == AirGroundState.AIR) {
            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;

                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }

            // allows movement while in the air
            if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
                moveAmountX -= walkSpeed;
            }

            else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
                moveAmountX += walkSpeed;
            }

            if (moveAmountY > 0) {
                increaseMomentum();
            }
        }

        else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
            playerState = PlayerState.STANDING;
        }
    }

    // Shoots fire when FIRE_KEY is pressed and cooldown is up
    protected void playerShootFire() {
        if (frozen) {
            return;
        }
        if (Keyboard.isKeyDown(FIRE_KEY) && !keyLocker.isKeyLocked(FIRE_KEY) && fireCoolDown == 0) {
            keyLocker.lockKey(FIRE_KEY);
            shootFire();
            fireAnimationTimer = 15;
        }
    }

    // increases momentum while player is in air
    protected void increaseMomentum() {
        momentumY += momentumYIncrease;

        if (momentumY > terminalVelocityY) {
            momentumY = terminalVelocityY;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(JUMP_KEY)) {
            keyLocker.unlockKey(JUMP_KEY);
        }

        if (Keyboard.isKeyUp(FIRE_KEY)) {
            keyLocker.unlockKey(FIRE_KEY);
        }

        if (Keyboard.isKeyUp(WAVE_KEY)) {
            keyLocker.unlockKey(WAVE_KEY);
        }
    }

    protected void handlePlayerAnimation() {
        if (fireAnimationTimer > 0) {
            fireAnimationTimer--;
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "SWIM_STAND_RIGHT" : "SWIM_STAND_LEFT";
            return;
        }

        if (waveAnimationTimer > 0) {
            waveAnimationTimer--;
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WAVE_RIGHT" : "WAVE_LEFT";
            return;
        }

        if (playerState == PlayerState.STANDING) {
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";

            int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
            int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);

            MapTile currentMapTile = map.getTileByPosition(centerX, centerY);

            if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "SWIM_STAND_RIGHT" : "SWIM_STAND_LEFT";
            }
        }

        else if (playerState == PlayerState.WALKING) {
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        }

        else if (playerState == PlayerState.CROUCHING) {
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "CROUCH_RIGHT" : "CROUCH_LEFT";
        }

        else if (playerState == PlayerState.JUMPING) {
            if (lastAmountMovedY <= 0) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
            } else {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) { }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
            } else {
                playerState = PlayerState.JUMPING;
                airGroundState = AirGroundState.AIR;
            }
        }

        else if (direction == Direction.UP) {
            if (hasCollided) {
                jumpForce = 0;
            }
        }
    }

    // =====================================
    // PLAYER DAMAGE / HEALTH
    // =====================================

    public void hurtPlayer(MapEntity mapEntity) {

        if (isInvincible) {
            return;
        }

        if (levelState != LevelState.RUNNING) {
            return;
        }

        if (damageCooldown > 0) {
            return;
        }

        if (mapEntity instanceof Enemy) {

            health-= 4;

            System.out.println("Player Health: " + health);

            damageCooldown = 60;

            if (health <= 0) {
                health = 0;
                levelState = LevelState.PLAYER_DEAD;
            }
        }
    }
public void healPlayer() {
    if (health < maxHealth) {
        health += 1;
    }

    if (health > maxHealth) {
        health = maxHealth;
    }

    System.out.println("Player Health: " + health);
}
    // Get current player health
    public int getHealth() {
        return health;
    }

    // Get maximum player health
    public int getMaxHealth() {
        return maxHealth;
    }

    // other entities can call this to tell the player they beat a level
    public void completeLevel() {
        levelState = LevelState.LEVEL_COMPLETED;
    }

    // if player has beaten level
    public void updateLevelCompleted() {
        if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this)) {
            currentAnimationName = "FALL_RIGHT";
            applyGravity();
            increaseMomentum();
            super.update();
            moveYHandleCollision(moveAmountY);
        }

        else if (map.getCamera().containsDraw(this)) {
            currentAnimationName = "WALK_RIGHT";
            super.update();
            moveXHandleCollision(walkSpeed);
        }

        else {
            for (PlayerListener listener : listeners) {
                listener.onLevelCompleted();
            }
        }
    }

    // if player has lost level
    public void updatePlayerDead() {
        if (!currentAnimationName.startsWith("DEATH")) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "DEATH_RIGHT";
            } else {
                currentAnimationName = "DEATH_LEFT";
            }

            super.update();
        }

        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
            super.update();
        }

        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                }
            }
        }
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public AirGroundState getAirGroundState() {
        return airGroundState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public void setLevelState(LevelState levelState) {
        this.levelState = levelState;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }

    // Uncomment this to have game draw player's bounds
    /*
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(new Color(255, 0, 0, 100));
    }
    */

    // Shoots a fireball from the player
    public void shootFire() {
        int fireballX;
        float movementSpeed;

        if (facingDirection == Direction.RIGHT) {
            fireballX = Math.round(getX()) + getWidth();
            movementSpeed = 3.0f;
        } else {
            fireballX = Math.round(getX() - 21);
            movementSpeed = -3.0f;
        }

        int fireballY = Math.round(getY()) + 15;

        Fireball fireball = new Fireball(new Point(fireballX, fireballY), movementSpeed, 60, true);

        map.addEnemy(fireball);

        // Modify to change cooldown
        fireCoolDown = 20;
    }

    protected void playerShootWave() {
        if (frozen) {
            return;
        }
        if (Keyboard.isKeyDown(WAVE_KEY) && !keyLocker.isKeyLocked(WAVE_KEY) && waveCoolDown == 0) {
            keyLocker.lockKey(WAVE_KEY);
            shootWave();
            waveAnimationTimer = 15;
        }
    }

    public void shootWave() {
        int waveX;
        float movementSpeed;

        if (facingDirection == Direction.RIGHT) {
            waveX = Math.round(getX()) + getWidth();
            movementSpeed = 3.0f;
        } else {
            waveX = Math.round(getX() - 21);
            movementSpeed = -3.0f;
        }

        int waveY = Math.round(getY()) + 15;


        WaveAttack wave = new WaveAttack(new Point(waveX, waveY), movementSpeed, 60, true);

        map.addEnemy(wave);


        waveCoolDown = 20;
    }
}