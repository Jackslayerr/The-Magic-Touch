package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Enemies.snowball;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

public class Vine extends EnhancedMapTile {
    private int timer = 0;
    private static int finishAnimation = 640;

    private Boolean platform1 = false;
    private Boolean platform2 = false;
    private Boolean platform3 = false;

    public Vine(Point location) {
        super(Math.round(location.x), Math.round(location.y), new SpriteSheet(ImageLoader.load("Vine.png"), 64, 144), TileType.PASSABLE);
        updateCurrentFrame();
    }

    @Override
    public void update(Player player) {
        super.update(player);
            timer++;
            if (timer >= 75 && !platform1){
                //build platform 1
                buildPlatform(1);
                platform1 = true;
            }
            if (timer >= 105 && !platform2){
                //build platform 2
                buildPlatform(2);
                platform2 = true;
            }
            if (timer >= 135 && !platform3){
                //build platform 3
                buildPlatform(3);
                platform3 = true;
            }

            if (timer >= finishAnimation) {
                VineSprout vineSprout = new VineSprout(this.getLocation().subtractY(-222).subtractX(-24));
                map.addEnhancedMapTile(vineSprout);
                this.mapEntityStatus = MapEntityStatus.REMOVED;
            }
    }

    public void buildPlatform(int platform) {
        // logic to build a platform
        int platformX;
        int platformY;
        if (platform == 1) {
            platformX = Math.round(getX() - 3);
            platformY = Math.round(getY() + 212);
        } else if (platform == 2) {
            platformX = Math.round(getX() + 78);
            platformY = Math.round(getY() + 148);
        } else if (platform == 3) {
            platformX = Math.round(getX() - 3);
            platformY = Math.round(getY() + 92);
                } else {
                        return;
        }



        // create platform
        VinePlatform vinePlatform = new VinePlatform(new Point(platformX, platformY), "VinePlatform.png");

        // add platform to the map for it to spawn in the level
        map.addEnhancedMapTile(vinePlatform);
    }



@Override
public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
    return new HashMap<String, Frame[]>() {{
        put("DEFAULT", new Frame[] {
            //Growing
            new FrameBuilder(spriteSheet.getSprite(0, 0), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 2), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 3), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 4), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 0), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 1), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),

            //Bud
            new FrameBuilder(spriteSheet.getSprite(1, 2), 20)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),

            //Sparkles
            new FrameBuilder(spriteSheet.getSprite(1, 3), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 4), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 3), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 4), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 3), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 4), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 3), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 4), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 3), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 4), 30)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),

            //Back to bud
            new FrameBuilder(spriteSheet.getSprite(1, 2), 180)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),

            //Descent
            new FrameBuilder(spriteSheet.getSprite(1, 1), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(1, 0), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 4), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 3), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 2), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build(),
            new FrameBuilder(spriteSheet.getSprite(0, 0), 10)
                    .withScale(2)
                    .withBounds(0, 0, 128, 128)
                    .build()
        });
    }};
}}