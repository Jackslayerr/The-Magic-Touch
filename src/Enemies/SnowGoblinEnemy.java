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

public class SnowGoblinEnemy extends Enemy {
    private float gravity = 4.0f; // he's very heavy
    private float movementSpeed = 0.5f; // he's very cold
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public SnowGoblinEnemy(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("SnowGoblinEnemy.png"), 24, 24), "WALK_LEFT");
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
    }

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

        moveAmountY += gravity;

        if (airGroundState == AirGroundState.GROUND) {
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
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
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
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                        .withScale(3)
                        .withBounds(6, 14, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                        .withScale(3)
                        .withBounds(6, 13, 12, 7)
                        .build()
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                        .withScale(3)
                        .withBounds(6, 14, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                        .withScale(3)
                        .withBounds(6, 13, 12, 7)
                        .build()
            });
        }};
    }
}
