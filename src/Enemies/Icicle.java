package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Player;
import Utils.Point;

import java.util.HashMap;

// This class is for the icicle enemy
public class Icicle extends Enemy {


    public Icicle(Point location) {
        super(
            location.x,
            location.y,
            new SpriteSheet(ImageLoader.load("Icicle.png"), 16, 16),
            "IDLE"
        );

        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        currentAnimationName = "IDLE";
    }

    @Override
    public void update(Player player) {

        super.update(player);
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("IDLE", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0), 8)
                .withScale(3)
                .withBounds(4,4,8,12)
                .build()
            });
        
        }};
    }
}
