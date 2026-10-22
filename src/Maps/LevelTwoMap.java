package Maps;

import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.IceBlock;
import EnhancedMapTiles.IceBlockRespawn;
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
        this.playerStartPosition = getMapTile(2, 16).getLocation();

        //test
        //this.playerStartPosition = getMapTile(42, 15).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
     /* Level start icicles
      */
        Icicle Icicle1 = new Icicle(getMapTile(5, 18).getLocation().subtractY(0));
        enemies.add(Icicle1);;

        Icicle Icicle2 = new Icicle(getMapTile(6, 18).getLocation().subtractY(0));
        enemies.add(Icicle2);

        Icicle Icicle3 = new Icicle(getMapTile(7, 18).getLocation().subtractY(0));
        enemies.add(Icicle3);

        Icicle Icicle4 = new Icicle(getMapTile(8, 18).getLocation().subtractY(0));
        enemies.add(Icicle4);

        Icicle Icicle5 = new Icicle(getMapTile(9, 18).getLocation().subtractY(0));
        enemies.add(Icicle5);

        Icicle Icicle6 = new Icicle(getMapTile(10, 18).getLocation().subtractY(0));
        enemies.add(Icicle6);

        Icicle Icicle7 = new Icicle(getMapTile(11, 18).getLocation().subtractY(0));
        enemies.add(Icicle7);

        Icicle Icicle8 = new Icicle(getMapTile(12, 18).getLocation().subtractY(0));
        enemies.add(Icicle8);

        /* parkour icicles
         */
        SnowGoblinEnemy snowGoblinEnemy1 = new SnowGoblinEnemy(getMapTile(17, 7).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy1);

        SnowGoblinEnemy snowGoblinEnemy2 = new SnowGoblinEnemy(getMapTile(18, 7).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy2);

        SnowGoblinEnemy snowGoblinEnemy3 = new SnowGoblinEnemy(getMapTile(19, 7).getLocation().subtractY(0), Direction.LEFT);
        enemies.add(snowGoblinEnemy3);

        Icicle Icicle9 = new Icicle(getMapTile(31, 17).getLocation().subtractY(0));
        enemies.add(Icicle9);;

        Icicle Icicle10 = new Icicle(getMapTile(32, 17).getLocation().subtractY(0));
        enemies.add(Icicle10);

        Icicle Icicle11 = new Icicle(getMapTile(34, 17).getLocation().subtractY(0));
        enemies.add(Icicle11);;

        Icicle Icicle12 = new Icicle(getMapTile(35, 17).getLocation().subtractY(0));
        enemies.add(Icicle12);

        Icicle Icicle13 = new Icicle(getMapTile(37, 17).getLocation().subtractY(0));
        enemies.add(Icicle13);;

        Icicle Icicle14 = new Icicle(getMapTile(38, 17).getLocation().subtractY(0));
        enemies.add(Icicle14);

        Icicle Icicle15 = new Icicle(getMapTile(71, 16).getLocation().subtractY(0));
        enemies.add(Icicle15);

        Icicle Icicle16 = new Icicle(getMapTile(72, 16).getLocation().subtractY(0));
        enemies.add(Icicle16);

        Icicle Icicle17 = new Icicle(getMapTile(80, 16).getLocation().subtractY(0));
        enemies.add(Icicle17);

        Icicle Icicle18 = new Icicle(getMapTile(81, 16).getLocation().subtractY(0));
        enemies.add(Icicle18);

        /* Enemies
         */


        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();



        
        //EndLevelBox endLevelBox = new EndLevelBox(getMapTile(46, 15).getLocation(), "LightningChest.png");
        //enhancedMapTiles.add(endLevelBox);

       

        IceBlock iceBlock2 = new IceBlock(getMapTile(67,9).getLocation());
        enhancedMapTiles.add(iceBlock2);

        IceBlock iceBlock3 = new IceBlock(getMapTile(70,9).getLocation());
        enhancedMapTiles.add(iceBlock3);

        IceBlock iceBlock4 = new IceBlock(getMapTile(86,16).getLocation());
        enhancedMapTiles.add(iceBlock4);

        IceBlockRespawn iceBlockRespawn = new IceBlockRespawn(getMapTile(44, 14).getLocation());
        enhancedMapTiles.add(iceBlockRespawn);


        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();


        // sign1
        Sign sign1 = new Sign(
                getMapTile(4, 17).getLocation().subtractY(13),
                "Welcome to the Frostbite Caverns!"
                + "\n" + "You've gained the power of Wave Attack (X)."
                + "\n" + "Defeat the Enemies, overcome the icy passages,"
                + "\n" + "and retrieve the third artifact! "

        );

        npcs.add(sign1);

        Sign sign2 = new Sign(
                getMapTile(42, 15).getLocation().subtractY(13),
                "We can push the Ice Block using the Wave!"
        );

        npcs.add(sign2);

        Sign sign3 = new Sign(
                getMapTile(78, 5).getLocation().subtractY(13),
                "We can melt the Ice Blocks using the Fireball as well!"
        );

        npcs.add(sign3);

        return npcs;
    }
}
