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
import Level.TileType;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;


public class WheelEnemy extends Enemy {
    private float gravity = 0.5f;
    private float movementSpeed = 4.0f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public WheelEnemy(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("WheelEnemy.png"), 24, 20), "ROLL_LEFT");
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "ROLL_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "ROLL_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
    }

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

        moveAmountY += gravity;

        if (airGroundState == AirGroundState.GROUND) {
            if (facingDirection == Direction.LEFT) {
                currentAnimationName = "ROLL_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "ROLL_RIGHT";
            }

            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if bug has collided into something while walking forward,
        // it turns around (changes facing direction)
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "ROLL_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "ROLL_RIGHT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bug is colliding with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.GROUND;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("ROLL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 2)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 2)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 2)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 2)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build()
            });

            put("ROLL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 2)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 2)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 2)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 2)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 20, 17)
                            .build()

            });
        }};
    }

    @Override
    public int getDamage() {
        return 15;
    }
}
