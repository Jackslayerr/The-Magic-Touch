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
import Maps.LevelThreeMap;
import Maps.LevelTwoMap;
import Players.Cat;
import Engine.Key;
import Engine.Keyboard;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;


// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {


    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected BufferedImage backgroundImage;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;

    protected int currentLevel = 1;
    protected static int totalLevels = 2;



    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize(Map level, String backgroundImageFile) {

        // Define/setup map
        this.map = level;
        this.backgroundImage = ImageLoader.load(backgroundImageFile);
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

                // Draw health bar
                drawHealthBar(graphicsHandler);

                

                break;

            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                if (Keyboard.isKeyDown(Key.SPACE)) {
                    loadLevel(currentLevel +1);
                }
                break;

            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    
    // Draw a simple health bar
    private void drawHealthBar(GraphicsHandler graphicsHandler) {
        Graphics2D g = graphicsHandler.getGraphics();

        int x = 20;
        int y = 20;
        int barWidth = 220;
        int barHeight = 18;

        int health = Math.max(0, Math.min(
                player.getMaxHealth(),
                player.getHealth()
        ));

        float healthPercent =
                (float) health / player.getMaxHealth();

        int filledWidth = Math.round(barWidth * healthPercent);

        // Dark background for missing health
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, barWidth, barHeight);

        // Green normally; red at 10 health or lower
        if (health <= 10) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        // Draw remaining health
        g.fillRect(x, y, filledWidth, barHeight);

        // White border
        g.setColor(Color.WHITE);
        g.drawRect(x, y, barWidth, barHeight);
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
        loadLevel(currentLevel);
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

    private Map chooseLevel(int levelNumber){
        switch (levelNumber) {
            case 2: return new LevelTwoMap();
            case 3: return new LevelThreeMap();
            default: return new LevelOneMap();
        }
    }

    private String chooseBackground(int levelNumber){
        switch (levelNumber) {
            case 2: return "game-background-level2.png";
            case 3: return "game-background-level3.png";
            default: return "game-background-level1.png";
        }
    }

    public void loadLevel(int levelNumber) {
        currentLevel = levelNumber;
        initialize(chooseLevel(levelNumber), chooseBackground(levelNumber));
        player.setCurrentLevel(levelNumber);
    }
}