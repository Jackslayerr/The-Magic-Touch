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

public class SHEEP extends Enemy {
    private final float gravity = 0.0f; // If it flies, it does not fall!
    private final float movementSpeed = 0.6f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public SHEEP(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("S.H.E.E.P.Enemy.png"), 33, 24), "FLY_LEFT");
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

        // no gravity if S.H.E.E.P. is in le sky

        if (airGroundState == AirGroundState.AIR) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        // move SHEEP
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
    }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FLY_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 100)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 100)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 100)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 100)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 100)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 30)
                            .withScale(10)
                            .withBounds(2, 4, 20, 17)
                            .build()
            });

            put("FLY_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 30)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 30)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 4, 20, 17)
                            .build(),
            });
        }};
    }

    @Override
    public int getDamage() {
        return 50;
    }
}
