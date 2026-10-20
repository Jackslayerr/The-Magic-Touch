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
// A Trigger invisible block that can be used to make things happen
public class Trigger extends EnhancedMapTile {
    int id;
    boolean triggered = false;
    public Trigger(Point location, int id) {
        super(location.x, location.y, new FrameBuilder(ImageLoader.load("transparent.png"))
              .withScale(3)
              .withBounds(0, 0, 16, 16)
              .build(), TileType.NOT_PASSABLE);
        this.id = id;
    }
    
    @Override
    public void update(Player player) {
        super.update(player);
        if (this.intersects(player) || this.touching(player)) {
            if (triggered == false) {
                doSomething();
            }
        }
        
    }
    public void doSomething() {
        if (id == 1) {
            System.out.println("Hey");
            triggered = true;
        }
    }
    @Override
    public void initialize() {
        super.initialize();
    }
    
    
}
