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

public class StoneGolem extends Enemy {
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

    public static final int STATE_THROW = 3;
    protected int throwTimer;


    public StoneGolem(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("stoneGolem1.png"), 170, 114), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }


    public StoneGolem(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("stoneGolem1.png"), 170, 114), "WALK_RIGHT");
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
                currentAnimationName = facingDirection == Direction.RIGHT ? "WINDUP_RIGHT" : "WINDUP_LEFT";
            } else if (shootTimer == 0) {
                penguinState = STATE_SHOOT;
            } else {
                shootTimer--;
            }
        }

        if (penguinState == STATE_SHOOT) {
            int boulderX;
            float boulderSpeed;
            Direction boulderDir;

            if (facingDirection == Direction.RIGHT) {
                boulderX = Math.round(getX()) + getWidth();
                boulderSpeed = 2.5f;
                boulderDir = Direction.RIGHT;
            } else {
                boulderX = Math.round(getX() - 35);
                boulderSpeed = -2.5f;
                boulderDir = Direction.LEFT;
            }

            int boulderY = Math.round(getY() + 8);

            GolemBoulder boulder = new GolemBoulder(new Point(boulderX, boulderY), boulderSpeed, 300, false, boulderDir);
            map.addEnemy(boulder);

            penguinState = STATE_THROW;
            throwTimer = 20;
            currentAnimationName = facingDirection == Direction.RIGHT ? "THROW_RIGHT" : "THROW_LEFT";
        }

        if (penguinState == STATE_THROW) {
            if (throwTimer == 0) {
                penguinState = STATE_WALK;
                shootWaitTimer = 140;
            } else {
                throwTimer--;
            }
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
                            .withScale(0.50f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 114)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(0.50f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 114)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                            .withScale(0.50f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 114)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                            .withScale(0.50f)
                            .withBounds(15, 5, 130, 114)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                            .withScale(0.50f)
                            .withBounds(15, 5, 130, 114)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                            .withScale(0.50f)
                            .withBounds(15, 5, 130, 114)
                            .build()
            });

            put("WINDUP_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 30)
                            .withScale(0.50f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 114)
                            .build()
            });

            put("WINDUP_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 30)
                            .withScale(0.50f)
                            .withBounds(15, 5, 130, 114)
                            .build()
            });

            put("THROW_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 4), 30)
                            .withScale(0.50f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 130, 114)
                            .build()
            });

            put("THROW_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 4), 30)
                            .withScale(0.50f)
                            .withBounds(15, 5, 130, 114)
                            .build()
            });
        }};
    }}