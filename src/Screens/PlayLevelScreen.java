package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ImageLoader;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.LevelOneMap;
import Maps.LevelTwoMap;
import Maps.SprintOneTestMap;
import Players.Cat;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {

    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected BufferedImage backgroundImage;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {

        // Define/setup map
        this.map = new LevelTwoMap();
        this.backgroundImage = ImageLoader.load("game-background-level1.png");

        // Setup player
        this.player = new Cat(
                map.getPlayerStartPosition().x,
                map.getPlayerStartPosition().y
        );

        this.player.setMap(map);
        this.player.addListener(this);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    public void update() {

        // Based on screen state, perform specific actions
        switch (playLevelScreenState) {

            case RUNNING:
                player.update();
                map.update(player);
                break;

            case LEVEL_COMPLETED:
                levelClearedScreen.update();
                break;

            case LEVEL_LOSE:
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {

        // Based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {

            case RUNNING:

                // Draw background first
                graphicsHandler.drawImage(
                        backgroundImage,
                        0, 0, 800, 605
                );

                // Draw map
                map.draw(graphicsHandler);

                // Draw player
                player.draw(graphicsHandler);

                // Draw health hearts
                drawHealth(graphicsHandler);

                break;

            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;

            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    // Draw the player's health as red hearts
    private void drawHealth(GraphicsHandler graphicsHandler) {

        int health = player.getHealth();

        for (int i = 0; i < health; i++) {

            int x = 20 + (i * 35);
            int y = 20;

            graphicsHandler.drawString(
                    "♥",
                    x,
                    y + 25,
                    new Font("Arial", Font.PLAIN, 56),
                    Color.RED
            );
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {

        if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
    }

    @Override
    public void onDeath() {

        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING,
        LEVEL_COMPLETED,
        LEVEL_LOSE
    }
}