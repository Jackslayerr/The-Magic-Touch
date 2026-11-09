
package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Game.ScreenCoordinator;
import Game.GameState;
import Level.Map;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

public class CutsceneScreen extends Screen {

    private ScreenCoordinator screenCoordinator;
    private BufferedImage backgroundImage;

    // Custom pixel font.
    private Font dialogueFont;

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
            "Bonwold the Great. Armed with a legendary magic orb, " +
            "Bonwold defeated Malodor and saved the kingdom. " +
            "Yet the victory came at a cost. The mighty weapon was " +
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
            "legendary orb lies somewhere within. Use your " +
            "Fire Hand Rune to launch fireballs, defeat enemies, " +
            "and light torches to solve the dungeon's puzzles. " +
            "Objective: Defeat the goblins and retrieve the first " +
            "piece! Press Z to shoot fireballs. Your quest begins here!"
        },

        {
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
            "Keep collecting those pieces of the orb, and let's say we'll have everything we need. Ha ha! " +
            "You've done your part splendidly. " +
            "Now, onward to Thunderbolt Castle! The next artifact awaits!",

            "Welcome to Thunderbolt Castle! Storm-charged halls " +
            "and dangerous guardians stand in your way. Search " +
            "the fortress, overcome its trials, and continue your " +
            "quest for Bonwold's legendary orb.!"
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

        loadDialogueFont();
        loadCurrentBackground();
    }

    // Load the custom Pixel Operator font.
    private void loadDialogueFont() {

        try {

            File fontFile = new File(
                "Resources/fonts/PixelOperator-Bold.ttf"
            );

            dialogueFont = Font.createFont(
                Font.TRUETYPE_FONT,
                fontFile
            );

            System.out.println("Pixel Operator font loaded!");

        } catch (Exception e) {

            System.out.println(
                "Could not load Pixel Operator font: " +
                e.getMessage()
            );

            dialogueFont = new Font(
                "Monospaced",
                Font.BOLD,
                17
            );
        }
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

    // Draws wrapped dialogue using the pixel font.
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

        Font font = dialogueFont.deriveFont(
            Font.BOLD, (float) fontSize
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
                    line.toString(),
                    x,
                    currentY,
                    font,
                    new Color(85, 45, 25)
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
                line.toString(),
                x,
                currentY,
                font,
                new Color(85, 45, 25)
            );
        }
    }

    @Override
    public void update() {

        boolean spaceIsDown = Keyboard.isKeyDown(Key.SPACE);
        boolean escIsDown = Keyboard.isKeyDown(Key.ESC);

        // ESC skips the cutscene.
        if (escIsDown && !escWasDown) {
            startLevel();
            return;
        }

        // SPACE advances to the next scene.
        if (spaceIsDown && !spaceWasDown) {

            if (currentScene <
                    scenes[currentLevel - 1].length - 1) {

                currentScene++;

                loadCurrentBackground();

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

        // Small tan title banner.
        graphicsHandler.drawFilledRectangleWithBorder(
            25, 15, 750, 45,
            new Color(244, 215, 139),
            new Color(151, 92, 43),
            3
        );

        // Main dialogue box.
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

        // Draw all top banner text using Pixel Operator.
        Graphics2D g = graphicsHandler.getGraphics();

        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
        );

        g.setColor(new Color(85, 45, 25));

        // Cutscene title.
        g.setFont(
            dialogueFont.deriveFont(Font.BOLD, 18f)
        );

        g.drawString(
            titles[currentLevel - 1][currentScene],
            40, 43
        );

        // Continue control.
        g.setFont(
            dialogueFont.deriveFont(Font.BOLD, 13f)
        );

        g.drawString(
            "SPACE: CONTINUE",
            515, 43
        );

        // Skip control.
        g.drawString(
            "ESC: SKIP",
            680, 43
        );

        // Main dialogue text.
        drawWrappedText(
            graphicsHandler,
            scenes[currentLevel - 1][currentScene],
            60, 440, 680, 25, 17, 5
        );
    }

    private void startLevel() {
        screenCoordinator.setGameState(GameState.LEVEL);
    }
}
