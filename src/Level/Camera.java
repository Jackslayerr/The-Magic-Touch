
package Level;

import Engine.GraphicsHandler;
import Engine.ScreenManager;
import GameObject.GameObject;
import GameObject.Rectangle;
import Maps.LevelTwoMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// This class represents a Map's "Camera", aka a piece of the map that is currently included in a level's update/draw logic based on what should be shown on screen.
public class Camera extends Rectangle {

    // the current map this camera is attached to
    private Map map;

    // Background image for Level 2
    private BufferedImage backgroundImage;

    // width and height of each tile in the map
    private int tileWidth, tileHeight;

    // leftover space on screen
    private int leftoverSpaceX, leftoverSpaceY;

    // current map entities
    private ArrayList<Enemy> activeEnemies = new ArrayList<>();
    private ArrayList<EnhancedMapTile> activeEnhancedMapTiles = new ArrayList<>();
    private ArrayList<NPC> activeNPCs = new ArrayList<>();

    // determines how many tiles off screen an entity can be before inactive
    private final int UPDATE_OFF_SCREEN_RANGE = 4;

    public Camera(int startX, int startY, int tileWidth, int tileHeight, Map map) {
        super(startX, startY, ScreenManager.getScreenWidth() / tileWidth, ScreenManager.getScreenHeight() / tileHeight);

        this.map = map;

        // Load background only for Level 2
        if (map instanceof LevelTwoMap) {
            backgroundImage = Engine.ImageLoader.load("game-background-level2.png");
        }

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.leftoverSpaceX = ScreenManager.getScreenWidth() % tileWidth;
        this.leftoverSpaceY = ScreenManager.getScreenHeight() % tileHeight;
    }

    // gets the tile index that the camera's x and y values are currently on
    public Point getTileIndexByCameraPosition() {
        int xIndex = Math.round(getX()) / tileWidth;
        int yIndex = Math.round(getY()) / tileHeight;
        return new Point(xIndex, yIndex);
    }

    public void update(Player player) {
        updateMapTiles();
        updateMapEntities(player);
    }

    private void updateMapTiles() {
        for (MapTile tile : map.getAnimatedMapTiles()) {
            tile.update();
        }
    }

    // update map entities currently a part of the update/draw cycle
    public void updateMapEntities(Player player) {
        activeEnemies = loadActiveEnemies();
        activeEnhancedMapTiles = loadActiveEnhancedMapTiles();
        activeNPCs = loadActiveNPCs();

        for (Enemy enemy : activeEnemies) {
            enemy.update(player);
        }

        for (EnhancedMapTile enhancedMapTile : activeEnhancedMapTiles) {
            enhancedMapTile.update(player);
        }

        for (NPC npc : activeNPCs) {
            npc.update(player);
        }
    }

    // determine which enemies are active
    private ArrayList<Enemy> loadActiveEnemies() {
        ArrayList<Enemy> activeEnemies = new ArrayList<>();

        for (int i = map.getEnemies().size() - 1; i >= 0; i--) {
            Enemy enemy = map.getEnemies().get(i);

            if (isMapEntityActive(enemy)) {
                activeEnemies.add(enemy);

                if (enemy.mapEntityStatus == MapEntityStatus.INACTIVE) {
                    enemy.setMapEntityStatus(MapEntityStatus.ACTIVE);
                }
            } else if (enemy.getMapEntityStatus() == MapEntityStatus.ACTIVE) {
                enemy.setMapEntityStatus(MapEntityStatus.INACTIVE);
            } else if (enemy.getMapEntityStatus() == MapEntityStatus.REMOVED) {
                map.getEnemies().remove(i);
            }
        }

        return activeEnemies;
    }

    // determine which enhanced map tiles are active
    private ArrayList<EnhancedMapTile> loadActiveEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> activeEnhancedMapTiles = new ArrayList<>();

        for (int i = map.getEnhancedMapTiles().size() - 1; i >= 0; i--) {
            EnhancedMapTile enhancedMapTile = map.getEnhancedMapTiles().get(i);

            if (isMapEntityActive(enhancedMapTile)) {
                activeEnhancedMapTiles.add(enhancedMapTile);

                if (enhancedMapTile.mapEntityStatus == MapEntityStatus.INACTIVE) {
                    enhancedMapTile.setMapEntityStatus(MapEntityStatus.ACTIVE);
                }
            } else if (enhancedMapTile.getMapEntityStatus() == MapEntityStatus.ACTIVE) {
                enhancedMapTile.setMapEntityStatus(MapEntityStatus.INACTIVE);
            } else if (enhancedMapTile.getMapEntityStatus() == MapEntityStatus.REMOVED) {
                map.getEnhancedMapTiles().remove(i);
            }
        }

