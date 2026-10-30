package Maps;

import Level.*;
import NPCs.*;
import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.BouncyCloud;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.VerticalMovingPlatform;
import EnhancedMapTiles.zapOrb;
import GameObject.Rectangle;
import Tilesets.LightningTileset;
import Utils.CheckpointManager;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelThreeMap extends Map {

    public LevelThreeMap() {
        super("level_three_map.txt", new LightningTileset());
        this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        /* SHEEP sheep = new SHEEP(getMapTile(17, 7).getLocation().subtractY(-350), Direction.LEFT);
        enemies.add(sheep);*/

        // DragonEnemy dragonEnemy = new DragonEnemy(getMapTile(18, 7).getLocation().subtractY(-200), Direction.LEFT);
        // enemies.add(dragonEnemy);
        return enemies;

    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        zapOrb zapOrb = new zapOrb(getMapTile(15, 14).getLocation(), getMapTile(17, 12).getLocation(), this);
        enhancedMapTiles.add(zapOrb);

        BouncyCloud bouncyCloud = new BouncyCloud(getMapTile(20, 16).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud);
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Teleporter teleporter1 = new Teleporter(getMapTile(32, 16).getLocation().subtractY(48), "GoldTeleporter.png",69, 16);
        npcs.add(teleporter1);    

        Teleporter teleporter2 = new Teleporter(getMapTile(69, 16).getLocation().subtractY(48), "DiamondTeleporter.png",32, 16);
        npcs.add(teleporter2);

        return npcs;
    }
}
