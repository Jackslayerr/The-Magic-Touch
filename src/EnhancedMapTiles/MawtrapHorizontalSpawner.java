
package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;
import Enemies.MawtrapHorizontal;

import java.util.HashMap;

// Spawner for horizontal Mawtraps
public class MawtrapHorizontalSpawner extends EnhancedMapTile {

    private boolean spawned = false;

    private final boolean facesRight;

    public MawtrapHorizontalSpawner(Point location, boolean facesRight) {
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

        updateCurrentFrame();
    }

    @Override
    public void update(Player player) {
        super.update(player);

        if (!spawned) {
            spawnMawtrap();
            spawned = true;
        }
    }

    private void spawnMawtrap() {

        // Spawner is displayed at 3x scale: 48 x 48 pixels.
        // Mawtrap is displayed at 2x scale: 192 x 64 pixels.

        int mawtrapX;
        int mawtrapY = Math.round(getY()) - 8;

        if (facesRight) {
            // Extend outward from the right side of the spawner
            mawtrapX = Math.round(getX()) + 48;
        } else {
            // Extend outward from the left side of the spawner
            mawtrapX = Math.round(getX()) - 192;
        }

        MawtrapHorizontal mawtrap = new MawtrapHorizontal(
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
