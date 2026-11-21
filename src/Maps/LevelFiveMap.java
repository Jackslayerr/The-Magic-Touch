package Maps;

import Level.*;
import NPCs.*;
import Enemies.*;
import EnhancedMapTiles.*;
import Tilesets.JungleTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;
import  Tilesets.BossTileset;

public class LevelFiveMap extends Map {

    public LevelFiveMap() {
        super("level_five_map.txt", new BossTileset());
        // this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
        this.playerStartPosition = getMapTile(1, 198).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

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
