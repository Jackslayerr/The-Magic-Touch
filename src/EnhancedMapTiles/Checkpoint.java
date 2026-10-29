package EnhancedMapTiles;

import java.awt.Color;
import java.util.ArrayList;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Utils.*;
import Level.*;
import Enemies.*;
import Level.Map;
import Level.MapTile;
// A Trigger invisible block that can be used to make things happen
public class Checkpoint extends EnhancedMapTile {
    boolean triggered = false;
    Map map;
    int checkPointIndex;
    public Checkpoint(Point location, int boundWidth, int boundHeight, Map map, int chekPointIndex) {
        super(location.x, location.y, new FrameBuilder(ImageLoader.load("transparent.png"))
              .withScale(3)
              .withBounds(0, 0, boundWidth, boundHeight)
              .build(), TileType.PASSABLE);
        this.map = map;
        this.checkPointIndex = chekPointIndex;
    }
    
    @Override
    public void update(Player player) {
        super.update(player);
        if (this.intersects(player) || this.touching(player)) {
            if (triggered == false) {
                CheckpointManager.setIndex(checkPointIndex);
                triggered = true;
            }
        }
        
    }
   
    @Override
    public void initialize() {
        super.initialize();
    }
    
    
}
