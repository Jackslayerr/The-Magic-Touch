
package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Game.ScreenCoordinator;
import Game.GameState;
import Level.Map;
import SpriteFont.SpriteFont;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class CutsceneScreen extends Screen {

    private ScreenCoordinator screenCoordinator;
    private BufferedImage backgroundImage;

    private SpriteFont titleText;
    private SpriteFont skipText;
    private SpriteFont continueText;

    private int currentScene = 0;
    private int currentLevel = 1;

    private boolean spaceWasDown = false;
    private boolean escWasDown = false;

    // Cutscene titles for each level.
    private final String[][] titles = {
        {
            "THE LEGEND",
            "THE MENTOR'S QUEST",
            "THE FIRE DUNGEON"
        },
        {
            "THE FROZEN FRONTIER",
            "FROSTBITE CAVERNS"
        },
        {
            "THE STORM APPROACHES",
            "THUNDERBOLT CASTLE"
        }
    };

    // Background PNGs for each cutscene.
    private final String[][] backgroundFiles = {
        {
            "cutscene-level1-1.png",
            "cutscene-level1-2.png",
            "cutscene-level1-3.png"
        },
        {
            "cutscene-level2-1.png",
            "cutscene-level2-2.png"
        },
        {
            "cutscene-level3-1.png",
            "cutscene-level3-2.png"
        }
    };

    // Story dialogue for each level.
    private final String[][] scenes = {
        {
            "Long ago, the kingdom faced a terrible threat. " +
            "The evil sorcerer Malodor sought to plunge the world " +
            "into darkness. But one hero stood against him: " +
            "Bonwold the Great. Armed with a legendary magic sword, " +
            "Bonwold defeated Malodor and saved the kingdom. " +
            "Yet the victory came at a cost. The mighty sword was " +
            "shattered into four pieces, scattered across the kingdom. " +
            "Centuries have passed, and the legend lives on...",

            "You have dreamed of becoming a treasure hunter " +
            "your entire life. One day, a retired treasure hunter " +
            "approaches you with an opportunity beyond your wildest " +
            "dreams. He says the four pieces of Bonwold's legendary " +
            "sword must be recovered to protect the kingdom. " +
            "He believes you are worthy of the challenge. " +
            "Your adventure begins now! But something about " +
            "your new mentor feels strange...",

            "Your journey leads you to the scorching depths " +
            "of the Fire Dungeon! Dangerous goblins guard its " +
            "ancient halls, and the first piece of Bonwold's " +
            "legendary sword lies somewhere within. Use your " +
            "Fire Hand Rune to launch fireballs, defeat enemies, " +
            "and light torches to solve the dungeon's puzzles. " +
            "Objective: Defeat the goblins and retrieve the first " +
            "piece! Press Z to shoot fireballs. Your quest begins here!"
        },

        {
            // Level 2, Scene 1
            "Excellent work! One artifact down, three to go! " +
            "I can already feel our kingdom becoming... safer. Ha ha! " +
            "You've done wonderfully, my young adventurer. " +
            "Now, onward to the Frostbite Caverns! There's no time to waste!",

            "Welcome to Frostbite Caverns! Frozen passages and " +
            "dangerous creatures stand between you and the next " +
            "sword piece. Watch your footing, explore the icy halls, " +
            "and overcome the creatures that protect this forgotten " +
            "place. Your next trial begins now!"
        },

        {
            "Wonderful! You're making excellent progress! " +
            "Keep collecting those artifacts, and let's say we'll have everything we need. Ha ha! " +
            "You've done your part splendidly. " +
            "Now, onward to Thunderbolt Castle! The next artifact awaits!",

            "Welcome to Thunderbolt Castle! Storm-charged halls " +
            "and dangerous guardians stand in your way. Search " +
            "the fortress, overcome its trials, and continue your " +
            "quest for Bonwold's legendary sword.!"
        }
    };

    public CutsceneScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {

        currentLevel = screenCoordinator.getDestinationLevel();

        // Keep the level number within the implemented levels.
        if (currentLevel < 1 || currentLevel > scenes.length) {
            currentLevel = 1;
        }

        currentScene = 0;

        spaceWasDown = Keyboard.isKeyDown(Key.SPACE);
        escWasDown = Keyboard.isKeyDown(Key.ESC);

        loadCurrentBackground();
        updateCutsceneText();
    }

    // Creates the title and controls for the current scene.
    private void updateCutsceneText() {

        titleText = createText(
            titles[currentLevel - 1][currentScene],
            40, 29, 18
        );

        continueText = createText(
            "SPACE: Continue", 515, 29, 13
        );

        skipText = createText(
            "ESC: Skip", 680, 29, 13
        );
    }

    private void loadCurrentBackground() {

        String filename =
            backgroundFiles[currentLevel - 1][currentScene];

        File imageFile = new File("Resources/" + filename);

        if (imageFile.exists()) {
            backgroundImage = ImageLoader.load(filename);
        } else {
            System.out.println(
                "Cutscene background not found: " +
                imageFile.getPath()
            );

            backgroundImage = ImageLoader.load("menu-screen.png");
        }
    }

    private SpriteFont createText(
        String text, int x, int y, int size) {

        SpriteFont font = new SpriteFont(
            text, x, y, "Arial", size, Color.BLACK
        );

        font.setOutlineColor(new Color(255, 235, 180));
        font.setOutlineThickness(1);

        return font;
    }

    private void drawWrappedText(
        GraphicsHandler graphicsHandler,
        String text,
        int x,
        int y,
        int maxWidth,
        int lineHeight,
        int fontSize,
        int maxLines) {

        Graphics2D g = graphicsHandler.getGraphics();

        Font font = new Font(
            "Arial", Font.PLAIN, fontSize
        );

        FontMetrics metrics = g.getFontMetrics(font);

        int currentY = y;
        int linesDrawn = 0;

        StringBuilder line = new StringBuilder();
        String[] words = text.split("\\s+");

        for (String word : words) {

            String testLine = line.length() == 0
                ? word
                : line + " " + word;

            if (metrics.stringWidth(testLine) > maxWidth
                    && line.length() > 0) {

                if (linesDrawn >= maxLines) {
                    return;
                }

                graphicsHandler.drawString(
                    line.toString(), x, currentY,
                    font, Color.BLACK
                );

                currentY += lineHeight;
                linesDrawn++;

                line = new StringBuilder(word);

            } else {
                line = new StringBuilder(testLine);
            }
        }

        if (line.length() > 0 && linesDrawn < maxLines) {
            graphicsHandler.drawString(
                line.toString(), x, currentY,
                font, Color.BLACK
            );
        }
    }

    @Override
    public void update() {

        boolean spaceIsDown = Keyboard.isKeyDown(Key.SPACE);
        boolean escIsDown = Keyboard.isKeyDown(Key.ESC);

        if (escIsDown && !escWasDown) {
            startLevel();
            return;
        }

        if (spaceIsDown && !spaceWasDown) {

            if (currentScene <
                    scenes[currentLevel - 1].length - 1) {

                currentScene++;

                loadCurrentBackground();
                updateCutsceneText();

            } else {
                startLevel();
                return;
            }
        }

        spaceWasDown = spaceIsDown;
        escWasDown = escIsDown;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        // Draw the background across the game screen.
        graphicsHandler.drawImage(
            backgroundImage, 0, 0, 800, 605
        );

        // Small tan banner matching the dialogue box.
        graphicsHandler.drawFilledRectangleWithBorder(
            25, 15, 750, 45,
            new Color(244, 215, 139),
            new Color(151, 92, 43),
            3
        );

        // Main dialogue box, raised slightly from the bottom.
        graphicsHandler.drawFilledRectangleWithBorder(
            35, 405, 730, 175,
            new Color(244, 215, 139),
            new Color(151, 92, 43),
            4
        );

        // Inner highlight border.
        graphicsHandler.drawFilledRectangleWithBorder(
            43, 413, 714, 159,
            new Color(244, 215, 139),
            new Color(255, 235, 180),
            2
        );

        // Title and controls in the small top banner.
        titleText.draw(graphicsHandler);
        continueText.draw(graphicsHandler);
        skipText.draw(graphicsHandler);

        // Dialogue text inside the bottom box.
        drawWrappedText(
            graphicsHandler,
            scenes[currentLevel - 1][currentScene],
            60, 440, 680, 24, 16, 5
        );
    }

    private void startLevel() {
        screenCoordinator.setGameState(GameState.LEVEL);
    }
}
