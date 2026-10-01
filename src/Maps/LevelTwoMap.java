package Maps;

import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Walrus;
import Enemies.*;
import Tilesets.IceCaveTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelTwoMap extends Map {

    public LevelTwoMap() {
        super("level_two_map.txt", new IceCaveTileset());
        this.playerStartPosition = getMapTile(2, 16).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        Icicle Icicle1 = new Icicle(getMapTile(8, 15).getLocation().subtractY(0));
        enemies.add(Icicle1);;

        Icicle Icicle2 = new Icicle(getMapTile(9, 15).getLocation().subtractY(0));
        enemies.add(Icicle2);;

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
        // enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();



        return npcs;
    }
}
