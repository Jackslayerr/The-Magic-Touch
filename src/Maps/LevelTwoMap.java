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
import EnhancedMapTiles.VerticalMovingPlatform;
import GameObject.Rectangle;
import Tilesets.IceCaveTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelTwoMap extends Map {

    public LevelTwoMap() {
        super("level_two_map.txt", new IceCaveTileset());
        //actual
        //this.playerStartPosition = getMapTile(2, 26).getLocation();

        //test
        this.playerStartPosition = getMapTile(86, 2).getLocation();
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

        for (int i = 0; i < 7; i= i + 3) {
            SnowGoblinEnemy snowGoblinEnemy = new SnowGoblinEnemy(getMapTile(88 + i, 3).getLocation(), Direction.RIGHT);
            enemies.add(snowGoblinEnemy);
        }

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

        /* Enemies
         */


        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();



        
        //EndLevelBox endLevelBox = new EndLevelBox(getMapTile(46, 15).getLocation(), "LightningChest.png");
        //enhancedMapTiles.add(endLevelBox);

        IceBlock iceBlock1 = new IceBlock(getMapTile(45,25).getLocation());
        enhancedMapTiles.add(iceBlock1);

        IceBlock iceBlock2 = new IceBlock(getMapTile(67,19).getLocation());
        enhancedMapTiles.add(iceBlock2);

        IceBlock iceBlock3 = new IceBlock(getMapTile(70,19).getLocation());
        enhancedMapTiles.add(iceBlock3);

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

        IceBlock iceBlock8 = new IceBlock(getMapTile(99,10).getLocation());
        enhancedMapTiles.add(iceBlock8);

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
                "We can push the Ice Block using the Wave!"
        );

        npcs.add(sign2);

        Sign sign3 = new Sign(
                getMapTile(78, 15).getLocation().subtractY(13),
                "We can melt the Ice Blocks using the Fireball as well!"
        );

        npcs.add(sign3);

        return npcs;
    }
}
