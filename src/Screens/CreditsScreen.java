package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
//import Level.Map;
//import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    //protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected BufferedImage backgroundImage;
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont returnInstructionsLabel;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        backgroundImage = ImageLoader.load("credits-screen.png");
        // setup graphics on screen (background map, spritefont text)
        //background = new TitleScreenMap();
        //background.setAdjustCamera(false);
        //creditsLabel = new SpriteFont("Credits", 15, 7, "Times New Roman", 30, Color.white);
        //createdByLabel = new SpriteFont("Created by Alex Thimineur", 130, 121, "Times New Roman", 20, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 325, 430, "Times New Roman", 10, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        //background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(backgroundImage, 0, 0, 794, 576);

        //creditsLabel.draw(graphicsHandler);
        //createdByLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
