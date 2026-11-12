package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

// This class is for the vine sprout tile
public class VineSprout extends EnhancedMapTile {

    public VineSprout(Point location) {
        super(Math.round(location.x), Math.round(location.y), new SpriteSheet(ImageLoader.load("VineSprout.png"), 32, 32), TileType.NOT_PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
    }



    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 10)
                            .withScale(2)
                            .withBounds(0, 13, 32, 26)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                            .withScale(2)
                            .withBounds(0, 13, 32, 26)
                            .build()
                });
            }
        };}}