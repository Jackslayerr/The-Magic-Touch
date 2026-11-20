
package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;
import Enemies.Mawtrap;

import java.util.HashMap;

public class MawtrapSpawner extends EnhancedMapTile {

    private int timer = 0;
    private boolean spawned = false;

    private final boolean facesRight;
    private final int activationDelay;

    private static final int SPAWN_DELAY = 30;

    public MawtrapSpawner(
        Point location,
        boolean facesRight,
        int activationDelay
    ) {
        super(
            Math.round(location.x),
            Math.round(location.y),
            new SpriteSheet(
                ImageLoader.load("MawtrapSpawner.png"),
                16,
                16
            ),
            TileType.NOT_PASSABLE
        );

        this.facesRight = facesRight;
        this.activationDelay = activationDelay;

        updateCurrentFrame();
    }

    // Keeps compatibility with existing two-argument spawners
    public MawtrapSpawner(Point location, boolean facesRight) {
        this(location, facesRight, 0);
    }

    @Override
    public void update(Player player) {
        super.update(player);

        if (!spawned) {
            timer++;

            if (timer >= activationDelay + SPAWN_DELAY) {
                spawnMawtrap();
                spawned = true;
            }
        }
    }

    private void spawnMawtrap() {

        int mawtrapX = Math.round(getX());
        int mawtrapY = Math.round(getY()) - 192;

        Mawtrap mawtrap = new Mawtrap(
            new Point(mawtrapX, mawtrapY),
            facesRight
        );

        map.addEnemy(mawtrap);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{

            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build()
            });
        }};
    }
}
