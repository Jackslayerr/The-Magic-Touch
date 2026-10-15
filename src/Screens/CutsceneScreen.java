
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

    // Each level has its own cutscene titles.
    private final String[][] titles = {
        {
            "THE LEGEND OF BONWOLD",
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

    // Each level can use different background images.
    // Missing images temporarily fall back to menu-screen.png.
    private final String[][] backgroundFiles = {
        {
            "menu-screen.png",
            "menu-screen.png",
            "menu-screen.png"
        },
        {
            "frostbite-caverns-cutscene.png",
            "frostbite-caverns-cutscene.png"
        },
        {
            "thunderbolt-castle-cutscene.png",
            "thunderbolt-castle-cutscene.png"
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
            "After conquering the Fire Dungeon, your journey " +
            "leads north into a frozen wilderness. Your mentor " +
            "warns you that the next trail is hidden beneath " +
            "ancient ice. Strange lights flicker inside the distant " +
            "mountains, and a bitter wind carries whispers of " +
            "something guarding the caverns below...",

            "Welcome to Frostbite Caverns! Frozen passages and " +
            "dangerous creatures stand between you and the next " +
            "sword piece. Watch your footing, explore the icy halls, " +
            "and overcome the creatures that protect this forgotten " +
            "place. Your next trial begins now!"
        },

        {
            "Word of your victories has spread across the kingdom. " +
            "Your mentor directs you toward a towering fortress " +
            "where violent storms gather around the highest spires. " +
            "Lightning flashes across the sky, illuminating an " +
            "ancient castle that has stood abandoned for generations. " +
            "Something powerful waits within its walls...",

            "Welcome to Thunderbolt Castle! Storm-charged halls " +
            "and dangerous guardians stand in your way. Search " +
            "the fortress, overcome its trials, and continue your " +
            "quest for Bonwold's legendary sword. Your mentor " +
            "believes the next piece lies beyond the castle's defenses. " +
            "Prepare yourself for the challenge ahead!"
        }
    };

    public CutsceneScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {

        currentLevel = screenCoordinator.getDestinationLevel();

        // Keep the level number within the levels currently implemented.
        if (currentLevel < 1 || currentLevel > scenes.length) {
            currentLevel = 1;
        }

        currentScene = 0;

        spaceWasDown = Keyboard.isKeyDown(Key.SPACE);
        escWasDown = Keyboard.isKeyDown(Key.ESC);

        loadCurrentBackground();

        titleText = createText(
            titles[currentLevel - 1][currentScene],
            100, 155, 26
        );

        continueText = createText(
            "Press SPACE to continue", 30, 555, 16
        );

        skipText = createText(
            "Press ESC to skip all", 560, 555, 14
        );
    }

    private void loadCurrentBackground() {

        String filename =
            backgroundFiles[currentLevel - 1][currentScene];

        File imageFile = new File("Resources/" + filename);

        if (imageFile.exists()) {
            backgroundImage = ImageLoader.load(filename);
        } else {
            backgroundImage = ImageLoader.load("menu-screen.png");
        }
    }

    private SpriteFont createText(
        String text, int x, int y, int size) {

        SpriteFont font = new SpriteFont(
            text, x, y, "Arial", size, Color.WHITE
        );

        font.setOutlineColor(Color.BLACK);
        font.setOutlineThickness(3);

        return font;
    }

    private void drawWrappedText(
        GraphicsHandler graphicsHandler,
        String text,
        int x,
        int y,
        int maxWidth,
        int lineHeight,
        int fontSize) {

        Graphics2D g = graphicsHandler.getGraphics();

        Font font = new Font(
            "Arial", Font.PLAIN, fontSize
        );

        FontMetrics metrics = g.getFontMetrics(font);

        int currentY = y;
        StringBuilder line = new StringBuilder();

        for (String word : text.split("\\s+")) {

            String testLine = line.length() == 0
                ? word
                : line + " " + word;

            if (metrics.stringWidth(testLine) > maxWidth
                    && line.length() > 0) {

                graphicsHandler.drawString(
                    line.toString(), x, currentY,
                    font, Color.WHITE
                );

                currentY += lineHeight;
                line = new StringBuilder(word);

            } else {
                line = new StringBuilder(testLine);
            }
        }

        if (line.length() > 0) {
            graphicsHandler.drawString(
                line.toString(), x, currentY,
                font, Color.WHITE
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

            if (currentScene < scenes[currentLevel - 1].length - 1) {

                currentScene++;

                loadCurrentBackground();

                titleText = createText(
                    titles[currentLevel - 1][currentScene],
                    100, 155, 26
                );

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

        graphicsHandler.drawImage(
            backgroundImage, 0, 0, 800, 605
        );

        graphicsHandler.drawFilledRectangleWithBorder(
            70, 110, 660, 390,
            new Color(20, 16, 30, 225),
            new Color(218, 170, 75),
            3
        );

        titleText.draw(graphicsHandler);

        drawWrappedText(
            graphicsHandler,
            scenes[currentLevel - 1][currentScene],
            100, 220, 600, 30, 18
        );

        continueText.draw(graphicsHandler);
        skipText.draw(graphicsHandler);
    }

    private void startLevel() {
        screenCoordinator.setGameState(GameState.LEVEL);
    }
}