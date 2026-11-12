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
public class VinePlatform extends EnhancedMapTile {
    private int timer = 0;
    private static int deleteTime = 400;

    public VinePlatform(Point location, String spriteFile) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(spriteFile), 16, 16), TileType.JUMP_THROUGH_PLATFORM);
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
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(3)
                            .withBounds(0, 0, 16, 6)
                            .build()
                });
            }
        };
    
}}