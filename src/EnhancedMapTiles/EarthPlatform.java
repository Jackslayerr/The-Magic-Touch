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

// This class is for the end level gold box tile
// when the player touches it, it will tell the player that the level has been completed
public class EarthPlatform extends EnhancedMapTile {
    private int timer = 0;
    private static int breakAt = 90;

    public EarthPlatform(Point location, String spriteFile) {
        super(Math.round(location.x), Math.round(location.y), new SpriteSheet(ImageLoader.load(spriteFile), 32, 32), TileType.JUMP_THROUGH_PLATFORM);
    }

    @Override
    public void update(Player player) {
        super.update(player);
            currentAnimationName = "Animating";
            timer++;
            if (timer >= breakAt){
                tileType = TileType.PASSABLE;
            }
    }



    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("Animating", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                        .withScale(2)
                        .withBounds(0, 0, 32, 6)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 70)
                        .withScale(2)
                        .withBounds(0, 0, 32, 6)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 100000)
                        .withScale(2)
                        .withBounds(0, 0, 0, 0)
                        .build()
            });

                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(2)
                            .withBounds(0, 0, 32, 6)
                            .build()
                });
            }
        };}}