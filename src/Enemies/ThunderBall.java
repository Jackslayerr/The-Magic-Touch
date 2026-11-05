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

// Ball dropped by the ThunderBirdEnemy.
// It falls straight down (with gravity) and is removed when it
// hits the player or lands on a solid ground tile.
public class ThunderBall extends Enemy {

    // sprite settings -- CHANGE THESE to match your ball png
    public static final String IMAGE_NAME = "birdBall.png";
    public static final int FRAME_SIZE = 32;      // width and height of the png in pixels
    public static final float BALL_SCALE = 1.5f;  // must be a constant (loadAnimations runs inside super())

    // fall settings
    private static final float GRAVITY = 0.25f;
    private static final float TERMINAL_VELOCITY = 8f;
    private static final int MAX_LIFETIME_FRAMES = 600; // safety net so a ball can never live forever

    private float fallSpeed = 1f;
    private int lifetimeFrames = MAX_LIFETIME_FRAMES;

    public ThunderBall(Point location) {
        super(location.x, location.y,
                new SpriteSheet(ImageLoader.load(IMAGE_NAME), FRAME_SIZE, FRAME_SIZE), "FALL");
        initialize();
    }

    @Override
    public void update(Player player) {
        // speed up while falling, up to a max speed
        fallSpeed += GRAVITY;
        if (fallSpeed > TERMINAL_VELOCITY) {
            fallSpeed = TERMINAL_VELOCITY;
        }

        // moves down and checks solid tiles; onEndCollisionCheckY handles landing
        moveYHandleCollision(fallSpeed);

        lifetimeFrames--;
        if (lifetimeFrames <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }

        // super.update checks if the ball touches the player (calls touchedPlayer)
        super.update(player);
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // ball landed on a solid tile -> disappear
        if (hasCollided && direction == Direction.DOWN) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // ball never moves sideways
    }

    @Override
    public void touchedPlayer(Player player) {
        // damages the player (via Enemy.touchedPlayer -> hurtPlayer), then disappears
        super.touchedPlayer(player);
        this.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    // damage the ball deals to the player
    @Override
    public int getDamage() {
        return 20;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FALL", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(BALL_SCALE)
                            .withBounds(4, 4, 24, 24)
                            .build()
            });
        }};
    }
}