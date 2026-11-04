package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Level.PlayerState;
import Utils.Point;
import Utils.AirGroundState;
import Utils.Direction;

import java.awt.Color;
import java.util.HashMap;

// This class is for the lava slime enemy
public class LavaSlime extends Enemy {
    private float gravity = 0.25f;
    private float momentumY = 0;
    private float momentumYIncrease = 0.2f;
    private float terminalVelocityY = 2f;
    private float jumpHeight = 7.3f;
    private float jumpDegrade = 0.3f;
    private float jumpForce = 0;

    private int jumpTimer = 0;
    private AirGroundState airGroundState;

    private float lastMoveY;

    public LavaSlime(Point location) {
        super(
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("LavaSlime.png"), 32, 33),
            "Jumping"
        );

        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        momentumY = 0;
        jumpForce = 0;
        jumpTimer = 0;
        this.airGroundState = AirGroundState.AIR;
        currentAnimationName = "Grounded";
    }

    @Override
    public void update(Player player) {
        float moveAmountY = 0;
        if (airGroundState == AirGroundState.GROUND) {
            jumpTimer++;
            if (jumpTimer > 60) {
                jumpForce = jumpHeight;
                jumpTimer = 0;
                airGroundState = AirGroundState.AIR;
            }
        }

        if (jumpForce > 0) {
            moveAmountY -= jumpForce;
            jumpForce -= jumpDegrade;

            if (jumpForce < 0) {
                jumpForce = 0;
            }
        }
    
        moveAmountY += gravity + momentumY;

        momentumY += momentumYIncrease;

        if (momentumY > terminalVelocityY) {
            momentumY = terminalVelocityY;
        }

        lastMoveY = moveAmountY;
        moveYHandleCollision(moveAmountY);

        currentAnimationName = (airGroundState == AirGroundState.AIR ? "Jumping" : "Grounded");

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
            } else if (lastMoveY > 1) {
                airGroundState = AirGroundState.AIR;
            }
        }

        else if (direction == Direction.UP) {
            if (hasCollided) {
                jumpForce = 0;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("Jumping", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,3), 1000)
                    .withScale(2)
                    .withBounds(0,0,32,33)
                    .build(),
            });
            put ("Grounded", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0), 25)
                    .withScale(2)
                    .withBounds(0,0,32,33)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0,1), 20)
                    .withScale(2)
                    .withBounds(0,0,32,33)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0,2), 15)
                    .withScale(2)
                    .withBounds(0,0,32,33)
                    .build(),
            });
        }};
    }

    // spike deal 5 damage to the player
    @Override
    public int getDamage() {
        return 5;
    }
}
