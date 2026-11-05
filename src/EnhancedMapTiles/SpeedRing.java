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

// This class is for the speed ring tile
// when the player touches it, it will increase the player's speed
public class SpeedRing extends EnhancedMapTile {
    private boolean activated = false;
    private int timer = 0;
    private static int speedBoostTime = 240;

    public SpeedRing(Point location) {
        super(location.x, location.y - 16, new SpriteSheet(ImageLoader.load("SpeedRing.png"), 16, 16), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            activated = true;
        }
        
        if (activated) {
            if (timer == 0) {
                player.setSpeedMultiplier(2.0f);
            }

            timer++;

            if (timer >= speedBoostTime) {
                player.setSpeedMultiplier(1.0f);
                activated = false;
                timer = 0;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(2)
                            .withBounds(0, 0, 16, 16)
                            .build()
                });
            }
        };
    
}}