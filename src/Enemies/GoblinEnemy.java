package Enemies;

import Builders.FrameBuilder;
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

import java.util.HashMap;

public class GoblinEnemy extends Enemy {
    private float gravity = 2.0f;
    private float movementSpeed = 1.5f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public GoblinEnemy(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("GoblinEnemy.png"), 24, 24), "WALK_LEFT");
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

    private int flipCoolDown = 0; // prevents a lot of back and forth flipping

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;

        if (flipCoolDown > 0) {
            flipCoolDown--;
        }

        // if on ground, walk forward based on facing direction
        if (airGroundState == AirGroundState.GROUND) {
            // Checks for a ledge
            if (flipCoolDown == 0 && isAboutToFall()) {
                // Switch direction if a ledge is found
                if (facingDirection == Direction.RIGHT) {
                    facingDirection = Direction.LEFT;
                    currentAnimationName = "WALK_LEFT";
                } else {
                    facingDirection = Direction.RIGHT;
                    currentAnimationName = "WALK_RIGHT";
                }
                flipCoolDown = 15; // wait 15 frames
            }
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        // move bug
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
    }

    private boolean isAboutToFall() {
        float xCheck = facingDirection == Direction.RIGHT
                ? getBounds().getX2() + 6 // just past right foot
                : getBounds().getX1() - 6; // just past left foot
        float yCheck = getBounds().getY2() + 8; // just below the bottom bound/feet

        // Check if there is a tile at that position
        return map.getTileByPosition(xCheck, yCheck) == null ||
                map.getTileByPosition(xCheck, yCheck).getTileType() != TileType.NOT_PASSABLE;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if bug has collided into something while walking forward,
        // it turns around (changes facing direction)
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
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 14, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 13, 12, 7)
                            .build()
            });
        }};
    }
}
