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

public class PenguinEnemy extends Enemy {
    public static final int STATE_WALK = 0;
    public static final int STATE_SHOOT_WAIT = 1;
    public static final int STATE_SHOOT = 2;

    protected Point startLocation;
    protected Point endLocation;

    protected float movementSpeed = 1f;
    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    protected int shootWaitTimer;
    protected int shootTimer;

    protected int penguinState;
    protected int previousPenguinState;

    // Constructor 1: Takes start point, end point, and facing direction
    public PenguinEnemy(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("penguinEnemy.png"), 160, 220), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    // Constructor 2: Overloaded constructor that takes a single point (patrols 2 tiles left/right)
    public PenguinEnemy(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("penguinEnemy.png"), 160, 220), "WALK_RIGHT");
        this.startLocation = new Point(location.x - 64, location.y);
        this.endLocation = new Point(location.x + 64, location.y);
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        penguinState = STATE_WALK;
        previousPenguinState = penguinState;
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
        shootWaitTimer = 90;
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        if (shootWaitTimer == 0 && penguinState != STATE_SHOOT_WAIT) {
            penguinState = STATE_SHOOT_WAIT;
        } else {
            shootWaitTimer--;
        }
        moveYHandleCollision(3);
        if (penguinState == STATE_WALK) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // Clean boundary checks to prevent jitter oscillation
            if (getX2() >= endBound) {
                setX(endBound - getWidth());
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                setX(startBound);
                facingDirection = Direction.RIGHT;
            }
        }

        if (penguinState == STATE_SHOOT_WAIT) {
            if (previousPenguinState == STATE_WALK) {
                shootTimer = 45;
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                penguinState = STATE_SHOOT;
            } else {
                shootTimer--;
            }
        }

        if (penguinState == STATE_SHOOT) {
            int fishX;
            float fishSpeed;
            Direction fishDir;

            if (facingDirection == Direction.RIGHT) {
                fishX = Math.round(getX()) + getWidth();
                fishSpeed = 2.5f;
                fishDir = Direction.RIGHT;
            } else {
                fishX = Math.round(getX() - 35);
                fishSpeed = -2.5f;
                fishDir = Direction.LEFT;
            }

            int fishY = Math.round(getY() + 8);

            Fish fish = new Fish(new Point(fishX, fishY), fishSpeed, 300, false, fishDir);
            map.addEnemy(fish);

            penguinState = STATE_WALK;
            shootWaitTimer = 140;
        }

        super.update(player);
        previousPenguinState = penguinState;
    }
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler,new Color(255, 0, 0, 100));
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

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(0.30f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 210)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(0.30f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 210)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(0.30f)
                            .withBounds(15, 5, 130, 210)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(0.30f)
                            .withBounds(15, 5, 130, 210)
                            .build()
            });

            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 30)
                            .withScale(0.30f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 210)
                            .build(),
            });

            put("SHOOT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 30)
                            .withScale(0.30f)
                            .withBounds(15, 5, 130, 210)
                            .build(),
            });
        }};
    }}