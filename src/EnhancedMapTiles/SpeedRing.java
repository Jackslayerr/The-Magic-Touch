package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Screens.PlayLevelScreen;
import Utils.Point;

import java.util.HashMap;

// This class is for the speed ring tile
// when the player touches it, it will increase the player's speed
public class SpeedRing extends EnhancedMapTile {

    public SpeedRing(Point location) {
        super(location.x, location.y - 16, new SpriteSheet(ImageLoader.load("SpeedRing.png"), 48, 64), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            player.setSpeedMultiplier(2.0f);
            PlayLevelScreen.timerRunning = true;
            currentAnimationName = "CHECKED";
        }
        if (PlayLevelScreen.timerRunning == false) {
            currentAnimationName = "DEFAULT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                            .withScale(1.5f)
                            .withBounds(0, 0, 48, 64)
                            .build()
                });
                put("CHECKED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 0)
                            .withScale(1.5f)
                            .withBounds(0, 0, 48, 64)
                            .build()
                });
            }
        };
    
}}