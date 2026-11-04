package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Utils.*;

public class snowball extends Fireball {
    private final float scale;

    public snowball(Point point, Boolean right, float scale) {
        super(point, right ? 4 : -4, 120, false, right ? Direction.RIGHT : Direction.LEFT, "snowball.png", 16, 16);
        this.scale = scale;

        // loadAnimations() already ran inside super() while scale was still 0,
        // so rebuild the frames now that the real scale is known
        this.animations = loadAnimations(new SpriteSheet(ImageLoader.load("snowball.png"), 16, 16));
        updateCurrentFrame();
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("LEFT_LAVA", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(snowball.this.scale)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(0, 0, 16, 16)
                            .build()
            });
            put("RIGHT_LAVA", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(snowball.this.scale)
                            .withBounds(0, 0, 16, 16)
                            .build()
            });
        }};
    }
}
