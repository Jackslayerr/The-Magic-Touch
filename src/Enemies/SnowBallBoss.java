package Enemies;

import java.awt.Color;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.Direction;
import Utils.Point;
import Level.*;

public class SnowBallBoss extends Enemy {
    Direction direction;
    int shootTimer = 0;
    public SnowBallBoss(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("snowballEnemy.png"), 24, 24), "DEFAULT");
        this.direction = facingDirection;
        this.initialize();
    }
    @Override
    public void update(Player player) {
        super.update(player);
        moveXHandleCollision(-1);
        shootTimer += 1;
        if (shootTimer == 120) {
            shootSnowBall();
            shootTimer = 0;
        }
        if (this.intersects(player)) {
            player.setLevelState(LevelState.PLAYER_DEAD);
        }


    }
    public void shootSnowBall() {
        float xPoint = getX() - 25;
        float yPoint;
        int n = ThreadLocalRandom.current().nextInt(1, 4);
        if (n == 1) {
            yPoint = getY() + 100;
        } else if (n == 2) {
            yPoint = getY() + 225;
        } else {
            yPoint = getY() + 325;
        }
        Point shootPoint = new Point(xPoint, yPoint);
        snowball sb = new snowball(shootPoint);
        map.addEnemy(sb);
    }
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));   // red, semi-transparent
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                        .withScale(21)
                        .withBounds(6, 6, 12, 16)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 20)
                        .withScale(21)
                        .withBounds(6, 6, 12, 16)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 20)
                        .withScale(21)
                        .withBounds(6, 6, 12, 16)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 20)
                        .withScale(21)
                        .withBounds(6, 6, 12, 16)
                        .build()
            });

            }
        };
    
}

}
