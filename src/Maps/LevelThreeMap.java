package Maps;

import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Tilesets.LightningTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelThreeMap extends Map {

    public LevelThreeMap() {
        super("level_three_map.txt", new LightningTileset());
        this.playerStartPosition = getMapTile(1, 16).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        SHEEP sheep = new SHEEP(getMapTile(17, 7).getLocation().subtractY(-200), Direction.LEFT);
        enemies.add(sheep);
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();



        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }
}
