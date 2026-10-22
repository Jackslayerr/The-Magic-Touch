package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

public class SHEEP2 extends Enemy {
    private float velocityY = -6.0f;
    private float gravity = 0.25f;
    private float targetX;
    private int lifeTimer = 300;

    public SHEEP2(Point location, float targetX) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("S.H.E.E.P.Enemy.png"), 33, 24), "FLY_LEFT");
        this.targetX = targetX;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        currentAnimationName = "FLY_LEFT";
    }

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

        // Apply arc physics
        velocityY += gravity;
        moveAmountY = velocityY;

        // Drifting horizontally towards the target X coordinate
        float currentX = this.getX();
        if (Math.abs(currentX - targetX) > 2) {
            if (currentX < targetX) {
                moveAmountX += 2.0f;
            } else {
                moveAmountX -= 2.0f;
            }
        }

        // Apply movement without block collision stopping it in mid-air
        moveX(moveAmountX);
        moveY(moveAmountY);

        super.update(player);

        // Explode if it touches the ground while falling downward
        if (velocityY > 0 && map.getTileByPosition(this.getX(), this.getY() + this.getHeight()) != null) {
            if (map.getTileByPosition(this.getX(), this.getY() + this.getHeight()).getTileType() != Level.TileType.PASSABLE) {
                explode();
                return;
            }
        }

        if (lifeTimer <= 0) {
            explode();
        }
        lifeTimer--;
    }

    public void explode() {
        float explosionX = this.getX() - 82;
        float explosionY = this.getY() - 60;

        Explosion explosion = new Explosion(new Point(explosionX, explosionY));
        map.addEnemy(explosion);

        this.mapEntityStatus = Level.MapEntityStatus.REMOVED;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FLY_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 10)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 10)
                            .withScale(3)
                            .withBounds(2, 4, 20, 17)
                            .build()
            });
        }};
    }
}
