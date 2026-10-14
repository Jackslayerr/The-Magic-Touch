
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
import Maps.LevelThreeMap;
import Players.Cat;
import Engine.Key;
import Engine.Keyboard;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class PlayLevelScreen extends Screen implements PlayerListener {

    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected BufferedImage backgroundImage;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;

    protected int currentLevel = 1;
    protected static int totalLevels = 3;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {
        this.map = level;
        this.currentLevel = screenCoordinator.getDestinationLevel();
        this.backgroundImage = ImageLoader.load(backgroundImageFile);

        this.player = new Cat(
            map.getPlayerStartPosition().x,
            map.getPlayerStartPosition().y
        );

        this.player.setMap(map);
        this.player.addListener(this);
        this.player.setCurrentLevel(currentLevel);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    @Override
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

    private void jumpToLevel(int levelNumber) {
        screenCoordinator.setDestinationLevel(levelNumber);
        screenCoordinator.setGameState(GameState.LEVEL);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        switch (playLevelScreenState) {

            case RUNNING:
                graphicsHandler.drawImage(
                    backgroundImage, 0, 0, 800, 605
                );

                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                drawHealthBar(graphicsHandler);
                break;

            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);

                if (Keyboard.isKeyDown(Key.SPACE)) {
                    if (currentLevel < totalLevels) {
                        screenCoordinator.setDestinationLevel(currentLevel + 1);
                        screenCoordinator.setGameState(GameState.CUTSCENE);
                    } else {
                        screenCoordinator.setGameState(GameState.CREDITS);
                    }
                }
                break;

            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

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

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, barWidth, barHeight);

        if (health <= 10) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        g.fillRect(x, y, filledWidth, barHeight);

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

    private enum PlayLevelScreenState {
        RUNNING,
        LEVEL_COMPLETED,
        LEVEL_LOSE
    }

    private Map chooseLevel(int levelNumber) {
        switch (levelNumber) {
            case 2:
                return new LevelTwoMap();
            case 3:
                return new LevelThreeMap();
            default:
                return new LevelOneMap();
        }
    }

    private String chooseBackground(int levelNumber) {
        switch (levelNumber) {
            case 2:
                return "game-background-level2.png";
            case 3:
                return "game-background-level3.png";
            default:
                return "game-background-level1.png";
        }
    }

    public void loadLevel(int levelNumber) {
        currentLevel = levelNumber;
        screenCoordinator.setDestinationLevel(levelNumber);
        initialize(chooseLevel(levelNumber), chooseBackground(levelNumber));
    }
}
