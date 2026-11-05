package Maps;

import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.Button;
import EnhancedMapTiles.CrumblingBlock;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.IceBlock;
import EnhancedMapTiles.Trigger;
import EnhancedMapTiles.IceBlockRespawn;
import EnhancedMapTiles.SnowballLauncher;
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Tilesets.IceCaveTileset;
import Utils.CheckpointManager;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelTwoMap extends Map {

    public LevelTwoMap() {
        super("level_two_map.txt", new IceCaveTileset());
        this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
        //this.playerStartPosition = getMapTile(135, 24).getLocation().subtractY(0);
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
     /* Level start icicles
      */
        Icicle Icicle1 = new Icicle(getMapTile(5, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle1);;

        Icicle Icicle2 = new Icicle(getMapTile(6, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle2);

        Icicle Icicle3 = new Icicle(getMapTile(7, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle3);

        Icicle Icicle4 = new Icicle(getMapTile(8, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle4);

        Icicle Icicle5 = new Icicle(getMapTile(9, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle5);

        Icicle Icicle6 = new Icicle(getMapTile(10, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle6);

        Icicle Icicle7 = new Icicle(getMapTile(11, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle7);

        Icicle Icicle8 = new Icicle(getMapTile(12, 28).getLocation().subtractY(0), false);
        enemies.add(Icicle8);

        /* parkour icicles
         */
        SnowGoblinEnemy snowGoblinEnemy1 = new SnowGoblinEnemy(getMapTile(17, 17).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy1);

        SnowGoblinEnemy snowGoblinEnemy2 = new SnowGoblinEnemy(getMapTile(18, 17).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy2);

        SnowGoblinEnemy snowGoblinEnemy3 = new SnowGoblinEnemy(getMapTile(19, 17).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy3);


        Icicle Icicle9 = new Icicle(getMapTile(31, 27).getLocation().subtractY(0), false);
        enemies.add(Icicle9);;

        Icicle Icicle10 = new Icicle(getMapTile(32, 27).getLocation().subtractY(0), false);
        enemies.add(Icicle10);

        Icicle Icicle11 = new Icicle(getMapTile(34, 27).getLocation().subtractY(0), false);
        enemies.add(Icicle11);;

        Icicle Icicle12 = new Icicle(getMapTile(35, 27).getLocation().subtractY(0), false);
        enemies.add(Icicle12);

        Icicle Icicle13 = new Icicle(getMapTile(37, 27).getLocation().subtractY(0), false);
        enemies.add(Icicle13);;

        Icicle Icicle14 = new Icicle(getMapTile(38, 27).getLocation().subtractY(0), false);
        enemies.add(Icicle14);

        Icicle Icicle15 = new Icicle(getMapTile(71, 26).getLocation().subtractY(0), false);
        enemies.add(Icicle15);

        Icicle Icicle16 = new Icicle(getMapTile(72, 26).getLocation().subtractY(0), false);
        enemies.add(Icicle16);

        Icicle Icicle17 = new Icicle(getMapTile(80, 26).getLocation().subtractY(0), false);
        enemies.add(Icicle17);

        Icicle Icicle18 = new Icicle(getMapTile(81, 26).getLocation().subtractY(0), false);
        enemies.add(Icicle18);
        
        Icicle Icicle19 = new Icicle(getMapTile(75, 23).getLocation().subtractY(0), true);
        enemies.add(Icicle19);

        Icicle Icicle20 = new Icicle(getMapTile(76, 23).getLocation().subtractY(0), true);
        enemies.add(Icicle20);

        Icicle Icicle21 = new Icicle(getMapTile(77, 23).getLocation().subtractY(0), true);
        enemies.add(Icicle21);

        for (int i = 0; i < 3; i++) {
            Icicle icicle = new Icicle(getMapTile(86 + i, 20).getLocation(), false);
            enemies.add(icicle);
        }

        Icicle Icicle22 = new Icicle(getMapTile(86, 18).getLocation().subtractY(0), true);
        enemies.add(Icicle22);

        Icicle Icicle23 = new Icicle(getMapTile(88, 18).getLocation().subtractY(0), true);
        enemies.add(Icicle23);

        Icicle Icicle24 = new Icicle(getMapTile(86, 15).getLocation().subtractY(0), false);
        enemies.add(Icicle24);

        Icicle Icicle25 = new Icicle(getMapTile(88, 15).getLocation().subtractY(0), false);
        enemies.add(Icicle25);

        Icicle Icicle26 = new Icicle(getMapTile(87, 12).getLocation().subtractY(0), true);
        enemies.add(Icicle26);

        Icicle Icicle27 = new Icicle(getMapTile(88, 12).getLocation().subtractY(0), true);
        enemies.add(Icicle27);

        SnowBallBoss boss2 = new SnowBallBoss(getMapTile(200,11).getLocation(), Direction.LEFT);
        enemies.add(boss2);
        /* Enemies
         */

        PenguinEnemy penguinEnemy1 = new PenguinEnemy(getMapTile(90, 3).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy1);

        PenguinEnemy penguinEnemy2 = new PenguinEnemy(getMapTile(92, 3).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy2);

        PenguinEnemy penguinEnemy3 = new PenguinEnemy(getMapTile(94, 3).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy3);

        LavaSlime lavaSlime1 = new LavaSlime(getMapTile(137, 24).getLocation().subtractX(16));
        enemies.add(lavaSlime1);

        LavaSlime lavaSlime2 = new LavaSlime(getMapTile(129, 19).getLocation().subtractX(16));
        enemies.add(lavaSlime2);

        LavaSlime lavaSlime3 = new LavaSlime(getMapTile(134, 19).getLocation().subtractX(-16));
        enemies.add(lavaSlime3);

        LavaSlime lavaSlime4 = new LavaSlime(getMapTile(160, 6).getLocation().subtractX(16));
        enemies.add(lavaSlime4);

        
        PenguinEnemy penguinEnemy4 = new PenguinEnemy(getMapTile(152, 11).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy4);

        PenguinEnemy penguinEnemy5 = new PenguinEnemy(getMapTile(157, 11).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy5);    


        return enemies;


    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();



        
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(227, 19).getLocation(), "LightningChest.png");
        enhancedMapTiles.add(endLevelBox);


       

        IceBlockRespawn iceBlockRespawn2 = new IceBlockRespawn(getMapTile(68, 19).getLocation());
        enhancedMapTiles.add(iceBlockRespawn2);

        IceBlock iceBlock4 = new IceBlock(getMapTile(75,20).getLocation());
        enhancedMapTiles.add(iceBlock4);

        IceBlock iceBlock5 = new IceBlock(getMapTile(93,16).getLocation());
        enhancedMapTiles.add(iceBlock5);

        IceBlock iceBlock6 = new IceBlock(getMapTile(85,2).getLocation());
        enhancedMapTiles.add(iceBlock6);

        IceBlock iceBlock9 = new IceBlock(getMapTile(85,3).getLocation());
        enhancedMapTiles.add(iceBlock9);

        IceBlock iceBlock7 = new IceBlock(getMapTile(96,13).getLocation());
        enhancedMapTiles.add(iceBlock7);

        IceBlockRespawn iceBlockRespawn3 = new IceBlockRespawn(getMapTile(99,10).getLocation());
        enhancedMapTiles.add(iceBlockRespawn3);

        Button button1 = new Button(getMapTile(82, 19).getLocation(), 66, 66, 23, 26, this, "Button.png");
        enhancedMapTiles.add(button1);

        Button button2 = new Button(getMapTile(87, 3).getLocation(), 107, 107, 1, 3, this, "Button.png");
        enhancedMapTiles.add(button2);

        for (int i = 0; i < 14; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(86 + i, 27).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        HorizontalMovingPlatform hmp1 = new HorizontalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(88, 23).getLocation(),
                getMapTile(98, 23).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp1);

        VerticalMovingPlatform vmp1 = new VerticalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(86, 10).getLocation(),
                getMapTile(86, 20).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp1);

        IceBlockRespawn iceBlockRespawn = new IceBlockRespawn(getMapTile(44, 24).getLocation());
        enhancedMapTiles.add(iceBlockRespawn);

        VerticalMovingPlatform vmp2 = new VerticalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(112, 4).getLocation(),
                getMapTile(112, 28).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp2);


        VerticalMovingPlatform vmp4 = new VerticalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(116, 4).getLocation(),
                getMapTile(116, 28).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp4);

        for (int i = 0; i < 21; i = i + 3) {
            SnowballLauncher snowballLauncher = new SnowballLauncher(getMapTile(107, 4 + i).getLocation().subtractY(16), true);
            enhancedMapTiles.add(snowballLauncher);
        }

        for (int i = 0; i < 12; i = i + 3) {
            SnowballLauncher snowballLauncher = new SnowballLauncher(getMapTile(119, 6 + i).getLocation().subtractY(16), false);
            enhancedMapTiles.add(snowballLauncher);
        }

        IceBlockRespawn iceBlockRespawn4 = new IceBlockRespawn(getMapTile(141, 15).getLocation());
        enhancedMapTiles.add(iceBlockRespawn4);

        SnowballLauncher snowballLauncher1 = new SnowballLauncher(getMapTile(127, 8).getLocation().subtractY(16), true);
        enhancedMapTiles.add(snowballLauncher1);

        SnowballLauncher snowballLauncher2 = new SnowballLauncher(getMapTile(125, 5).getLocation().subtractY(16), true);
        enhancedMapTiles.add(snowballLauncher2);









        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();


        // sign1
        Sign sign1 = new Sign(
                getMapTile(4, 27).getLocation().subtractY(13),
                "Welcome to the Frostbite Caverns!"
                + "\n" + "You've gained the power of Wave Attack (X)."
                + "\n" + "Defeat the Enemies, overcome the icy passages,"
                + "\n" + "and retrieve the third artifact! "

        );

        npcs.add(sign1);

        Sign sign2 = new Sign(
                getMapTile(42, 25).getLocation().subtractY(13),
                "Hmmm: Snow + Water = ???"
        );

        npcs.add(sign2);

        Sign sign3 = new Sign(
                getMapTile(67, 19).getLocation().subtractY(13),
                "We can melt the Ice Blocks using the Fireball as well to retrace our steps."
        );

        npcs.add(sign3);


        return npcs;
    }
}
