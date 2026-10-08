package Maps;

import Enemies.GoblinEnemy;
import EnhancedMapTiles.EndLevelBox;
import Level.*;
import NPCs.Torch;
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

        GoblinEnemy goblinEnemy = new GoblinEnemy(getMapTile(22, 11).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(goblinEnemy);
        GoblinEnemy goblinEnemy2 = new GoblinEnemy(getMapTile(30, 11).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(goblinEnemy2);
        GoblinEnemy goblinEnemy3 = new GoblinEnemy(getMapTile(31, 11).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(goblinEnemy3);


        // DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        // enemies.add(dinosaurEnemy);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(33, 9).getLocation(), "WaterChest.png");
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Torch torch = new Torch(getMapTile(4, 11).getLocation().subtractY(13), 1);
        npcs.add(torch);



        return npcs;
    }
}
