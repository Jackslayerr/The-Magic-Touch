package EnhancedMapTiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.*;
import Level.*;
import Enemies.*;

public class zapOrb extends EnhancedMapTile {
    Map map;
    ArrayList<Enemy> enemies;
    Point target;
    public zapOrb(Point location, Point target, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ElectricOrb.png"), 32, 32) ,TileType.PASSABLE);
        this.map = map;
        this.target = target;
    }
    
    @Override
    public void update(Player player) {
        super.update(player);
        enemies = map.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Lightning) {
                if (this.intersects(enemy) || this.touching(enemy)) {
                    zapPlayer(player);
                }
            }
        }
    }
    public void zapPlayer(Player player) {
        player.setLocation(target.x, target.y);
    }
    @Override
    public void initialize() {
        super.initialize();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 10)
                        .withScale(1.5f)
                        .withBounds(0, 0, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                        .withScale(1.5f)
                        .withBounds(0, 0, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 10)
                        .withScale(1.5f)
                        .withBounds(0, 0, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 10)
                        .withScale(1.5f)
                        .withBounds(0, 0, 32, 32)
                        .build()
            });

            }
        };
    
}
    
}