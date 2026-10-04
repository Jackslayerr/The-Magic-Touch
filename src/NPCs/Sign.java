package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Point;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

// This class represents the sign NPC
public class Sign extends NPC {

    private boolean wasEPressed = false;
    private boolean playerNearby = false;

    public Sign(Point location, String text) {
        super(
                location.x,
                location.y,
                new SpriteSheet(ImageLoader.load("sign.png"), 24, 24),
                "TAIL_DOWN"
        );

        isInteractable = true;
        talkedToTime = -1;

        textbox.setText(text);

        textboxOffsetX = -4;
        textboxOffsetY = -120;
    }

    @Override
    public void update(Player player) {
        super.update(player);

        if (talkedTo) {
            currentAnimationName = "TAIL_UP";
        } else {
            currentAnimationName = "TAIL_DOWN";
        }
    }

    @Override
    public void checkTalkedTo(Player player) {

        playerNearby = intersects(player);

        boolean ePressed = Keyboard.isKeyDown(Key.E);

        if (playerNearby && ePressed && !wasEPressed) {
            talkedTo = !talkedTo;
        }

        if (!playerNearby) {
            talkedTo = false;
        }

        wasEPressed = ePressed;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("TAIL_DOWN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("TAIL_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        if (talkedTo) {
            textbox.draw(graphicsHandler);
        }

        if (playerNearby && !talkedTo) {
            graphicsHandler.drawString(
                    "(E)",
                    (int) getCalibratedXLocation() + 12,
                    (int) getCalibratedYLocation() - 8,
                    new Font("Arial", Font.PLAIN, 12),
                    Color.WHITE
            );
        }
    }
}