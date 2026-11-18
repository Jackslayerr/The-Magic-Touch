package EnhancedMapTiles;

import java.awt.Color;
import java.util.ArrayList;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Utils.*;
import Level.*;
import Screens.PlayLevelScreen;
import Enemies.*;
// A Trigger invisible block that can be used to make things happen
public class Trigger extends EnhancedMapTile {
    int id;
    boolean triggered = false;
    Map map;
    ArrayList<EnhancedMapTile> EMTs = new ArrayList<EnhancedMapTile>();
    public Trigger(Point location, int id, int boundx, int boundy, int boundWidth, int boundHeight, Map map) {
        super(location.x, location.y, new FrameBuilder(ImageLoader.load("transparent.png"))
              .withScale(3)
              .withBounds(boundx, boundy, boundWidth, boundHeight)
              .build(), TileType.PASSABLE);
        this.id = id;
        this.map = map;
    }
    
    @Override
    public void update(Player player) {
        super.update(player);
        if (this.intersects(player) || this.touching(player)) {
            if (triggered == false) {
                doSomething(player);
            }
        }
        
    }
    public void doSomething(Player player) {
        if (id == 1) {
            Puzzle.addTiles(120, 120, 9, 11, 21, map);
            triggered = true;
        } else if (id == 11) {
            Puzzle.getRidOfTiles(40, 40, 187, 196, map);
            triggered = true;
        } else if (id == 12) {
            Puzzle.getRidOfTiles(78, 78, 188, 196, map);
            triggered = true;
        } else if (id == 13) {
            Puzzle.getRidOfTiles(118, 118, 184, 196, map);
            triggered = true;
        } else if (id == 14) {
            // Speed Ring
            Puzzle.getRidOfTiles(64, 64, 144, 146, map);
            Puzzle.getRidOfTiles(160, 160, 188, 196, map);
            PlayLevelScreen.timerRunning = false;
            PlayLevelScreen.timerFrames = 420;
            player.setSpeedMultiplier(1.0f);
            triggered = true;
            EMTs = map.getEnhancedMapTiles();
            for (EnhancedMapTile emt: EMTs) {
                if (emt instanceof SpeedRing) {
                    emt.setMapEntityStatus(MapEntityStatus.REMOVED);
                }
            }
        } else if (id == 15) {
            DragonEnemy dragonEnemy = new DragonEnemy(map.getMapTile(177, 190).getLocation().subtractY(-200), Direction.LEFT);
            map.addEnemy(dragonEnemy);
            triggered = true;
        } else if (id == 2) {
            PenguinEnemy penguin5 = new PenguinEnemy(map.getMapTile(154, 17).getLocation().subtractY(0), Direction.LEFT);
            map.addEnemy(penguin5);

            PenguinEnemy penguin6 = new PenguinEnemy(map.getMapTile(167, 17).getLocation().subtractY(0), Direction.LEFT);
            map.addEnemy(penguin6);



            triggered = true;
        } else if (id == 3) {
            SnowballLauncher snowballLauncher = new SnowballLauncher(map.getMapTile(156, 22).getLocation().subtractY(0), true);
            map.addEnhancedMapTile(snowballLauncher);

            SnowballLauncher snowballLauncher3 = new SnowballLauncher(map.getMapTile(154, 22).getLocation().subtractX(8), true);
            map.addEnhancedMapTile(snowballLauncher3);

            SnowballLauncher snowballLauncher2 = new SnowballLauncher(map.getMapTile(152, 22).getLocation().subtractX(16), true);
            map.addEnhancedMapTile(snowballLauncher2);

            triggered = true;
        }
        
    }
    @Override
    public void initialize() {
        super.initialize();
    }
    
    
}
