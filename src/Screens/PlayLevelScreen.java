package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.LevelOneMap;
import Maps.LevelTwoMap;
import Maps.MovementTestMap;
import Maps.LevelThreeMap;
import Players.Cat;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

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

    // Background music
    private BackgroundMusic backgroundMusic;

    // Red screen flash when player takes damage
    private int damageFlashTimer = 0;
    private static final int DAMAGE_FLASH_DURATION = 12;

    // Health bar animation
    private int healthBarAnimationTimer = 0;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {
        this.map = level;
        this.currentLevel = screenCoordinator.getDestinationLevel();
        this.backgroundImage = ImageLoader.load(backgroundImageFile);

        // Give the background image to the camera for parallax scrolling
        map.getCamera().setBackgroundImage(backgroundImage);

        this.player = new Cat(
                map.getPlayerStartPosition().x,
                map.getPlayerStartPosition().y
        );

        this.player.setMap(map);
        this.player.addListener(this);
        this.player.setCurrentLevel(currentLevel);

        // Reset damage and health animations whenever a level loads
        damageFlashTimer = 0;
        healthBarAnimationTimer = 0;

        // NOTE: levelClearedScreen is created in onLevelCompleted(), not here.
        // Its constructor sets the next level's checkpoint index, so building it at
        // level load would overwrite checkpoints (e.g. the level 1 checkpoint).
        levelClearedScreen = null;
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        // Background music
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }

        backgroundMusic = new BackgroundMusic();

        switch (currentLevel) {
            case 1:
                backgroundMusic.loadMusic("/Audio/Preservation_of_Artifacts.wav");
                break;
            case 2:
                backgroundMusic.loadMusic("/Audio/Im_Turning_Blue.wav");
                break;
            case 3:
                backgroundMusic.loadMusic("/Audio/SHEEP_ANTHEM.wav");
                break;
            default:
                backgroundMusic.loadMusic("/Audio/The_Magic_Touch.wav");
                break;
        }

        backgroundMusic.play();
        backgroundMusic.loop();
    }

    @Override
    public void update() {
        switch (playLevelScreenState) {

            case RUNNING:
                int healthBeforeUpdate = player.getHealth();

                player.update();
                map.update(player);

                int healthAfterUpdate = player.getHealth();

                // Start the red flash whenever health decreases
                if (healthAfterUpdate < healthBeforeUpdate) {
                    damageFlashTimer = DAMAGE_FLASH_DURATION;
                } else if (damageFlashTimer > 0) {
                    damageFlashTimer--;
                }

                // Update health bar animation
                healthBarAnimationTimer++;

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

                // Camera now draws the scrolling background
                map.draw(graphicsHandler);

                player.draw(graphicsHandler);

                // Draw health HUD above the game world
                drawHealthBar(graphicsHandler);

                // Draw existing damage flash over gameplay
                drawDamageFlash(graphicsHandler);
                break;

            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);

                if (Keyboard.isKeyDown(Key.SPACE)) {
                    levelClearedScreen.stopJingle();
                    //if (backgroundMusic != null) {
                    //    backgroundMusic.stop();
                   // }

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

    // ==========================================
    // DAMAGE SCREEN FLASH
    // ==========================================

    private void drawDamageFlash(GraphicsHandler graphicsHandler) {
        if (damageFlashTimer <= 0) {
            return;
        }

        Graphics2D g = graphicsHandler.getGraphics();

        int alpha = (int) (
                180.0 * damageFlashTimer / DAMAGE_FLASH_DURATION
        );

        g.setColor(new Color(255, 0, 0, alpha));

        int borderThickness = 22;
        int screenWidth = 800;
        int screenHeight = 605;

        // Top and bottom borders
        g.fillRect(0, 0, screenWidth, borderThickness);
        g.fillRect(
                0, screenHeight - borderThickness,
                screenWidth, borderThickness
        );

        // Left and right borders
        g.fillRect(0, 0, borderThickness, screenHeight);
        g.fillRect(
                screenWidth - borderThickness, 0,
                borderThickness, screenHeight
        );
    }

    // ==========================================
    // HEALTH BAR HUD
    // ==========================================

    private void drawHealthBar(GraphicsHandler graphicsHandler) {
        Graphics2D g = graphicsHandler.getGraphics();

        int health = Math.max(0, Math.min(
                player.getMaxHealth(),
                player.getHealth()
        ));

        int maxHealth = player.getMaxHealth();

        float healthPercent = (float) health / maxHealth;

        // HUD position
        int x = 20;
        int y = 18;

        // ----------------------------------
        // GOLD DECORATIVE FRAME
        // ----------------------------------

        g.setColor(new Color(54, 32, 77));
        g.fillRect(x, y, 370, 48);

        g.setColor(new Color(255, 207, 91));
        g.fillRect(x, y, 370, 3);
        g.fillRect(x, y + 45, 370, 3);
        g.fillRect(x, y, 3, 48);
        g.fillRect(x + 367, y, 3, 48);

        g.setColor(new Color(135, 85, 37));
        g.drawRect(x + 4, y + 4, 362, 40);

        // ----------------------------------
        // PIXEL HEART
        // ----------------------------------

        drawPixelHeart(g, x + 12, y + 12, 4);

        // ----------------------------------
        // HEALTH BAR FRAME
        // ----------------------------------

        int barX = x + 48;
        int barY = y + 11;
        int barWidth = 200;
        int barHeight = 20;

        g.setColor(new Color(24, 18, 36));
        g.fillRect(barX, barY, barWidth, barHeight);

        g.setColor(new Color(255, 207, 91));
        g.drawRect(
                barX - 2,
                barY - 2,
                barWidth + 4,
                barHeight + 4
        );

        // ----------------------------------
        // HEALTH BAR COLOR
        // ----------------------------------

        Color healthColor;

        if (health < 20) {

            // Critical health: pulsing red
            int pulse = (int) (
                    150 + 105 * (
                            0.5 + 0.5 *
                                    Math.sin(healthBarAnimationTimer * 0.18)
                    )
            );

            healthColor = new Color(255, 35, 35, pulse);

        } else if (damageFlashTimer > 0) {

            // Brief red flash after taking damage
            healthColor = Color.RED;

        } else {

            // Normal health: green
            healthColor = new Color(55, 210, 85);
        }

        // ----------------------------------
        // SEGMENTED HEALTH BAR
        // ----------------------------------

        int segments = 20;
        int segmentWidth = 9;
        int segmentGap = 1;

        int filledSegments = (int) Math.ceil(
                healthPercent * segments
        );

        for (int i = 0; i < segments; i++) {

            int segmentX = barX + 1 +
                    i * (segmentWidth + segmentGap);

            if (i < filledSegments) {
                g.setColor(healthColor);
            } else {
                g.setColor(new Color(65, 48, 65));
            }

            g.fillRect(
                    segmentX,
                    barY + 1,
                    segmentWidth,
                    barHeight - 2
            );

            // Small highlight on green segments
            if (i < filledSegments && health >= 20
                    && damageFlashTimer <= 0) {

                g.setColor(new Color(255, 255, 255, 65));

                g.fillRect(
                        segmentX,
                        barY + 1,
                        segmentWidth,
                        2
                );
            }
        }

        // ----------------------------------
        // NUMERIC HEALTH DISPLAY
        // ----------------------------------

        String healthText = health + " / " + maxHealth;

        Font healthFont = new Font(
                "Monospaced",
                Font.BOLD,
                16
        );

        Color numberColor = health < 20
                ? new Color(255, 95, 95)
                : Color.WHITE;

        graphicsHandler.drawStringWithOutline(
                healthText,
                x + 265,
                y + 31,
                healthFont,
                numberColor,
                Color.BLACK,
                2f
        );
    }

    // ==========================================
    // PIXEL ART HEART
    // ==========================================

    private void drawPixelHeart(Graphics2D g, int x, int y, int size) {

        int[][] heart = {
                {0, 1, 1, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}
        };

        for (int row = 0; row < heart.length; row++) {
            for (int col = 0; col < heart[row].length; col++) {

                if (heart[row][col] == 1) {

                    // Main red heart
                    g.setColor(new Color(220, 35, 55));

                    // Pink highlights
                    if (row == 1 && (col == 1 || col == 2)) {
                        g.setColor(new Color(255, 115, 135));
                    }

                    g.fillRect(
                            x + col * size,
                            y + row * size,
                            size,
                            size
                    );
                }
            }
        }
    }

    // ==========================================
    // LEVEL AND DEATH HANDLING
    // ==========================================

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
            levelClearedScreen = new LevelClearedScreen(currentLevel);
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
    }

    @Override
    public void onDeath() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {

            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
            playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
            levelLoseScreen.initialize(map, "you-lost.png");
        }
    }

    public void resetLevel() {
        loadLevel(currentLevel);
    }

    public void goBackToMenu() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }

        screenCoordinator.setGameState(GameState.MENU);
    }

    private enum PlayLevelScreenState {
        RUNNING,
        LEVEL_COMPLETED,
        LEVEL_LOSE
    }

    // ==========================================
    // LEVEL LOADING
    // ==========================================

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

        initialize(
                chooseLevel(levelNumber),
                chooseBackground(levelNumber)
        );
    }
}