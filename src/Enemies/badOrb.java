package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Utils.*;
import Level.Player;

public class badOrb extends Enemy {
    float xSpeed;
    float ySpeed;
    public badOrb(Point location, float xSpeed, float ySpeed) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ElectricOrb.png"), 32, 32), "DEFAULT");
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        canBePushed = false;
    }

    @Override 
    public void update(Player player) {
        super.update(player);
        moveXHandleCollision(xSpeed);
        moveYHandleCollision(ySpeed);
    }
    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        super.onEndCollisionCheckX(hasCollided, direction, entityCollidedWith);
        if (hasCollided) {
            xSpeed = -xSpeed;
        }
    }
    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        super.onEndCollisionCheckY(hasCollided, direction, entityCollidedWith);
        if (hasCollided) {
            ySpeed = -ySpeed;
        }
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
