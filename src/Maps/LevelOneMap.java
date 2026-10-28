package Maps;

import Enemies.*;
import Engine.ImageLoader;
import Level.*;
import NPCs.Mentor;
import NPCs.Torch;
import NPCs.Sign;
import Tilesets.CastleTileset;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Trigger;
import EnhancedMapTiles.VerticalMovingPlatform;
import EnhancedMapTiles.BouncyCloud;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.CrumblingBlock;
import GameObject.Rectangle;
import Utils.CheckpointManager;
import Utils.Direction;
import Level.*;
import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelOneMap extends Map {

    public LevelOneMap() {
        super("level_one_map.txt", new CastleTileset());
        // 3,16
        this.playerStartPosition = CheckpointManager.getInstance().getCheckPoint();
        //System.out.println(this.playerStartPosition);
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        GoblinEnemy Goblin1 = new GoblinEnemy(
                getMapTile(31, 17).getLocation().subtractY(25),
                Direction.LEFT
        );

        LavaMonster lavaMonster = new LavaMonster(getMapTile(125, 11).getLocation().subtractY(13),
                getMapTile(130, 11).getLocation().subtractY(13), Direction.LEFT);

       BatEnemy Bat1 = new BatEnemy(getMapTile(41, 13).getLocation().subtractY(20), Direction.LEFT);
       BatEnemy Bat2 = new BatEnemy(getMapTile(84, 6).getLocation().subtractY(20), Direction.LEFT);
       BatEnemy Bat3 = new BatEnemy(getMapTile(83, 13).getLocation().subtractY(20), Direction.LEFT);
       BatEnemy Bat4 = new BatEnemy(getMapTile(69, 10).getLocation().subtractY(20), Direction.LEFT);

       GoblinEnemy Goblin2 = new GoblinEnemy(
                getMapTile(48, 18).getLocation().subtractY(25),
                Direction.LEFT
        );
        GoblinEnemy Goblin3 = new GoblinEnemy(
                getMapTile(55, 11).getLocation().subtractY(25),
                Direction.LEFT
        );
        GoblinEnemy Goblin4 = new GoblinEnemy(
                getMapTile(57, 2).getLocation().subtractY(25),
                Direction.LEFT
        );
        GoblinEnemy Goblin5 = new GoblinEnemy(
                getMapTile(67, 18).getLocation().subtractY(25),
                Direction.LEFT
        );
       
        enemies.add(Goblin1);
        enemies.add(Goblin2);
        enemies.add(Goblin3);
        enemies.add(Goblin4);
        enemies.add(Goblin5);
        enemies.add(lavaMonster);
        enemies.add(Bat1);
        enemies.add(Bat2);
        enemies.add(Bat3);
        enemies.add(Bat4);

        Spike Spike1 = new Spike(getMapTile(54, 11).getLocation().subtractY(0));
        enemies.add(Spike1);;

        Spike Spike2 = new Spike(getMapTile(66, 18).getLocation().subtractY(0));
        enemies.add(Spike2);;

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(146, 11).getLocation(), "WaterChest.png");
        enhancedMapTiles.add(endLevelBox);


        for (int i = 0; i < 10; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(100 + i, 12).getLocation(), "CrumblingBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }

        for (int i = 0; i < 13; i++) {
            CrumblingBlock crumblingBlock = new CrumblingBlock(getMapTile(121 + i, 12).getLocation(), "CrumblingBlock.png");
            enhancedMapTiles.add(crumblingBlock);
        }
        Trigger trigger = new Trigger(getMapTile(122, 7).getLocation(), 1, 0, 0, 16, 80, this);
        enhancedMapTiles.add(trigger);

        Checkpoint checkpoint = new Checkpoint(getMapTile(65, 18).getLocation(), 16, 16, this, 1);
        enhancedMapTiles.add(checkpoint);

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
                getMapTile(93, 16).getLocation().subtractY(13),
                5
        );

        npcs.add(torch5);

        Torch torch6 = new Torch(
                getMapTile(79, 18).getLocation().subtractY(13),
                6
        );

        npcs.add(torch6);

        Torch torch7 = new Torch(
                getMapTile(90, 6).getLocation().subtractY(13),
                7
        );

        npcs.add(torch7);

        Torch torch8 = new Torch(
                getMapTile(78, 4).getLocation().subtractY(13),
                8
        );

        npcs.add(torch8);

        Torch torch9 = new Torch(
                getMapTile(79, 9).getLocation().subtractY(13),
                9
        );

        npcs.add(torch9);

        // Goblin warning sign
        Sign sign = new Sign(
                getMapTile(27, 14).getLocation().subtractY(13),
                "Watch out for the goblin! Use your fireball to defeat him"
        );

        npcs.add(sign);

        // sign tutorial
        Sign sign2 = new Sign(
                getMapTile(7, 15).getLocation().subtractY(13),
                 "Welcome to the Fire Dungeon!"
                + "\n" + "Defeat the goblins and claim the first artifact."
                + "\n" + "Press Z to use your Fire Hand Rune."
                + "\n" + "Light torches to solve the puzzles!"

        );

        npcs.add(sign2);

        Sign sign3 = new Sign(
                getMapTile(56, 2).getLocation().subtractY(13),
                "Somethings should stay secret-B"
        );

        npcs.add(sign3);

        Sign sign4 = new Sign(
                getMapTile(117, 11).getLocation().subtractY(13),
                "WARNING: Boss Ahead. Watching out for the disappearing platforms "
                + "\n" + "The boss has to watch out for them too..."
        );
        npcs.add(sign4);

        

        return npcs;
    }
}