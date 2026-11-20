
package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Player;
import Utils.Point;

import java.awt.image.BufferedImage;
import java.util.HashMap;

// Vertical Mawtrap enemy
public class Mawtrap extends Enemy {

    private int hitBoxTimer = 0;

    private static final int FRAME_WIDTH = 32;
    private static final int FRAME_HEIGHT = 96;

    private static final int EMERGING_DAMAGE = 5;
    private static final int ATTACK_DAMAGE = 15;
    private static final int RETRACTING_DAMAGE = 5;

    private static final int HIDDEN_TIME = 5;
    private static final int EMERGE_TIME = 25;
    private static final int ATTACK_TIME = 180;
    private static final int RETRACT_TIME = 25;

    private final boolean facesRight;

    public Mawtrap(Point location, boolean facesRight) {
        super(
            location.x,
            location.y,
            new SpriteSheet(
                ImageLoader.load("Mawtrap.png"),
                FRAME_WIDTH,
                FRAME_HEIGHT
            ),
            "DEFAULT"
        );

        this.facesRight = facesRight;
        canBePushed = false;

        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();

        currentAnimationName = "DEFAULT";
        hitBoxTimer = 0;
    }

    @Override
    public void update(Player player) {

        // Start emerging after waiting
        if (hitBoxTimer > HIDDEN_TIME) {
            currentAnimationName = "HitBox";
        }

        // Finish the cycle and return underground
        if (hitBoxTimer > HIDDEN_TIME + EMERGE_TIME
                + ATTACK_TIME + RETRACT_TIME) {

            currentAnimationName = "DEFAULT";
            hitBoxTimer = 0;
        }

        hitBoxTimer++;

        super.update(player);
    }

    private Frame makeFrame(
        SpriteSheet spriteSheet,
        int frame,
        int duration
    ) {
        BufferedImage image = spriteSheet.getImage().getSubimage(
            frame * FRAME_WIDTH,
            0,
            FRAME_WIDTH,
            FRAME_HEIGHT
        );

        FrameBuilder builder = new FrameBuilder(image, duration)
            .withScale(2)
            .withBounds(6, 0, 20, 96);

        if (!facesRight) {
            builder.withImageEffect(ImageEffect.FLIP_HORIZONTAL);
        }

        return builder.build();
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{

            // Hidden
            put("DEFAULT", new Frame[] {
                makeFrame(spriteSheet, 6, 180)
            });

            // Emerging, attacking, and retracting
            put("HitBox", new Frame[] {

                // Emerging
                makeFrame(spriteSheet, 0, 5),
                makeFrame(spriteSheet, 1, 5),
                makeFrame(spriteSheet, 2, 5),
                makeFrame(spriteSheet, 3, 5),
                makeFrame(spriteSheet, 4, 5),

                // Fully extended
                makeFrame(spriteSheet, 5, ATTACK_TIME),

                // Retracting
                makeFrame(spriteSheet, 4, 5),
                makeFrame(spriteSheet, 3, 5),
                makeFrame(spriteSheet, 2, 5),
                makeFrame(spriteSheet, 1, 5),
                makeFrame(spriteSheet, 0, 5)
            });
        }};
    }

    @Override
    public int getDamage() {

        if (currentAnimationName.equals("DEFAULT")) {
            return 0;
        }

        int emergingEnd = HIDDEN_TIME + EMERGE_TIME;
        int attackingEnd = emergingEnd + ATTACK_TIME;
        int retractingEnd = attackingEnd + RETRACT_TIME;

        if (hitBoxTimer <= emergingEnd) {
            return EMERGING_DAMAGE;
        }

        if (hitBoxTimer <= attackingEnd) {
            return ATTACK_DAMAGE;
        }

        if (hitBoxTimer <= retractingEnd) {
            return RETRACTING_DAMAGE;
        }

        return 0;
    }
}
