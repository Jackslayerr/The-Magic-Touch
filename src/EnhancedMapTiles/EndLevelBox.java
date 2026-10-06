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
public class EndLevelBox extends EnhancedMapTile {
    private boolean opened = false;
    private int timer = 0;
    private static int delayTime = 240;

    public EndLevelBox(Point location, String spriteFile) {
        super(location.x, location.y - 16, new SpriteSheet(ImageLoader.load(spriteFile), 32, 32), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            opened = true;
            currentAnimationName = "OPENING";
            player.setFrozen(true);


            if (opened) {
                timer++;
                if (timer >= delayTime) {
                    player.setFrozen(false);
                    player.completeLevel();
                }
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("OPENING", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                        .withScale(2)
                        .withBounds(1, 1, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 50)
                        .withScale(2)
                        .withBounds(1, 1, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 5000)
                        .withScale(2)
                        .withBounds(1, 1, 32, 32)
                        .build()
            });

                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(2)
                            .withBounds(1, 1, 32, 32)
                            .build()
                });
            }
        };
    
}}