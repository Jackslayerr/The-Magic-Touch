
package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

public class FallingSpore extends Enemy {

    private static final String IMAGE_NAME = "spore.png";

    private static final int FRAME_WIDTH = 8;
    private static final int FRAME_HEIGHT = 16;

    private static final float SCALE = 2.0f;

    private static final float GRAVITY = 0.25f;
    private static final float TERMINAL_VELOCITY = 5.0f;

    private static final int MAX_LIFETIME = 360;

    // Main movement
    private float horizontalSpeed;
    private float fallSpeed;

    // Fly-like movement
    private float wigglePhase;
    private float wiggleAmplitude = 1.9f;
    private float wiggleFrequency = 0.9f;

    private int age = 0;
    private int lifetimeFrames = MAX_LIFETIME;

    public FallingSpore(Point location, float horizontalSpeed, float upwardSpeed) {

        super(
                location.x,
                location.y,
                new SpriteSheet(
                        ImageLoader.load(IMAGE_NAME),
                        FRAME_WIDTH,
                        FRAME_HEIGHT
                ),
                "BLINK"
        );

        this.horizontalSpeed = horizontalSpeed;
        this.fallSpeed = upwardSpeed;

        // Give each spore a slightly different flight pattern.
        this.wigglePhase = (float) (Math.random() * Math.PI * 2);

        initialize();
    }

    @Override
    public void update(Player player) {

        if (mapEntityStatus == MapEntityStatus.REMOVED) {
            return;
        }

        age++;

        // Gravity gradually pulls the spore back down.
        fallSpeed += GRAVITY;

        if (fallSpeed > TERMINAL_VELOCITY) {
            fallSpeed = TERMINAL_VELOCITY;
        }

        // Side-to-side fly-like movement.
        float horizontalWiggle =
                (float) Math.sin(age * wiggleFrequency + wigglePhase)
                        * wiggleAmplitude;

        // Small vertical flutter.
        float verticalWiggle =
                (float) Math.sin(age * 0.22f + wigglePhase)
                        * 0.70f;

        // Combine the original launch direction with the wiggling.
        float actualHorizontalSpeed =
                horizontalSpeed + horizontalWiggle;

        float actualVerticalSpeed =
                fallSpeed + verticalWiggle;

        moveXHandleCollision(actualHorizontalSpeed);

        if (mapEntityStatus == MapEntityStatus.REMOVED) {
            return;
        }

        moveYHandleCollision(actualVerticalSpeed);

        if (mapEntityStatus == MapEntityStatus.REMOVED) {
            return;
        }

        // Remove after its lifetime expires.
        lifetimeFrames--;

        if (lifetimeFrames <= 0) {
            mapEntityStatus = MapEntityStatus.REMOVED;
            return;
        }

        // Check collision with the player.
        super.update(player);
    }

    @Override
    public void onEndCollisionCheckY(
            boolean hasCollided,
            Direction direction,
            MapEntity entityCollidedWith
    ) {

        // Remove the spore when it hits the ground.
        if (hasCollided && direction == Direction.DOWN) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void onEndCollisionCheckX(
            boolean hasCollided,
            Direction direction,
            MapEntity entityCollidedWith
    ) {

        // Remove the spore when it hits a wall.
        if (hasCollided) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void touchedPlayer(Player player) {

        // Deal damage using the existing player health system.
        super.touchedPlayer(player);

        // Remove the spore after contact.
        mapEntityStatus = MapEntityStatus.REMOVED;
    }

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{

            put("BLINK", new Frame[] {

                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(SCALE)
                            .withBounds(0, 0, 8, 16)
                            .build(),

                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(SCALE)
                            .withBounds(0, 0, 8, 16)
                            .build()

            });

        }};
    }
}
