package Maps;

import Enemies.BugEnemy;
import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
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

        BugEnemy Goblin1 = new BugEnemy(
                getMapTile(31, 16).getLocation().subtractY(25),
                Direction.LEFT
        );

        enemies.add(Goblin1);

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

        // First torch
        Torch torch1 = new Torch(
                getMapTile(12, 16).getLocation().subtractY(13),
                1
        );

        npcs.add(torch1);

        // Second torch
        Torch torch2 = new Torch(
                getMapTile(43, 14).getLocation().subtractY(13),
                2
        );

        npcs.add(torch2);

        // Goblin warning sign
        Sign sign = new Sign(
                getMapTile(27, 14).getLocation().subtractY(13),
                "Watch out for the goblin! Use your fireball to defeat him"
        );

        npcs.add(sign);

        // Mentor tutorial
        Sign sign2 = new Sign(
                getMapTile(7, 15).getLocation().subtractY(13),
                "Your treasure-hunting dreams have come true!"
                + "\n" + "Your mentor, a retired hunter has entrusted you with finding"
                + "\n" + "the legendary artifacts of Bonwold the Great."
                + "\n" + "Welcome to the Fire Dungeon!"
                + "\n" + "Defeat the goblins and claim the first artifact."
                + "\n" + "Press Z to use your Fire Hand Rune."
                + "\n" + "Light torches to solve the puzzles!"

        );

        npcs.add(sign2);

        return npcs;
    }
}