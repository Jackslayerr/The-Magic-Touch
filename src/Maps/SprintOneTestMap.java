package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Torch;
import NPCs.Walrus;
import Tilesets.CastleTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class SprintOneTestMap extends Map {

    public SprintOneTestMap() {
        super("sprint_one_test_map.txt", new CastleTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy = new BugEnemy(getMapTile(19, 11).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy);

        DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy);

        return enemies;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Torch torch = new Torch(getMapTile(4, 11).getLocation().subtractY(13));
        npcs.add(torch);

        return npcs;
    }
}
