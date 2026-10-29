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
    private Direction facingDirection;

    public Lightning(Point playerLocation, Map map, Player player) {
        super(playerLocation.x, playerLocation.y, new SpriteSheet(ImageLoader.load("thunderCloud.png"), 64, 160), player.getFacingDirection() == Direction.RIGHT ? "BOLT_RIGHT" : "BOLT_LEFT");
        this.playerLocation = playerLocation;
        enemies = map.getEnemies();
        initialize();
    }

    @Override
    public void update(Player player) {
        lightningStrike();
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
                    if (enemy instanceof DragonEnemy) {
                        ((DragonEnemy) enemy).takeDamage(0.3f);
                    } else {
                        enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                    }
                }
            }
        }
    }

    

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
            return new HashMap<String, Frame[]>() {{
                put("BOLT_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                                .withScale(1)
                                .withBounds(16, 0, 32, 144)
                                .build()
                });

                put("BOLT_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                                .withScale(1)
                                .withImageEffect(GameObject.ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(16, 0, 32, 144)
                                .build()
                });
        }};
    }
}