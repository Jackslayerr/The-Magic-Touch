
package Screens;

import Engine.*;
import Level.Map;
import Game.GameState;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0;
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();
    protected BufferedImage backgroundImage;

    protected Font pixelFont;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {

        // Load Pixel Operator font
        try {
            File fontFile = new File(
                "Resources/fonts/PixelOperator-Bold.ttf"
            );

            pixelFont = Font.createFont(
                Font.TRUETYPE_FONT,
                fontFile
            ).deriveFont(Font.BOLD, 24f);

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

        // PLAY GAME text
        playGame = new SpriteFont(
            "PLAY GAME",
            345,
            270,
            pixelFont,
            Color.white
        );

        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);

        // CREDITS text
        credits = new SpriteFont(
            "CREDITS",
            355,
            322,
            pixelFont,
            Color.white
        );

        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);

        backgroundImage = ImageLoader.load(backgroundImageFile);

        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.ENTER);
    }

    public void update() {

        if (Keyboard.isKeyDown(Key.DOWN) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.UP) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        if (currentMenuItemHovered > 1) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 1;
        }

        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));

            pointerLocationX = 320;
            pointerLocationY = 273;

        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(255, 215, 0));

            pointerLocationX = 330;
            pointerLocationY = 327;
        }

        if (Keyboard.isKeyUp(Key.ENTER)) {
            keyLocker.unlockKey(Key.ENTER);
        }

        if (!keyLocker.isKeyLocked(Key.ENTER)
                && Keyboard.isKeyDown(Key.ENTER)) {

            menuItemSelected = currentMenuItemHovered;

            if (menuItemSelected == 0) {
                screenCoordinator.setGameState(GameState.CUTSCENE);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            }
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

        // Disable text smoothing for crisp pixel font
        Graphics2D g = graphicsHandler.getGraphics();

        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
        );

        // Draw menu text
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);

        // Draw selection indicator
        graphicsHandler.drawFilledRectangleWithBorder(
            pointerLocationX,
            pointerLocationY,
            20,
            20,
            new Color(49, 207, 240),
            Color.black,
            2
        );
    }
}
