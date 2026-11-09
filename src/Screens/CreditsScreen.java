
package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

// This class is for the credits screen
public class CreditsScreen extends Screen {

    protected ScreenCoordinator screenCoordinator;
    protected KeyLocker keyLocker = new KeyLocker();
    protected BufferedImage backgroundImage;

    protected SpriteFont returnInstructionsLabel;
    protected Font pixelFont;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        initialize(null, "credits-screen.png");
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {

        backgroundImage = ImageLoader.load(backgroundImageFile);

        // Load Pixel Operator font
        try {
            File fontFile = new File(
                "Resources/fonts/PixelOperator-Bold.ttf"
            );

            pixelFont = Font.createFont(
                Font.TRUETYPE_FONT,
                fontFile
            ).deriveFont(Font.BOLD, 12f);

            System.out.println("Pixel Operator font loaded!");

        } catch (Exception e) {
            System.out.println(
                "Could not load Pixel Operator font: " + e.getMessage()
            );

            pixelFont = new Font(
                "Monospaced",
                Font.BOLD,
                24
            );
        }

        // Return to menu instruction
        returnInstructionsLabel = new SpriteFont(
            "PRESS SPACE TO RETURN TO MENU",
            320,
            430,
            pixelFont,
            Color.white
        );

        returnInstructionsLabel.setOutlineColor(Color.black);
        returnInstructionsLabel.setOutlineThickness(3);

        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // Return to main menu when SPACE is pressed
        if (!keyLocker.isKeyLocked(Key.SPACE)
                && Keyboard.isKeyDown(Key.SPACE)) {

            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {

        graphicsHandler.drawImage(
            backgroundImage,
            0,
            0,
            794,
            576
        );

        // Disable text smoothing for pixel-art appearance
        Graphics2D g = graphicsHandler.getGraphics();

        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
        );

        // Draw return instructions
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
