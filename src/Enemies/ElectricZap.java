package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Player;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

// This class is for the icicle enemy
public class ElectricZap extends Enemy {
    private int hitBoxTimer = 0;

    public ElectricZap(Point location) {
        super(
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("ElectricBar.png"), 32, 96),
            "DEFAULT"
        );
        canBePushed = false;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        currentAnimationName = "DEFAULT";
    }

    @Override
    public void update(Player player) {
        if (hitBoxTimer > 180) {
            currentAnimationName = "HitBox";
        }
        if (hitBoxTimer > 410) {
            currentAnimationName = "DEFAULT";
            hitBoxTimer = 0;
        }
        hitBoxTimer++;
        super.update(player);
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("HitBox", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,1), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,2), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,3), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,4), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,5), 180)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,4), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,3), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,2), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,1), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),
                new FrameBuilder(spriteSheet.getSprite(0,0), 5)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),


            });
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,6), 180)
                .withScale(2)
                .withBounds(6,0,20,96)
                .build(),


            });
        }};
    }

    // spike deal 5 damage to the player
    @Override
    public int getDamage() {
        if (currentAnimationName.equals("HitBox")) {
            return 5;
        } else {
            return 0;
        }
    }
}
