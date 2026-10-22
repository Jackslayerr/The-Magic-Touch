
package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Level.Map;
import Screens.CreditsScreen;
import Screens.MenuScreen;
import Screens.CutsceneScreen;
import Screens.PlayLevelScreen;
import Maps.LevelOneMap;
import Maps.LevelTwoMap;
import Maps.LevelThreeMap;

public class ScreenCoordinator extends Screen {

    protected Screen currentScreen = new DefaultScreen();

    protected GameState gameState;
    protected GameState previousGameState;
    protected Map level;
    protected String backgroundImageFile;

    protected int destinationLevel = 1;

    public int getDestinationLevel() {
        return destinationLevel;
    }

    public void setDestinationLevel(int destinationLevel) {
        this.destinationLevel = destinationLevel;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void initialize(Map level, String backgroundImageFile) {
        this.level = level;
        this.backgroundImageFile = backgroundImageFile;
        this.destinationLevel = 1;
        this.previousGameState = null;
        this.gameState = GameState.MENU;
    }

    @Override
    public void update() {
        do {
            if (previousGameState != gameState) {
                switch (gameState) {

                    case MENU:
                        currentScreen = new MenuScreen(this);
                        currentScreen.initialize(level, "menu-screen.png");
                        break;

                    case CUTSCENE:
                        currentScreen = new CutsceneScreen(this);
                        currentScreen.initialize(level, "menu-screen.png");
                        break;

                    case LEVEL:
                        currentScreen = new PlayLevelScreen(this);

                        switch (destinationLevel) {
                            case 1:
                                currentScreen.initialize(
                                    new LevelOneMap(),
                                    "game-background-level1.png"
                                );
                                break;

                            case 2:
                                currentScreen.initialize(
                                    new LevelTwoMap(),
                                    "game-background-level2.png"
                                );
                                break;

                            case 3:
                                currentScreen.initialize(
                                    new LevelThreeMap(),
                                    "game-background-level3.png"
                                );
                                break;

                            default:
                                destinationLevel = 1;
                                currentScreen.initialize(
                                    new LevelOneMap(),
                                    "game-background-level1.png"
                                );
                                break;
                        }
                        break;

                    case CREDITS:
                        currentScreen = new CreditsScreen(this);
                        break;
                }
            }

            previousGameState = gameState;
            currentScreen.update();

        } while (previousGameState != gameState);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        currentScreen.draw(graphicsHandler);
    }
}
