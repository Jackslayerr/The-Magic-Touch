
package Maps;

import Level.*;
import NPCs.*;
import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.BouncyCloud;
import EnhancedMapTiles.ElectricZapSpawner;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.IceBlock;
import EnhancedMapTiles.SnowballLauncher;
import EnhancedMapTiles.VerticalMovingPlatform;
import EnhancedMapTiles.zapOrb;
import EnhancedMapTiles.SpeedRing;
import EnhancedMapTiles.Trigger;
import GameObject.Rectangle;
import EnhancedMapTiles.*;
import Tilesets.JungleTileset;
import Utils.CheckpointManager;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

public class LevelFourMap extends Map {

    public LevelFourMap() {
        super("level_four_map.txt", new JungleTileset());
        //this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
        this.playerStartPosition = getMapTile(1,96).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // Poison Spore Plant
        SporePlant sporePlant1 = new SporePlant(
                getMapTile(10, 95).getLocation()
        );

        enemies.add(sporePlant1);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

<<<<<<< HEAD
        VineSprout vineSprout1 = new VineSprout(getMapTile(3,96).getLocation().subtractY(16));
        enhancedMapTiles.add(vineSprout1);       
        
        BurnableVine burnableVine1 = new BurnableVine(getMapTile(5,96).getLocation().subtractY(143));
        enhancedMapTiles.add(burnableVine1);

        ElectricZapSpawner electricZapSpawner1 = new ElectricZapSpawner(getMapTile(7,94).getLocation().subtractY(16));
        enhancedMapTiles.add(electricZapSpawner1);
=======
        VineSprout vineSprout1 = new VineSprout(
                getMapTile(3,96).getLocation().subtractY(16)
        );

        enhancedMapTiles.add(vineSprout1);
>>>>>>> eb1289d (adding plant enemy)

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }
}
