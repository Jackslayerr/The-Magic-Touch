package Maps;

import Level.*;
import NPCs.*;
import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.BouncyCloud;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.SnowballLauncher;
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


        LavaSlime lavaSlime = new LavaSlime(getMapTile(8, 196).getLocation().subtractY(0));
        enemies.add(lavaSlime);

        badOrb orb = new badOrb(getMapTile(22, 193).getLocation(), 3.0f, 3.0f);
        enemies.add(orb);

        return enemies;

    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        zapOrb zapOrb = new zapOrb(getMapTile(30, 175).getLocation(), getMapTile(40, 172).getLocation(), this);
        enhancedMapTiles.add(zapOrb);
        zapOrb zapOrb2 = new zapOrb(getMapTile(33, 175).getLocation(), getMapTile(28, 172).getLocation(), this);
        enhancedMapTiles.add(zapOrb2);
        zapOrb zapOrb3 = new zapOrb(getMapTile(36, 175).getLocation(), getMapTile(37, 172).getLocation(), this);
        enhancedMapTiles.add(zapOrb3);
        zapOrb zapOrb4 = new zapOrb(getMapTile(39, 175).getLocation(), getMapTile(31, 169).getLocation(), this);
        enhancedMapTiles.add(zapOrb4);
        zapOrb zapOrb5 = new zapOrb(getMapTile(28, 172).getLocation(), getMapTile(33, 175).getLocation(), this);
        enhancedMapTiles.add(zapOrb5);
        zapOrb zapOrb6 = new zapOrb(getMapTile(29, 169).getLocation(), getMapTile(31, 169).getLocation(), this);
        enhancedMapTiles.add(zapOrb6);
        zapOrb zapOrb7 = new zapOrb(getMapTile(31, 169).getLocation(), getMapTile(29, 163).getLocation(), this);
        enhancedMapTiles.add(zapOrb7);
        zapOrb zapOrb8 = new zapOrb(getMapTile(35, 169).getLocation(), getMapTile(30, 175).getLocation(), this);
        enhancedMapTiles.add(zapOrb8);
        zapOrb zapOrb9 = new zapOrb(getMapTile(37, 172).getLocation(), getMapTile(36, 175).getLocation(), this);
        enhancedMapTiles.add(zapOrb9);
        zapOrb zapOrb10 = new zapOrb(getMapTile(40, 172).getLocation(), getMapTile(33, 175).getLocation(), this);
        enhancedMapTiles.add(zapOrb10);
        zapOrb zapOrb11 = new zapOrb(getMapTile(40, 169).getLocation(), getMapTile(29, 163).getLocation(), this);
        enhancedMapTiles.add(zapOrb11);
        zapOrb zapOrb12 = new zapOrb(getMapTile(29, 163).getLocation(), getMapTile(33, 161).getLocation(), this);
        enhancedMapTiles.add(zapOrb12);
        zapOrb zapOrb13 = new zapOrb(getMapTile(40, 163).getLocation(), getMapTile(36, 175).getLocation(), this);
        enhancedMapTiles.add(zapOrb13);







        BouncyCloud bouncyCloud = new BouncyCloud(getMapTile(20, 16).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud);

        SnowballLauncher snowballLauncher = new SnowballLauncher(getMapTile(85, 12).getLocation().subtractY(15), false);
        enhancedMapTiles.add(snowballLauncher);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Teleporter teleporter1 = new Teleporter(getMapTile(32, 196).getLocation().subtractY(48), "GoldTeleporter.png",29, 175);
        npcs.add(teleporter1);    

        Teleporter teleporter2 = new Teleporter(getMapTile(69, 196).getLocation().subtractY(48), "DiamondTeleporter.png",32, 16);
        npcs.add(teleporter2);

        return npcs;
    }
}
