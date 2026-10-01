package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Walrus;
import Tilesets.CastleTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelOneMap extends Map {

    public LevelOneMap() {
        super("level_one_map.txt", new CastleTileset());
        this.playerStartPosition = getMapTile(3, 16).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy Goblin1 = new BugEnemy(getMapTile(31, 16).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(Goblin1);;

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

        Torch torch1 = new Torch(getMapTile(12, 16).getLocation().subtractY(13), 1);
        npcs.add(torch1);
        Torch torch2 = new Torch(getMapTile(43, 13).getLocation().subtractY(13), 2);
        npcs.add(torch2);
        Walrus sign1 = new Walrus(getMapTile(7, 15).getLocation().subtractY(13), "You have the magic touch!" + "\n" + "Use the arrow keys to move, and press Z to shoot a fireball");
        npcs.add(sign1);
        Walrus sign2 = new Walrus(getMapTile(27, 14).getLocation().subtractY(13), "Watch out for the goblin! Use your fireball to defeat him");
        npcs.add(sign2);

        Mentor mentor = new Mentor(getMapTile(4, 11).getLocation().subtractY(13));
        npcs.add(mentor);

        return npcs;
    }
}
