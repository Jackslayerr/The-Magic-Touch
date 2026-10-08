package Maps;

import Enemies.GoblinEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Torch;
import NPCs.Sign;
import Tilesets.CastleTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class MovementTestMap extends Map {

    public MovementTestMap() {
        super("movement_test_map.txt", new CastleTileset());
        this.playerStartPosition = getMapTile(12, 12).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // BugEnemy bugEnemy = new BugEnemy(getMapTile(22, 11).getLocation().subtractY(25), Direction.LEFT);
        // enemies.add(bugEnemy);

        // DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        // enemies.add(dinosaurEnemy);

        return enemies;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Torch torch = new Torch(getMapTile(4, 11).getLocation().subtractY(13));
        // npcs.add(torch);

        return npcs;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("CastleMovingPlatform.png"),
                getMapTile(8, 8).getLocation(),
                getMapTile(14, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,48,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);

        return enhancedMapTiles;
    }
}
