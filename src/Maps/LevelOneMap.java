package Maps;

import Enemies.BatEnemy;
import Enemies.BugEnemy;
import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
import Tilesets.CastleTileset;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
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
       BatEnemy Bat1 = new BatEnemy(getMapTile(41, 13).getLocation().subtractY(20), Direction.LEFT);
        enemies.add(Goblin1);
        enemies.add(Bat1);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(93, 11).getLocation(), "WaterChest.png");
        enhancedMapTiles.add(endLevelBox);

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

        Torch torch3 = new Torch(
                getMapTile(45, 16).getLocation().subtractY(13),
                3
        );

        npcs.add(torch3);

        Torch torch4 = new Torch(
                getMapTile(52, 14).getLocation().subtractY(13),
                4
        );

        npcs.add(torch4);

        Torch torch5 = new Torch(
                getMapTile(93, 15).getLocation().subtractY(-3),
                5
        );

        npcs.add(torch5);

        Torch torch6 = new Torch(
                getMapTile(79, 18).getLocation().subtractY(-3),
                6
        );

        npcs.add(torch6);

        Torch torch7 = new Torch(
                getMapTile(90, 6).getLocation().subtractY(-3),
                7
        );

        npcs.add(torch7);

        Torch torch8 = new Torch(
                getMapTile(78, 4).getLocation().subtractY(-3),
                8
        );

        npcs.add(torch8);

        Torch torch9 = new Torch(
                getMapTile(79, 9).getLocation().subtractY(-3),
                9
        );

        npcs.add(torch9);

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

        Sign sign3 = new Sign(
                getMapTile(56, 2).getLocation().subtractY(13),
                "Secret"
        );

        npcs.add(sign3);

        return npcs;
    }
}