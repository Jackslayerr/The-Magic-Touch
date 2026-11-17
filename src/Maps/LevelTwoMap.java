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
        //this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
        this.playerStartPosition = getMapTile(128, 22).getLocation().subtractY(0);
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
     /* Level start icicles
      */
        Icicle Icicle1 = new Icicle(getMapTile(5, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle1);;

        Icicle Icicle2 = new Icicle(getMapTile(6, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle2);

        Icicle Icicle3 = new Icicle(getMapTile(7, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle3);

        Icicle Icicle4 = new Icicle(getMapTile(8, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle4);

        Icicle Icicle5 = new Icicle(getMapTile(9, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle5);

        Icicle Icicle6 = new Icicle(getMapTile(10, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle6);

        Icicle Icicle7 = new Icicle(getMapTile(11, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle7);

        Icicle Icicle8 = new Icicle(getMapTile(12, 58).getLocation().subtractY(0), false);
        enemies.add(Icicle8);

        /* parkour icicles
         */
        SnowGoblinEnemy snowGoblinEnemy1 = new SnowGoblinEnemy(getMapTile(17, 47).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy1);

        SnowGoblinEnemy snowGoblinEnemy2 = new SnowGoblinEnemy(getMapTile(18, 47).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy2);

        SnowGoblinEnemy snowGoblinEnemy3 = new SnowGoblinEnemy(getMapTile(19, 47).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy3);


        Icicle Icicle9 = new Icicle(getMapTile(31, 57).getLocation().subtractY(0), false);
        enemies.add(Icicle9);;

        Icicle Icicle10 = new Icicle(getMapTile(32, 57).getLocation().subtractY(0), false);
        enemies.add(Icicle10);

        Icicle Icicle11 = new Icicle(getMapTile(34, 57).getLocation().subtractY(0), false);
        enemies.add(Icicle11);;

        Icicle Icicle12 = new Icicle(getMapTile(35, 57).getLocation().subtractY(0), false);
        enemies.add(Icicle12);

        Icicle Icicle13 = new Icicle(getMapTile(37, 57).getLocation().subtractY(0), false);
        enemies.add(Icicle13);;

        Icicle Icicle14 = new Icicle(getMapTile(38, 57).getLocation().subtractY(0), false);
        enemies.add(Icicle14);

        Icicle Icicle15 = new Icicle(getMapTile(71, 56).getLocation().subtractY(0), false);
        enemies.add(Icicle15);

        Icicle Icicle16 = new Icicle(getMapTile(72, 56).getLocation().subtractY(0), false);
        enemies.add(Icicle16);

        Icicle Icicle17 = new Icicle(getMapTile(80, 56).getLocation().subtractY(0), false);
        enemies.add(Icicle17);

        Icicle Icicle18 = new Icicle(getMapTile(81, 56).getLocation().subtractY(0), false);
        enemies.add(Icicle18);
        
        Icicle Icicle19 = new Icicle(getMapTile(75, 53).getLocation().subtractY(0), true);
        enemies.add(Icicle19);

        Icicle Icicle20 = new Icicle(getMapTile(76, 53).getLocation().subtractY(0), true);
        enemies.add(Icicle20);

        Icicle Icicle21 = new Icicle(getMapTile(77, 53).getLocation().subtractY(0), true);
        enemies.add(Icicle21);

        for (int i = 0; i < 3; i++) {
            Icicle icicle = new Icicle(getMapTile(86 + i, 50).getLocation(), false);
            enemies.add(icicle);
        }

        Icicle Icicle22 = new Icicle(getMapTile(86, 48).getLocation().subtractY(0), true);
        enemies.add(Icicle22);

        Icicle Icicle23 = new Icicle(getMapTile(88, 48).getLocation().subtractY(0), true);
        enemies.add(Icicle23);

        Icicle Icicle24 = new Icicle(getMapTile(86, 45).getLocation().subtractY(0), false);
        enemies.add(Icicle24);

        Icicle Icicle25 = new Icicle(getMapTile(88, 45).getLocation().subtractY(0), false);
        enemies.add(Icicle25);

        Icicle Icicle26 = new Icicle(getMapTile(87, 42).getLocation().subtractY(0), true);
        enemies.add(Icicle26);

        Icicle Icicle27 = new Icicle(getMapTile(88, 42).getLocation().subtractY(0), true);
        enemies.add(Icicle27);

        SnowBallBoss boss2 = new SnowBallBoss(getMapTile(205, 41).getLocation(), Direction.LEFT);
        enemies.add(boss2);
        /* Enemies
         */

        PenguinEnemy penguinEnemy1 = new PenguinEnemy(getMapTile(90, 33).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy1);

        PenguinEnemy penguinEnemy2 = new PenguinEnemy(getMapTile(92, 33).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy2);

        PenguinEnemy penguinEnemy3 = new PenguinEnemy(getMapTile(94, 33).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy3);

        LavaSlime lavaSlime1 = new LavaSlime(getMapTile(142, 54).getLocation().subtractX(16));
        enemies.add(lavaSlime1);

        LavaSlime lavaSlime2 = new LavaSlime(getMapTile(134, 49).getLocation().subtractX(16));
        enemies.add(lavaSlime2);

        LavaSlime lavaSlime3 = new LavaSlime(getMapTile(139, 49).getLocation().subtractX(-16));
        enemies.add(lavaSlime3);

        LavaSlime lavaSlime5 = new LavaSlime(getMapTile(136, 40).getLocation().subtractX(-16));
        enemies.add(lavaSlime5);

        SnowGoblinEnemy snowGoblinEnemy4 = new SnowGoblinEnemy(getMapTile(119, 25).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(snowGoblinEnemy4);

        SnowGoblinEnemy snowGoblinEnemy5 = new SnowGoblinEnemy(getMapTile(122, 25).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy5);

        Spike Spike30 = new Spike(getMapTile(118, 25).getLocation().subtractY(0));
        enemies.add(Spike30);

        Spike Spike31 = new Spike(getMapTile(123, 25).getLocation().subtractY(0));
        enemies.add(Spike31);



        for (int i = 0; i < 3; i++) {
            Icicle icicle = new Icicle(getMapTile(138 + i, 20).getLocation(), true);
            enemies.add(icicle);
        }

        for (int i = 0; i < 3; i++) {
            Spike spike = new Spike(getMapTile(138 + i, 22).getLocation());
            enemies.add(spike);
        }

        Icicle Icicle28 = new Icicle(getMapTile(134, 23).getLocation().subtractY(0), true);
        enemies.add(Icicle28);

        Icicle Icicle29 = new Icicle(getMapTile(143, 20).getLocation().subtractY(0), true);
        enemies.add(Icicle29);

        Spike Spike3 = new Spike(getMapTile(145, 22).getLocation());
        enemies.add(Spike3);

        PenguinEnemy penguinEnemy6 = new PenguinEnemy(getMapTile(104, 26).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy6);

        PenguinEnemy penguinEnemy7 = new PenguinEnemy(getMapTile(110, 26).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(penguinEnemy7);

        LavaSlime lavaSlime6 = new LavaSlime(getMapTile(99, 22).getLocation().subtractX(0));
        enemies.add(lavaSlime6);

        for (int i = 0; i < 20; i = i + 5) {
            LavaSlime lavaSlime = new LavaSlime(getMapTile(81 + i, 18).getLocation().subtractX(8));
            enemies.add(lavaSlime);
        }

        for (int i = 0; i < 15; i = i + 5) {
            Icicle icicle = new Icicle(getMapTile(83 + i, 18).getLocation(), true);
            enemies.add(icicle);
        }

        for (int i = 0; i < 15; i = i + 5) {
            Icicle icicle = new Icicle(getMapTile(84 + i, 18).getLocation(), true);
            enemies.add(icicle);
        }

        for (int i = 0; i < 15; i = i + 5) {
            Spike spike = new Spike(getMapTile(83 + i, 16).getLocation());
            enemies.add(spike);
        }

        for (int i = 0; i < 15; i = i + 5) {
            Spike spike = new Spike(getMapTile(84 + i, 16).getLocation());
            enemies.add(spike);
        }

        for (int i = 0; i < 15; i = i + 5) {
            Icicle icicle = new Icicle(getMapTile(134 + i, 13).getLocation(), false);
            enemies.add(icicle);
        }

        for (int i = 0; i < 15; i = i + 5) {
            Icicle icicle = new Icicle(getMapTile(135 + i, 13).getLocation(), false);
            enemies.add(icicle);
        }

        PenguinEnemy penguinEnemy8 = new PenguinEnemy(getMapTile(137, 12).getLocation().subtractY(0), Direction.RIGHT);
        enemies.add(penguinEnemy8);

        PenguinEnemy penguinEnemy9 = new PenguinEnemy(getMapTile(142, 12).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(penguinEnemy9);

        return enemies;


    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();



        
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(232, 49).getLocation(), "LightningChest.png");
        enhancedMapTiles.add(endLevelBox);


       

        IceBlockRespawn iceBlockRespawn2 = new IceBlockRespawn(getMapTile(68, 49).getLocation());
        enhancedMapTiles.add(iceBlockRespawn2);

        IceBlock iceBlock4 = new IceBlock(getMapTile(75, 50).getLocation());
        enhancedMapTiles.add(iceBlock4);

        IceBlock iceBlock5 = new IceBlock(getMapTile(93, 46).getLocation());
        enhancedMapTiles.add(iceBlock5);

        IceBlock iceBlock6 = new IceBlock(getMapTile(85, 32).getLocation());
        enhancedMapTiles.add(iceBlock6);

        IceBlock iceBlock9 = new IceBlock(getMapTile(85, 33).getLocation());
        enhancedMapTiles.add(iceBlock9);

        IceBlock iceBlock7 = new IceBlock(getMapTile(96, 43).getLocation());
        enhancedMapTiles.add(iceBlock7);

        IceBlockRespawn iceBlockRespawn3 = new IceBlockRespawn(getMapTile(99, 40).getLocation());
        enhancedMapTiles.add(iceBlockRespawn3);

        Button button1 = new Button(getMapTile(82, 49).getLocation(), 66, 66, 53, 56, this, "Button.png", 1);
        enhancedMapTiles.add(button1);

        Button button2 = new Button(getMapTile(87, 33).getLocation(), 107, 107, 31, 33, this, "Button.png", 1);
        enhancedMapTiles.add(button2);

        for (int i = 0; i < 14; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(86 + i, 57).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        HorizontalMovingPlatform hmp1 = new HorizontalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(88, 53).getLocation(),
                getMapTile(98, 53).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp1);

        VerticalMovingPlatform vmp1 = new VerticalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(86, 40).getLocation(),
                getMapTile(86, 50).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.DOWN
        );
        enhancedMapTiles.add(vmp1);

        IceBlockRespawn iceBlockRespawn1 = new IceBlockRespawn(getMapTile(44, 54).getLocation());
        enhancedMapTiles.add(iceBlockRespawn1);

        Button button3 = new Button(getMapTile(111, 33).getLocation(), 66, 66, 53, 56, this, "Button.png", 3);
        enhancedMapTiles.add(button3);

        for (int i = 0; i < 21; i = i + 3) {
            SnowballLauncher snowballLauncher = new SnowballLauncher(getMapTile(107, 35 + i).getLocation().subtractY(0), true);
            enhancedMapTiles.add(snowballLauncher);
        }

        for (int i = 0; i < 12; i = i + 3) {
            SnowballLauncher snowballLauncher = new SnowballLauncher(getMapTile(122, 37 + i).getLocation().subtractY(0), false);
            enhancedMapTiles.add(snowballLauncher);
        }

        IceBlockRespawn iceBlockRespawn4 = new IceBlockRespawn(getMapTile(146, 45).getLocation());
        enhancedMapTiles.add(iceBlockRespawn4);

        IceBlockRespawn iceBlockRespawn5 = new IceBlockRespawn(getMapTile(135, 36).getLocation());
        enhancedMapTiles.add(iceBlockRespawn5);

        for (int i = 0; i < 15; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(132 + i, 29).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        for (int i = 0; i < 1; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(130 + i, 28).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        for (int i = 0; i < 6; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(140 + i, 26).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        IceBlockRespawn iceBlockRespawn6 = new IceBlockRespawn(getMapTile(139, 25).getLocation());
        enhancedMapTiles.add(iceBlockRespawn6);

        HorizontalMovingPlatform hmp2 = new HorizontalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(130, 23).getLocation(),
                getMapTile(150, 23).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp2);


        for (int i = 0; i < 3; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(133 + i, 24).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        for (int i = 0; i < 3; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(138 + i, 21).getLocation(), "CrumblingIceBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        IceBlockRespawn iceBlockRespawn7 = new IceBlockRespawn(getMapTile(102, 24).getLocation());
        enhancedMapTiles.add(iceBlockRespawn7);

        IceBlockRespawn iceBlockRespawn8 = new IceBlockRespawn(getMapTile(112, 24).getLocation());
        enhancedMapTiles.add(iceBlockRespawn8);

        SnowballLauncher snowballLauncher3 = new SnowballLauncher(getMapTile(98, 24).getLocation().subtractY(0), true);
        enhancedMapTiles.add(snowballLauncher3);

        HorizontalMovingPlatform hmp3 = new HorizontalMovingPlatform(
                ImageLoader.load("IceCaveMovingPlatform.png"),
                getMapTile(98, 16).getLocation(),
                getMapTile(105, 16).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 0,48,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp3);


        for (int i = 0; i < 15; i = i + 5) {
            IceBlockRespawn iceBlockRespawn = new IceBlockRespawn(getMapTile(83 + i, 15).getLocation().subtractX(-16));
            enhancedMapTiles.add(iceBlockRespawn);
        }

        for (int i = 0; i < 16; i++) {
            IceBlock iceBlock = new IceBlock(getMapTile(153 + i, 11).getLocation());
            enhancedMapTiles.add(iceBlock);
        }

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();


        // sign1
        Sign sign1 = new Sign(
                getMapTile(4, 57).getLocation().subtractY(13),
                "Welcome to the Frostbite Caverns!"
                + "\n" + "You've gained the power of Wave Attack (X)."
                + "\n" + "Defeat the Enemies, overcome the icy passages,"
                + "\n" + "and retrieve the third artifact! "

        );

        npcs.add(sign1);

        Sign sign2 = new Sign(
                getMapTile(42, 55).getLocation().subtractY(13),
                "Hmmm: Snow + Water = ???"
        );

        npcs.add(sign2);

        Sign sign3 = new Sign(
                getMapTile(67, 49).getLocation().subtractY(13),
                "We can melt the Ice Blocks using the Fireball as well to retrace our steps."
        );

        npcs.add(sign3);


        return npcs;
    }
}