        return activeEnhancedMapTiles;
    }

    // determine which NPCs are active
    private ArrayList<NPC> loadActiveNPCs() {
        ArrayList<NPC> activeNPCs = new ArrayList<>();

        for (int i = map.getNPCs().size() - 1; i >= 0; i--) {
            NPC npc = map.getNPCs().get(i);

            if (isMapEntityActive(npc)) {
                activeNPCs.add(npc);

                if (npc.mapEntityStatus == MapEntityStatus.INACTIVE) {
                    npc.setMapEntityStatus(MapEntityStatus.ACTIVE);
                }
            } else if (npc.getMapEntityStatus() == MapEntityStatus.ACTIVE) {
                npc.setMapEntityStatus(MapEntityStatus.INACTIVE);
            } else if (npc.getMapEntityStatus() == MapEntityStatus.REMOVED) {
                map.getNPCs().remove(i);
            }
        }

        return activeNPCs;
    }

    private boolean isMapEntityActive(MapEntity mapEntity) {
        return mapEntity.getMapEntityStatus() != MapEntityStatus.REMOVED
                && (mapEntity.isUpdateOffScreen() || containsUpdate(mapEntity));
    }

    public void draw(GraphicsHandler graphicsHandler) {

        // Draw Level 2 background first
        if (backgroundImage != null) {
            graphicsHandler.drawImage(
                    backgroundImage,
                    0,
                    0,
                    ScreenManager.getScreenWidth(),
                    ScreenManager.getScreenHeight()
            );
        }

        // Draw map tiles and entities over the background
        drawMapTiles(graphicsHandler);
        drawMapEntities(graphicsHandler);
    }

    // draws visible map tiles to the screen
    public void drawMapTiles(GraphicsHandler graphicsHandler) {
        Point tileIndex = getTileIndexByCameraPosition();

        for (int i = tileIndex.y - 1; i <= tileIndex.y + height + 1; i++) {
            for (int j = tileIndex.x - 1; j <= tileIndex.x + width + 1; j++) {
                MapTile tile = map.getMapTile(j, i);

                if (tile != null) {
                    tile.draw(graphicsHandler);
                }
            }
        }
    }

    // draws active map entities to the screen
    public void drawMapEntities(GraphicsHandler graphicsHandler) {
        for (Enemy enemy : activeEnemies) {
            if (containsDraw(enemy)) {
                enemy.draw(graphicsHandler);
            }
        }

        for (EnhancedMapTile enhancedMapTile : activeEnhancedMapTiles) {
            if (containsDraw(enhancedMapTile)) {
                enhancedMapTile.draw(graphicsHandler);
            }
        }

        for (NPC npc : activeNPCs) {
            if (containsDraw(npc)) {
                npc.draw(graphicsHandler);
            }
        }
    }

    public boolean containsUpdate(GameObject gameObject) {
        return getX1() - (tileWidth * UPDATE_OFF_SCREEN_RANGE) < gameObject.getX() + gameObject.getWidth()
                && getEndBoundX() + (tileWidth * UPDATE_OFF_SCREEN_RANGE) > gameObject.getX()
                && getY1() - (tileHeight * UPDATE_OFF_SCREEN_RANGE) < gameObject.getY() + gameObject.getHeight()
                && getEndBoundY() + (tileHeight * UPDATE_OFF_SCREEN_RANGE) > gameObject.getY();
    }

    public boolean containsDraw(GameObject gameObject) {
        return getX1() - tileWidth < gameObject.getX() + gameObject.getWidth()
                && getEndBoundX() + tileWidth > gameObject.getX()
                && getY1() - tileHeight < gameObject.getY() + gameObject.getHeight()
                && getEndBoundY() + tileHeight > gameObject.getY();
    }

    public ArrayList<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public ArrayList<EnhancedMapTile> getActiveEnhancedMapTiles() {
        return activeEnhancedMapTiles;
    }

    public ArrayList<NPC> getActiveNPCs() {
        return activeNPCs;
    }

    public float getEndBoundX() {
        return x + (width * tileWidth) + leftoverSpaceX;
    }

    public float getEndBoundY() {
        return y + (height * tileHeight) + leftoverSpaceY;
    }

    public boolean isAtTopOfMap() {
        return this.getY() <= 0;
    }

    public boolean isAtBottomOfMap() {
        return this.getEndBoundY() >= map.getEndBoundY();
    }

    public boolean isAtRightOfMap() {
        return this.getEndBoundX() >= map.getEndBoundX();
    }

    public boolean isAtLeftOfMap() {
        return this.getX() <= 0;
    }
}
