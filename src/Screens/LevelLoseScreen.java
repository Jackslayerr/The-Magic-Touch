package Screens;

import Engine.*;
import Level.Map;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

// This is the class for the level lose screen
public class LevelLoseScreen extends Screen {
    protected BufferedImage backgroundImage;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;
    protected BackgroundMusic backgroundMusic;

    public LevelLoseScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        //initialize(null, "you-lost.png"); This has to be stopped for the death music to work
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {
        backgroundImage = ImageLoader.load(backgroundImageFile);

        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);

        backgroundMusic = new BackgroundMusic();
        backgroundMusic.loadMusic("/Audio/Game_Over.wav");
        backgroundMusic.play();
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            stopJingle();
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            stopJingle();
            playLevelScreen.goBackToMenu();
        }
    }

    private void stopJingle() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(backgroundImage, 0, 0, 794, 576);
    }
}