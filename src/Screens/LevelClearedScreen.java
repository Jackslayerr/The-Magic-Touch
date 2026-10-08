package Screens;

import Engine.*;
import Level.Map;
import java.awt.*;
import java.awt.image.BufferedImage;

// This class is for the level cleared screen
public class LevelClearedScreen extends Screen {
    protected BufferedImage backgroundImage;

    public LevelClearedScreen() {
        initialize(null, "level-cleared-level1.png");
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {
        backgroundImage = ImageLoader.load(backgroundImageFile);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(backgroundImage, 0, 0, 794, 576);
    }
}