package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Point;

import java.util.HashMap;

public class Explosion extends Enemy {
    private int animationFrames = 30; // Duration of explosion before disappearing

    public Explosion(Point location) {
        // Uses the sheep sprite sheet
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("S.H.E.E.P.Enemy.png"), 33, 24), "EXPLODE");
        this.initialize();
    }

    @Override
    public void update(Player player) {
        super.update(player);

        // Remove explosion from map once the timer finishes
        if (animationFrames <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
        animationFrames--;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("EXPLODE", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 30)
                            .withScale(8) // Scale up the explosion huge
                            .withBounds(2, 2, 28, 20) // Active damage hitbox for the explosion
                            .build()
            });
        }};
    }
}