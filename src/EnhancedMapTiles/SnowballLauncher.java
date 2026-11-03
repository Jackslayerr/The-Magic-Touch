package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Enemies.snowball;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

public class SnowballLauncher extends EnhancedMapTile {
    private int timer = 0;
    private static int shootAt = 165;

    private boolean right;

    public SnowballLauncher(Point location, Boolean right) {
        super(Math.round(location.x), Math.round(location.y), new SpriteSheet(ImageLoader.load("SnowballLauncher.png"), 128, 128), TileType.PASSABLE);
        this.right = right;

        if (right) {
            for (Frame[] animation : animations.values()) {
                for (Frame frame : animation) {
                    frame.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
                }
            }
        }
        updateCurrentFrame();
    }

    @Override
    public void update(Player player) {
        super.update(player);
            timer++;
            if (timer >= shootAt){
                //shooting logic
                if (this.right) {
                    // shoot to the right
                    shootSnowball(true);

                } else {
                    // shoot to the left
                    shootSnowball(false);
                }
                timer = -60;
            }
    }

    public void shootSnowball(boolean Right) {
        // logic to shoot a snowball
        int snowballX;
        if (Right) {
            snowballX = Math.round(getX() + 178);
        } else {
            snowballX = Math.round(getX() + 30);
        }

        int snowballY = Math.round(getY() + 105);

        // create Snowball enemy
        snowball snowball = new snowball(new Point(snowballX, snowballY), Right, 2.5f);

        // add Snowball enemy to the map for it to spawn in the level
        map.addEnemy(snowball);
    }



    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 50)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 35)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 35)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 15)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 4), 15)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 15)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 35)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 25)
                            .withScale(2)
                            .withBounds(0, 0, 128, 128)
                            .build()
                });
            }
        };}}