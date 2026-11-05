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
import EnhancedMapTiles.SpeedRing;
import EnhancedMapTiles.Trigger;
import GameObject.Rectangle;
import Tilesets.LightningTileset;
import Utils.CheckpointManager;
import Utils.Direction;
import Utils.Point;

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
        GoblinEnemy goblin1  = new GoblinEnemy(getMapTile(42, 191).getLocation(), Direction.RIGHT);
        GoblinEnemy goblin2  = new GoblinEnemy(getMapTile(43, 193).getLocation(), Direction.RIGHT);
        enemies.add(goblin2);
        enemies.add(goblin1);
        GoblinEnemy goblin3  = new GoblinEnemy(getMapTile(92, 192).getLocation(), Direction.RIGHT);
        GoblinEnemy goblin4  = new GoblinEnemy(getMapTile(88, 188).getLocation(), Direction.RIGHT);
        enemies.add(goblin3);
        enemies.add(goblin4);


        // LavaSlime lavaSlime = new LavaSlime(getMapTile(8, 196).getLocation().subtractY(0));
        // enemies.add(lavaSlime);

        badOrb orb = new badOrb(getMapTile(69, 175).getLocation(), 0.0f, -3.0f);
        enemies.add(orb);
        badOrb orb2 = new badOrb(getMapTile(72, 177).getLocation(), 0.0f, 3.0f);
        enemies.add(orb2);
        badOrb orb3 = new badOrb(getMapTile(65, 171).getLocation(), -3.0f, -3.0f);
        enemies.add(orb3);
        badOrb orb4 = new badOrb(getMapTile(76, 171).getLocation(), 3.0f, -3.0f);
        enemies.add(orb4);
        int currentY = 167;
        for (int i=0;i<7;i++) {
            badOrb tempOrb = new badOrb(getMapTile(67+i, currentY).getLocation(), 0, -3.0f);
            if (currentY == 167) {
                currentY = 169;
            } else {
                currentY = 167;
            }
            enemies.add(tempOrb);
        }
        badOrb orb5 = new badOrb(getMapTile(96, 187).getLocation(), 3.0f, 0.0f);
        enemies.add(orb5);
        badOrb orb6 = new badOrb(getMapTile(97, 190).getLocation(), 3.0f, 0.0f);
        enemies.add(orb6);
        badOrb orb7 = new badOrb(getMapTile(97, 193).getLocation(), 3.0f, 0.0f);
        enemies.add(orb7);
        

        //Thunder Bird adding here
        //starting point below
        Point startPoint = getMapTile(14, 190).getLocation();
        //ending point 1(needs same y value since it moves linearly and this is where it flips to go
        // to the right) below
        float startX = getMapTile(12, 190).getLocation().x;
        //end point 2 (where it flips to the left again) could be
        // different below
        float endX = getMapTile(14, 190).getLocation().x;
        ThunderBirdEnemy thunderBird = new ThunderBirdEnemy(startPoint, startX, endX, Direction.LEFT);
        enemies.add(thunderBird);

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


        SpeedRing speedRing = new SpeedRing(getMapTile(4, 196).getLocation());
        enhancedMapTiles.add(speedRing);

        Trigger trial1 = new Trigger(getMapTile(33, 161).getLocation(), 11, 0, 0, 48, 32, this);
        enhancedMapTiles.add(trial1);

        Trigger trial2 = new Trigger(getMapTile(77, 166).getLocation(), 12, 0, 0, 16, 16, this);
        enhancedMapTiles.add(trial2);

        Trigger trial3 = new Trigger(getMapTile(118, 151).getLocation(), 13, 0, 0, 16, 48, this);
        enhancedMapTiles.add(trial3);

        



        BouncyCloud bouncyCloud = new BouncyCloud(getMapTile(15, 196).getLocation(), "BouncyCloud.png");
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

        Teleporter teleporter2 = new Teleporter(getMapTile(36, 162).getLocation().subtractY(48), "DiamondTeleporter.png",33, 196);
        npcs.add(teleporter2);

        Teleporter teleporter3 = new Teleporter(getMapTile(69, 196).getLocation().subtractY(48), "DiamondTeleporter.png",64, 177);
        npcs.add(teleporter3);

        Teleporter teleporter4 = new Teleporter(getMapTile(70, 165).getLocation().subtractY(48), "DiamondTeleporter.png",69, 196);
        npcs.add(teleporter4);

        Teleporter teleporter5 = new Teleporter(getMapTile(109, 196).getLocation().subtractY(48), "DiamondTeleporter.png",110, 177);
        npcs.add(teleporter5);

        

        return npcs;
    }
}
