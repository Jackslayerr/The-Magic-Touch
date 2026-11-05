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

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

// This class is for the thunder bird enemy
// It flies back and forth between startX and endX and drops lightning balls below itself
// It shows its attack pose for a short time each time it drops a ball
// It is not affected by gravity

public class ThunderBirdEnemy extends Enemy{
    private final float gravity = 0.0f; // If it flies, gravity doesn't matter
    private final float movementSpeed = 2.3f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    private float startX;
    private float endX;

    // frames until the bird drops its next ball
    private int dropTimer;

    // frames left to show the attack pose after dropping a ball
    private int attackTimer;
    private static final int ATTACK_FRAMES = 30; // 0.5 seconds at 60 fps


    public ThunderBirdEnemy(Point location, float startX, float endX, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("birdLightning.png"), 100, 100), "FLY_LEFT");
        this.startFacingDirection = facingDirection;
        this.startX = startX;
        this.endX = endX;
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
        dropTimer = getRandomAttackInterval();
        attackTimer = 0;
    }
/*
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler,new Color(255, 0, 0, 100));
    }
 */

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;


        if (airGroundState == AirGroundState.AIR) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        if (facingDirection == Direction.RIGHT && (this.x + moveAmountX >= endX)) {
            facingDirection = Direction.LEFT;
            currentAnimationName = "FLY_LEFT";
            moveAmountX = -movementSpeed;
        } else if (facingDirection == Direction.LEFT && (this.x + moveAmountX <= startX)) {
            facingDirection = Direction.RIGHT;
            currentAnimationName = "FLY_RIGHT";
            moveAmountX = movementSpeed;
        }

        // move bird
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        // count down and drop a ball when the timer runs out
        dropTimer--;
        if (dropTimer <= 0) {
            dropBall();
            attackTimer = ATTACK_FRAMES;
            dropTimer = getRandomAttackInterval();
        }

        if (attackTimer > 0) {
            attackTimer--;
        }

        // attack pose while the timer runs, otherwise the normal flying animation
        if (attackTimer > 0) {
            currentAnimationName = (facingDirection == Direction.RIGHT) ? "ATTACK_RIGHT" : "ATTACK_LEFT";
        } else {
            currentAnimationName = (facingDirection == Direction.RIGHT) ? "FLY_RIGHT" : "FLY_LEFT";
        }

        super.update(player);
    }

    // spawns a ThunderBall centered under the bird
    private void dropBall() {
        float ballWidth = ThunderBall.FRAME_SIZE * ThunderBall.BALL_SCALE;
        float ballX = getBounds().getX1() + (getBounds().getWidth() / 2f) - (ballWidth / 2f);
        float ballY = getBounds().getY2();
        map.addEnemy(new ThunderBall(new Point(ballX, ballY)));
    }

    // frames between drops (2 to 4 seconds at 60 fps)
    private int getRandomAttackInterval() {
        return (int) (Math.random() * 120) + 120;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bird has collided into something while flying,
        // it flies through it

        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "FLY_RIGHT";
            } else {
                facingDirection = Direction.LEFT;
                currentAnimationName = "FLY_LEFT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bird collides with the ground, keep its air state to AIR
        if (direction == Direction.DOWN) {
            airGroundState = AirGroundState.AIR;
        }
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FLY_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2)
                            .withBounds(10, 30, 75, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(2)
                            .withBounds(10, 30, 75, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,2), 8)
                            .withScale(2)
                            .withBounds(10,30,75,50)
                            .build()
            });

            put("FLY_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 30, 75, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 30, 75, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,2),8)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10,30,75,50)
                            .build()
            });

            put("ATTACK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 30)
                            .withScale(2)
                            .withBounds(10, 30, 75, 50)
                            .build()
            });

            put("ATTACK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 30)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 30, 75, 50)
                            .build()
            });
        }};
    }

    // this enemy deal 30 damage to the player
    @Override
    public int getDamage() {
        return 30;
    }
}