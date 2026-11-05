
package Screens;

import Engine.*;
import Level.Map;
import Utils.CheckpointManager;

import java.awt.image.BufferedImage;
import java.io.File;

public class LevelClearedScreen extends Screen {

    protected BufferedImage backgroundImage;
    protected BackgroundMusic backgroundMusic;

    public LevelClearedScreen() {
        this(1);
    }

    public LevelClearedScreen(int levelNumber) {
        initialize(null, chooseBackground(levelNumber));
    }

    private String chooseBackground(int levelNumber) {
        switch (levelNumber) {
            case 1:
                CheckpointManager.setIndex(2);
                return "level-cleared-level1.png";

            case 2:
                CheckpointManager.setIndex(3);
                return "level-cleared-level2.png";

            case 3:
                return "level-cleared-level1.png";

            case 4:
                return "level-cleared-level1.png";

            case 5:
                return "level-cleared-level1.png";

            default:
                return "level-cleared-level1.png";
        }
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {
        File imageFile = new File("Resources/" + backgroundImageFile);

        if (imageFile.exists()) {
            backgroundImage = ImageLoader.load(backgroundImageFile);
        } else {
            backgroundImage = ImageLoader.load("level-cleared-level1.png");
        }

        backgroundMusic = new BackgroundMusic();
        backgroundMusic.loadMusic("/Audio/Wait_I_Did_It.wav");
        backgroundMusic.play();
    }

    @Override
    public void update() {
    }

    public void stopJingle() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(
            backgroundImage, 0, 0, 794, 576
        );
    }
}
