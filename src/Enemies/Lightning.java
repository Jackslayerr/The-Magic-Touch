package Enemies;
import Utils.*;

import static javax.swing.UIManager.put;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import java.awt.Color;
import Level.*;

public class Lightning extends Enemy {
    Point playerLocation;
    ArrayList<Enemy> enemies;
    int existenceFrames = 20;

    public Lightning(Point playerLocation, Map map) {
        super(playerLocation.x, playerLocation.y, new SpriteSheet(ImageLoader.load("thunderCloud.png"), 64, 160), "LIGHTNING");
        this.playerLocation = playerLocation;
        enemies = map.getEnemies();
    }

    @Override
    public void update(Player player) {
        lightningStrike();
        player.setFrozen(true);
        existenceFrames -= 1;
        if (existenceFrames == 0) {
            player.setFrozen(false);
            this.setMapEntityStatus(MapEntityStatus.REMOVED);
        }
    }

    public void lightningStrike() {
        for (Enemy enemy : enemies) {
            if (this.touching(enemy) || this.intersects(enemy)) {
                if (enemy != this) {
                    enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                }
            }
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(0, 255, 0, 120));
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("LIGHTNING", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0), 8)
                .withScale(1)
                .withBounds(16,0,32,144)
                .build()
            });
        }};
    }
}