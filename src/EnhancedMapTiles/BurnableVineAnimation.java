package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Level.MapEntityStatus;
import Utils.Point;

import java.util.HashMap;

// This class is for the vine platform tile
// when the player touches it, it will allow the player to stand on it
public class BurnableVineAnimation extends EnhancedMapTile {
    private int timer = 0;
    private static int deleteTime = 30;

    public BurnableVineAnimation(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("BurnableVine.png"), 32, 96), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        timer++;
        if (timer >= deleteTime) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                            .withScale(2)
                            .withBounds(0, 0, 32, 96)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 10)
                            .withScale(2)
                            .withBounds(0, 0, 32, 96)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 10)
                            .withScale(2)
                            .withBounds(0, 0, 32, 96)
                            .build()
                });
            }
        };
    
}}