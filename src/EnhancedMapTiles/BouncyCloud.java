package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;
import Level.*;

import java.util.HashMap;

// This class is for the end level gold box tile
// when the player touches it, it will tell the player that the level has been completed
public class BouncyCloud extends EnhancedMapTile {

    public BouncyCloud(Point location, String spriteFile) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(spriteFile), 48, 16), TileType.JUMP_THROUGH_PLATFORM);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (onTop(player)) {
            player.setPlayerState(PlayerState.JUMPING);
            player.playerJumping(1.7f);
        }
    }

    private boolean onTop(Player player) {
        boolean xOverlap = player.getBounds().getX2() > getBounds().getX1() && player.getBounds().getX1() < getBounds().getX2();
        float playerY = player.getBounds().getY2();
        float blockY = getBounds().getY1();
        boolean playerOnTop = (playerY >= blockY - 1) && (playerY <= blockY + 1);
        return playerOnTop && xOverlap;
    }    

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(3)
                            .withBounds(0, 0, 48, 16)
                            .build()
                });
            }
        };
    
}}