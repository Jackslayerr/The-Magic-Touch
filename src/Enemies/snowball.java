package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Utils.*;

public class snowball extends Fireball {
    public snowball(Point point) {
        super(point, -4, 120, false, Direction.LEFT, "snowball.png", 16, 16);
    }
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("LEFT_LAVA", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(8)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(0, 0, 16, 16)
                            .build()
            });
            put("RIGHT_LAVA", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(8)
                            .withBounds(0, 0, 16, 16)
                            .build()
            });
        }};
    }
}
