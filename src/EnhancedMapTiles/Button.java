package EnhancedMapTiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.*;
import Level.*;
import Enemies.*;
// A Button that can be used to make things happen
public class Button extends EnhancedMapTile {
    int id;
    int x1;
    int x2;
    int y1;
    int y2;
    boolean triggered = false;
    Map map;
    public Button(Point location, int x1, int x2, int y1, int y2, Map map, String spriteFile) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(spriteFile), 16, 16), TileType.PASSABLE);
          this.x1 = x1;
          this.x2 = x2;
          this.y1 = y1;
          this.y2 = y2;
        this.map = map;
    }
    
    @Override
    public void update(Player player) {
        super.update(player);
        if (this.intersects(player) || this.touching(player)) {
            if (triggered == false) {
                currentAnimationName = "PRESSED";
                doSomething();
            }
        }
        
    }

    public void doSomething() {
        Puzzle.getRidOfTiles(x1, x2, y1, y2, map);
        triggered = true;
    }

    @Override
    public void initialize() {
        super.initialize();
    }
    
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("PRESSED", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1), 0)
                        .withScale(3)
                        .withBounds(0, 0, 16, 5)
                        .build(),
            });

                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                            .withScale(3)
                            .withBounds(0, 0, 16, 5)
                            .build()
                });
            }
        };
    
}
}
