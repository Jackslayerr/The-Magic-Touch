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
import java.awt.Graphics2D;
import java.awt.Shape;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {

    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected BufferedImage backgroundImage;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;

    // Damage flash variables
    protected int damageFlashTimer = 0;
    protected int previousHealth = 12;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {

        // Define/setup map
        this.map = new LevelTwoMap();
        this.backgroundImage = ImageLoader.load("game-background-level2.png");
        // Setup player
        this.player = new Cat(
                map.getPlayerStartPosition().x,
                map.getPlayerStartPosition().y
        );

        this.player.setMap(map);
        this.player.addListener(this);

        // Reset damage flash
        damageFlashTimer = 0;
        previousHealth = player.getHealth();

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    public void update() {

        switch (playLevelScreenState) {

            case RUNNING:

                player.update();
                map.update(player);

                // Count down the red flash timer
                if (damageFlashTimer > 0) {
                    damageFlashTimer--;
                }

                // Detect when the player loses health
                if (player.getHealth() < previousHealth) {
                    damageFlashTimer = 20;
                }

                // Remember current health
                previousHealth = player.getHealth();

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

                // Quick red flash when damaged
                if (damageFlashTimer > 0) {

                    graphicsHandler.drawRectangle(
                            4, 4, 792, 597,
                            new Color(255, 0, 0, 200),
                            12 
                    );
                }

                break;

            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;

            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    // Draw the player's health using quarter hearts
    private void drawHealth(GraphicsHandler graphicsHandler) {

        Graphics2D g = graphicsHandler.getGraphics();

        int health = player.getHealth();

        Font heartFont = new Font("Arial", Font.PLAIN, 56);

        for (int i = 0; i < 5; i++) {

            int x = 20 + (i * 35);
            int y = 20;

            // Health remaining in this heart: 0 to 4
            int heartHealth = Math.max(0, Math.min(4, health));

            if (heartHealth == 0) {

                

            } else {

                // Save original drawing boundary
                Shape oldClip = g.getClip();

                // Calculate the filled portion
                int fillWidth = (int) (35 * (heartHealth / 4.0));

                // Restrict drawing to the filled portion
                g.setClip(x, y - 20, fillWidth, 60);

                // Draw colored heart
                graphicsHandler.drawString(
                        "♥",
                        x,
                        y + 25,
                        heartFont,
                        Color.RED
                );

                // Restore original drawing boundary
                g.setClip(oldClip);
            }

            health -= 4;
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