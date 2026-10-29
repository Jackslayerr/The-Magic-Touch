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
// A Trigger invisible block that can be used to make things happen
public class Checkpoint extends EnhancedMapTile {
    boolean triggered = false;
    Map map;
    int checkPointIndex;
    public Checkpoint(Point location, int boundWidth, int boundHeight, Map map, int checkPointIndex) {
        super(location.x, location.y,
            new SpriteSheet(ImageLoader.load("Checkpoint.png"), 138, 248),
            TileType.PASSABLE);
        this.map = map;
        this.checkPointIndex = checkPointIndex;
    }
    
    @Override
    public void update(Player player) {
        
        super.update(player);
        if (this.intersects(player) || this.touching(player)) {
            if (triggered == false) {
                CheckpointManager.setIndex(checkPointIndex);
                triggered = true;
                currentAnimationName = "COLLECTED";

            }
        }
        
    }
   
    @Override
    public void initialize() {
        super.initialize();
    }
    
        @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("COLLECTED", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 30)
                        .withScale(0.5f)
                        .withBounds(0, 0, 138, 248)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 30)
                        .withScale(0.5f)
                        .withBounds(0, 0, 138, 248)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 30)
                        .withScale(0.5f)
                        .withBounds(0, 0, 138, 248)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 30)
                        .withScale(0.5f)
                        .withBounds(0, 0, 138, 248)
                        .build()
            });

                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 10)
                            .withScale(0.5f)
                            .withBounds(0, 0, 138, 248)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                            .withScale(0.5f)
                            .withBounds(0, 0, 138, 248)
                            .build()
                });
            }
        };
    
}}
