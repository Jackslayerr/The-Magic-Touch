
package Maps;

import Level.*;
import NPCs.*;
import Enemies.*;
import EnhancedMapTiles.*;
import Tilesets.JungleTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

public class LevelFourMap extends Map {

    public LevelFourMap() {
        super("level_four_map.txt", new JungleTileset());

        this.playerStartPosition = getMapTile(1, 96).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // Poison Spore Plant
        SporePlant sporePlant1 = new SporePlant(
            getMapTile(19, 95).getLocation()
        );

        enemies.add(sporePlant1);

        // Wheel Enemy
        WheelEnemy wheel1 = new WheelEnemy(
            getMapTile(11, 95).getLocation(),
            Direction.LEFT
        );

        enemies.add(wheel1);

        // Stone Golem
        StoneGolem golem = new StoneGolem(
            getMapTile(46, 96).getLocation(),
            Direction.LEFT
        );

        enemies.add(golem);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // Vine Sprout
        VineSprout vineSprout1 = new VineSprout(
            getMapTile(3, 96).getLocation().subtractY(16)
        );

        enhancedMapTiles.add(vineSprout1);

        // Burnable Vine
        BurnableVine burnableVine1 = new BurnableVine(
            getMapTile(5, 96).getLocation().subtractY(143)
        );

        enhancedMapTiles.add(burnableVine1);

        // Electric Zap Spawner 
        // ElectricZapSpawner electricZapSpawner1 = new ElectricZapSpawner(
        //     getMapTile(7, 94).getLocation().subtractY(16)
        // );
        // enhancedMapTiles.add(electricZapSpawner1);

        // =====================================
        // VERTICAL MAWTRAP FOREST
        // Three spawners, activating one by one
        // =====================================

        // First Mawtrap
        MawtrapSpawner mawtrapSpawner1 = new MawtrapSpawner(
            getMapTile(6, 97).getLocation(),
            true,
            0
        );

        enhancedMapTiles.add(mawtrapSpawner1);

        // Second Mawtrap - 1.5 seconds later
        MawtrapSpawner mawtrapSpawner2 = new MawtrapSpawner(
            getMapTile(8, 97).getLocation(),
            true,
            30
        );

        enhancedMapTiles.add(mawtrapSpawner2);

        // Third Mawtrap - 3 seconds later
        MawtrapSpawner mawtrapSpawner3 = new MawtrapSpawner(
            getMapTile(10, 97).getLocation(),
            true,
            60
        );

        enhancedMapTiles.add(mawtrapSpawner3);

        // =====================================
        // HORIZONTAL MAWTRAP
        // Wall level, facing left
        // =====================================

        MawtrapHorizontalSpawner horizontalMawtrap1 =
            new MawtrapHorizontalSpawner(
                getMapTile(18, 90).getLocation(),
                false
            );

        enhancedMapTiles.add(horizontalMawtrap1);

        // =====================================
        // MUD TILES
        // =====================================

        // Mud Tile at X = 7
        MudTile mudTile1 = new MudTile(
            getMapTile(7, 97).getLocation()
        );

        enhancedMapTiles.add(mudTile1);

        // Mud Tile at X = 9
        MudTile mudTile2 = new MudTile(
            getMapTile(9, 97).getLocation()
        );

        enhancedMapTiles.add(mudTile2);

        // Mud Tile at X = 11
        MudTile mudTile3 = new MudTile(
            getMapTile(11, 97).getLocation()
        );

        enhancedMapTiles.add(mudTile3);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        return npcs;
    }
}
