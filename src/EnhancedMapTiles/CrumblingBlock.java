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
public class CrumblingBlock extends EnhancedMapTile {
    private boolean opened = false;
    private int timer = 0;
    private static int breakAt = 90;
    private static int respawnAt = 350;

    public CrumblingBlock(Point location, String spriteFile) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(spriteFile), 16, 16), TileType.NOT_PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (!opened && onTop(player)) {
            opened = true;
            timer = 0;
            currentAnimationName = "Animating";
        }

        if (opened) {
            timer++;
            if (timer >= breakAt){
                tileType = TileType.PASSABLE;
            }
            if (timer >= respawnAt) {
                opened = false;
                currentAnimationName = "DEFAULT";
                timer = 0;
                tileType = TileType.NOT_PASSABLE;
            }
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
            put("Animating", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                        .withScale(3)
                        .withBounds(0, 0, 16, 6)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 70)
                        .withScale(3)
                        .withBounds(0, 0, 16, 6)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 240)
                        .withScale(3)
                        .withBounds(0, 0, 0, 0)
                        .build()
            });

                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                            .withScale(3)
                            .withBounds(0, 0, 16, 6)
                            .build()
                });
            }
        };
    
}}