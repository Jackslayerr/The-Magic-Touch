
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

    // ==========================================
    // CUTSCENE TITLES
    // ==========================================

    private final String[][] titles = {

        // LEVEL 1 - FIRE DUNGEON
        {
            "THE LEGEND",
            "THE MENTOR'S QUEST",
            "THE FIRE DUNGEON"
        },

        // LEVEL 2 - FROSTBITE CAVERNS
        {
            "THE FROZEN FRONTIER",
            "FROSTBITE CAVERNS"
        },

        // LEVEL 3 - THUNDERBOLT CASTLE
        {
            "THE STORM APPROACHES",
            "THUNDERBOLT CASTLE"
        },

        // LEVEL 4 - EMERALD WILDS
        {
            "ANOTHER PIECE...",
            "THE EMERALD WILDS"
        },

        // LEVEL 5 - CURSED KINGDOM
        {
            "THE ORB RESTORED",
            "THE BETRAYAL",
            "MALODOR AWAKENS",
            "THE CURSED KINGDOM",
            "THE FINAL BATTLE"
        }
    };

    // ==========================================
    // BACKGROUND IMAGES
    // ==========================================

    private final String[][] backgroundFiles = {

        // LEVEL 1
        {
            "cutscene-level1-1.png",
            "cutscene-level1-2.png",
            "cutscene-level1-3.png"
        },

        // LEVEL 2
        {
            "cutscene-level2-1.png",
            "cutscene-level2-2.png"
        },

        // LEVEL 3
        {
            "cutscene-level3-1.png",
            "cutscene-level3-2.png"
        },

        // LEVEL 4
        {
            "cutscene-level4-1.png",
            "cutscene-level4-2.png"
        },

        // LEVEL 5 - CURSED KINGDOM
        {
            "cutscene-level5-1.png",
            "cutscene-level5-2.png",
            "cutscene-level5-3.png",
            "cutscene-level5-4.png",
            "cutscene-level5-5.png"
        }
    };

    // ==========================================
    // STORY DIALOGUE
    // ==========================================

    private final String[][] scenes = {

        // ======================================
        // LEVEL 1 - FIRE DUNGEON
        // ======================================
        {
            "Long ago, the kingdom faced a terrible threat. " +
            "The evil sorcerer Malodor sought to plunge the world " +
            "into darkness. But one hero stood against him: " +
            "Bonwold the Great. Armed with a legendary magic orb, " +
            "Bonwold defeated Malodor and saved the kingdom. " +
            "Yet the victory came at a cost. The mighty orb was " +
            "shattered into four pieces, scattered across the kingdom. " +
            "Centuries have passed, and the legend lives on...",

            "You have dreamed of becoming a treasure hunter " +
            "your entire life. One day, a retired treasure hunter " +
            "approaches you with an opportunity beyond your wildest " +
            "dreams. He explains that the four pieces of Bonwold's " +
            "legendary orb must be recovered and brought together " +
            "once more. He believes you are worthy of the challenge. " +
            "Your adventure begins now!",

            "Your journey leads you to the scorching depths " +
            "of the Fire Dungeon! Dangerous goblins guard its " +
            "ancient halls, and the first piece of Bonwold's " +
            "legendary orb lies somewhere within. Use your " +
            "Fire Hand Rune to launch fireballs, defeat enemies, " +
            "and light torches to solve the dungeon's puzzles. " +
            "Defeat the goblins and recover the first piece " +
            "of Bonwold's shattered orb! Press Z to shoot fireballs."
        },

        // ======================================
        // LEVEL 2 - FROSTBITE CAVERNS
        // ======================================
        {
            "Excellent work! The first piece of Bonwold's orb " +
            "is ours! Three more pieces remain before the " +
            "legendary orb can be restored. You're doing " +
            "wonderfully, my young adventurer. Ha ha! " +
            "Now, onward to the Frostbite Caverns! " +
            "The next piece awaits!",

            "Welcome to Frostbite Caverns! Deep within these " +
            "frozen passages lies the second piece of Bonwold's " +
            "legendary orb. Dangerous creatures guard the icy " +
            "halls, and the frozen paths are treacherous. " +
            "Watch your footing, defeat the guardians, and " +
            "recover the next piece. We are one step closer " +
            "to restoring Bonwold's legendary orb!"
        },

        // ======================================
        // LEVEL 3 - THUNDERBOLT CASTLE
        // ======================================
        {
            "Wonderful! The second piece is ours! " +
            "We're halfway to restoring Bonwold's legendary orb. " +
            "I can hardly contain my excitement! Ha ha! " +
            "Keep up the excellent work, my young adventurer. " +
            "Now, onward to Thunderbolt Castle! " +
            "The third piece is waiting for us!",

            "Welcome to Thunderbolt Castle! Storm-charged halls " +
            "and dangerous guardians stand between you and " +
            "the third piece of Bonwold's legendary orb. " +
            "Search the fortress, overcome its trials, and " +
            "claim the next piece. Only two pieces remain " +
            "before the legendary orb can be restored!"
        },

        // ======================================
        // LEVEL 4 - EMERALD WILDS
        // ======================================
        {
            // Scene 1 - Mentor
            "Splendid! Another piece of the orb for me... " +
            "I mean, for the kingdom's protection! " +
            "You're doing a marvelous job, my young adventurer. " +
            "Just one more piece, and Bonwold's legendary orb " +
            "will finally be whole again. Everything will be " +
            "as it should be. Now, off to the Emerald Wilds! " +
            "Do hurry, I'm growing rather impatient!",

            // Scene 2 - Emerald Wilds
            "Welcome to the Emerald Wilds! Deep within the ancient ruins " +
            "lies the final piece of Bonwold's legendary orb. " +
            "Defeat the monsters, conquer the ancient ruins, " +
            "and retrieve the final piece! Once we have it, " +
            "the legendary orb can finally be reassembled!"
        },

        // ======================================
        // LEVEL 5 - CURSED KINGDOM
        // ======================================
        {
            // Scene 1 - The Orb Restored
            "Magnificent! You've done it! All four pieces " +
            "of Bonwold's legendary orb have finally been reunited.",

            // Scene 2 - The Betrayal
            "After all these centuries, the orb is finally mine! " +
            "You foolish child. Did you truly believe I wanted " +
            "to save the kingdom?",

            // Scene 3 - Malodor Awakens
            "The mentor begins to transform before your eyes. " +
            "His disguise fades, revealing the evil sorcerer Malodor! " +
            "At last, the power of Bonwold's orb will be mine!",

            // Scene 4 - The Cursed Kingdom
            "Darkness spreads across the kingdom as Malodor unleashes " +
            "the orb's power. The land is consumed by a terrible curse. " +
            "There is only one way to stop him!",

            // Scene 5 - The Final Battle
            "The time has come to face Malodor! " +
            "Defeat the sorcerer and save the kingdom. " +
            "The fate of the world rests in your hands!"
        }
    };

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public CutsceneScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    // ==========================================
    // INITIALIZE
    // ==========================================

    @Override
    public void initialize(Map level, String backgroundImageFile) {

        currentLevel = screenCoordinator.getDestinationLevel();

        // Keep the level number within implemented levels.
        if (currentLevel < 1 || currentLevel > scenes.length) {
            currentLevel = 1;
        }

        currentScene = 0;

        spaceWasDown = Keyboard.isKeyDown(Key.SPACE);
        escWasDown = Keyboard.isKeyDown(Key.ESC);

        loadDialogueFont();
        loadCurrentBackground();
    }

    // ==========================================
    // LOAD PIXEL FONT
    // ==========================================

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

    // ==========================================
    // LOAD CURRENT BACKGROUND
    // ==========================================

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

    // ==========================================
    // DRAW WRAPPED DIALOGUE
    // ==========================================

    private void drawWrappedText(
        GraphicsHandler graphicsHandler,
        String text,
        int x,
        int y,
        int maxWidth,
        int lineHeight,
        int fontSize,
        int maxLines
    ) {

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

    // ==========================================
    // UPDATE
    // ==========================================

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

    // ==========================================
    // DRAW
    // ==========================================

    @Override
    public void draw(GraphicsHandler graphicsHandler) {

        // Draw background across the game screen.
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

        // Draw top banner text.
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

    // ==========================================
    // START LEVEL
    // ==========================================

    private void startLevel() {
        screenCoordinator.setGameState(GameState.LEVEL);
    }
}
