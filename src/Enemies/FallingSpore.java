
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

    // Your spore spritesheet is 17 x 16:
    // Two 8 x 16 frames with a 1-pixel gap
    private static final int FRAME_WIDTH = 8;
    private static final int FRAME_HEIGHT = 16;
    private static final float SCALE = 2.0f;

    private static final float GRAVITY = 0.25f;
    private static final float TERMINAL_VELOCITY = 7.0f;
    private static final int MAX_LIFETIME = 360;

    private float horizontalSpeed;
    private float fallSpeed;

    private int lifetimeFrames = MAX_LIFETIME;

    public FallingSpore(Point location, float horizontalSpeed, float upwardSpeed) {

        super(location.x, location.y,
                new SpriteSheet(
                        ImageLoader.load(IMAGE_NAME),
                        FRAME_WIDTH,
                        FRAME_HEIGHT
                ),
                "BLINK");

        this.horizontalSpeed = horizontalSpeed;
        this.fallSpeed = upwardSpeed;

        initialize();
    }

    @Override
    public void update(Player player) {

        if (mapEntityStatus == MapEntityStatus.REMOVED) {
            return;
        }

        // Apply gravity
        fallSpeed += GRAVITY;

        if (fallSpeed > TERMINAL_VELOCITY) {
            fallSpeed = TERMINAL_VELOCITY;
        }

        // Spread horizontally
        moveXHandleCollision(horizontalSpeed);

        // Move upward first, then fall back down
        moveYHandleCollision(fallSpeed);

        // Remove spores that have existed too long
        lifetimeFrames--;

        if (lifetimeFrames <= 0) {
            mapEntityStatus = MapEntityStatus.REMOVED;
            return;
        }

        // Check player collision
        super.update(player);
    }

    @Override
    public void onEndCollisionCheckY(
            boolean hasCollided,
            Direction direction,
            MapEntity entityCollidedWith) {

        // Remove spore when it hits the ground
        if (hasCollided && direction == Direction.DOWN) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void onEndCollisionCheckX(
            boolean hasCollided,
            Direction direction,
            MapEntity entityCollidedWith) {

        // Remove spore if it hits a wall
        if (hasCollided) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void touchedPlayer(Player player) {

        // Damage player and remove the spore
        super.touchedPlayer(player);
        mapEntityStatus = MapEntityStatus.REMOVED;
    }

    @Override
    public int getDamage() {
        return 12;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{

            put("BLINK", new Frame[] {

                    // First blinking frame
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(SCALE)
                            .withBounds(2, 2, 4, 12)
                            .build(),

                    // Second blinking frame
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(SCALE)
                            .withBounds(2, 2, 4, 12)
                            .build()

            });

        }};
    }
}
