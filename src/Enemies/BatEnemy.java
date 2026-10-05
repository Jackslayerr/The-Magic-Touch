package Enemies;

import Builders.FrameBuilder;
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

import java.util.HashMap;

// This class is for the bat enemy
// It behaves like a Boo from Mario -- slowly flies towards the player
// It is not affected by gravity

public class BatEnemy extends Enemy{
    private final float gravity = 0.0f; // If it flies, gravity doesn't matter
    private final float movementSpeed = 2.3f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public BatEnemy(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("BatEnemy.png"), 24, 16), "FLY_LEFT");
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
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

        // no gravity if bat is in the air

        if (airGroundState == AirGroundState.AIR) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        // move bat
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bat has collided into something while flying,
        // it flies through it

        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "FLY_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "FLY_RIGHT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bat collides with the ground, keep its air state to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.AIR;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FLY_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2)
                            .withBounds(6, 20, 12, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(2)
                            .withBounds(6, 20, 12, 9)
                            .build()
            });

            put("FLY_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 20, 12, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 20, 12, 9)
                            .build()
            });
        }};
    }

}
