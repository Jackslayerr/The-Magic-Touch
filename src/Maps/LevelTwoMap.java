package Maps;

import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
import Enemies.*;
import Tilesets.IceCaveTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelTwoMap extends Map {

    public LevelTwoMap() {
        super("level_two_map.txt", new IceCaveTileset());
        this.playerStartPosition = getMapTile(2, 16).getLocation();
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

        /* Enemies
         */


        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
        // enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();



        return npcs;
    }
}
