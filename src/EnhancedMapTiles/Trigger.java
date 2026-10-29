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
public class Trigger extends EnhancedMapTile {
    int id;
    boolean triggered = false;
    Map map;
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
        }
    }
    @Override
    public void initialize() {
        super.initialize();
    }
    
    
}
