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
        //this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
        this.playerStartPosition = getMapTile(110,178).getLocation();
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

        Point startPoint1 = getMapTile(116, 157).getLocation();
        float startX1 = getMapTile(111, 157).getLocation().x;
        float endX1 = getMapTile(116, 157).getLocation().x;
        ThunderBirdEnemy thunderBird1 = new ThunderBirdEnemy(startPoint1, startX1, endX1, Direction.LEFT);
        enemies.add(thunderBird1);

        Point startPoint2 = getMapTile(116, 169).getLocation();
        float startX2 = getMapTile(111, 169).getLocation().x;
        float endX2 = getMapTile(116, 169).getLocation().x;
        ThunderBirdEnemy thunderBird2 = new ThunderBirdEnemy(startPoint2, startX2, endX2, Direction.LEFT);
        enemies.add(thunderBird2);




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





        BouncyCloud bouncyCloud1 = new BouncyCloud(getMapTile(110, 181).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud1);

        BouncyCloud bouncyCloud2 = new BouncyCloud(getMapTile(114, 177).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud2);

        BouncyCloud bouncyCloud3 = new BouncyCloud(getMapTile(108, 172).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud3);

        BouncyCloud bouncyCloud4 = new BouncyCloud(getMapTile(117, 172).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud4);

        BouncyCloud bouncyCloud5 = new BouncyCloud(getMapTile(112, 169).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud5);

        BouncyCloud bouncyCloud6 = new BouncyCloud(getMapTile(109, 164).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud6);

        BouncyCloud bouncyCloud7 = new BouncyCloud(getMapTile(116, 164).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud7);

        BouncyCloud bouncyCloud8 = new BouncyCloud(getMapTile(112, 159).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud8);

        BouncyCloud bouncyCloud9 = new BouncyCloud(getMapTile(114, 154).getLocation(), "BouncyCloud.png");
        enhancedMapTiles.add(bouncyCloud9);

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

        return npcs;
    }
}
