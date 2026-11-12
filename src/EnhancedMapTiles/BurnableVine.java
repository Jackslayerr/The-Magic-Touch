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

public class BurnableVine extends EnhancedMapTile {

    public BurnableVine(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("BurnableVine.png"), 32, 96), TileType.NOT_PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(2)
                            .withBounds(0, 0, 32, 96)
                            .build()
                });
            }
        };
    
}}