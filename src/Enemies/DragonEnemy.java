package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

public class DragonEnemy extends Enemy {
    private float gravity = 0.0f; // I intend for her to move up and down
    private float movementSpeed = 0.8f; // She's quite big and slow
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;
    private float health = 30.0f; // takes 30 hits to slay the dragon

    private int attackTimer = 0;
    private final int ATTACK_COOLDOWN = 180;

    public DragonEnemy(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("DragonEnemy.png"), 52, 43), "FLY_LEFT");
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "FLY_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "FLY_LEFT";
        }
        airGroundState = AirGroundState.AIR;
    }

    @Override
    public void update (Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

        // HORIZONTAL MOVEMENT
        float playerCenterX = player.getX() + (player.getWidth() / 2f);
        float dragonCenterX = this.getX() + (this.getWidth() / 2f);

        if (playerCenterX > dragonCenterX + 10) {
            moveAmountX += movementSpeed;
            facingDirection = Direction.RIGHT;
            currentAnimationName = "FLY_RIGHT";
        } else if (playerCenterX < dragonCenterX - 10) {
            moveAmountX -= movementSpeed;
            facingDirection = Direction.LEFT;
            currentAnimationName = "FLY_LEFT";
        }

        // VERTICAL MOVEMENT
        float targetY = player.getY() - 120;

        if (this.getY() < targetY) {
            moveAmountY += movementSpeed; // Fly down towards height
        } else if (this.getY() > targetY) {
            moveAmountY -= movementSpeed; // fly up towards height
        }

        // no gravity if Dragon is in the air

        // move Dragon
        moveY(moveAmountY);
        moveX(moveAmountX);

        // ATTACK LOGIC
        attackTimer++;
        if (attackTimer >= ATTACK_COOLDOWN) {
            launchSheepAttack(player);
            attackTimer = 0; // Reset attack timer
        }

        super.update(player);
    }

    private void launchSheepAttack(Player player) {
        // Spawn point centered on dragon's current location
        Point spawnPoint = new Point(this.getX() + (this.getWidth() / 2f), this.getY());

        // Target where the player is currently standing
        float targetPlayerX = player.getX();

        // Instantiate sheep with upward trajectory targeting the player's X
        SHEEP2 sheep = new SHEEP2(spawnPoint, targetPlayerX);

        // Add sheep to active entities in the map
        if (this.map != null) {
            this.map.addEnemy(sheep);
            System.out.println("DRAGON LAUNCHED S.H.E.E.P. AT PLAYER!");
        }
    }

    public void takeDamage(float damage) {
        this.health -= damage;

        System.out.println("DRAGON HIT! HOLY S**T! HEALTH: " + this.health);

        // when health is zero, she dies :(
        if (this.health <= 0) {
            this.setMapEntityStatus(Level.MapEntityStatus.REMOVED);
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // leave empty
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (direction == Direction.DOWN) {
            airGroundState = AirGroundState.AIR;
        }
    }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>(){{
            put("FLY_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 100)
                            .withScale(5)
                            .withBounds(2, 2, 48, 39)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 100)
                            .withScale(5)
                            .withBounds(2, 2, 48, 39)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 100)
                            .withScale(5)
                            .withBounds(2, 2, 48, 39)
                            .build()
            });

            put("FLY_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 100)
                            .withScale(5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 48, 39)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 100)
                            .withScale(5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 48, 39)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 100)
                            .withScale(5)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 48, 39)
                            .build()
            });
        }};
    }
}
