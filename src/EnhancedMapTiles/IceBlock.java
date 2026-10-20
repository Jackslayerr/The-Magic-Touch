package EnhancedMapTiles;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Utils.*;
import Level.*;
import Enemies.*;

public class IceBlock extends EnhancedMapTile {
    ArrayList<Enemy> enemies;
    boolean move;
    int moveCount;
    Direction direction;
    public IceBlock(Point location) {
        //super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceBlock.png"), 16, 16), TileType.NOT_PASSABLE);
        super(location.x, location.y, new FrameBuilder(ImageLoader.load("IceBlock.png"))
              .withScale(3)
              .withBounds(0, 0, 16, 16)
              .build(), TileType.NOT_PASSABLE);
        move = false;
        moveCount = 0;
    }
    public void moveBlock() {
        enemies = map.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof WaveAttack && enemy.touching(this)) {
                WaveAttack wave = (WaveAttack) enemy;
                direction = wave.getDirection();
                move = true;
                enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
            } else if (enemy instanceof Fireball && enemy.touching(this)) {
                enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                this.setMapEntityStatus(MapEntityStatus.REMOVED);
                
            }
        }
        
    }
    @Override
    public void update(Player player) {
        super.update(player);
        if (move == true) {
            if (direction == Direction.RIGHT) {
                this.moveXHandleCollision(1);
            } else {
                this.moveXHandleCollision(-1);
            }
            moveCount += 1;
            if (moveCount == 48) {
                move = false;
                moveCount = 0;
            }
        } else {
            moveBlock();
        }
        moveYHandleCollision(3.0f);
    }

    @Override
    public void initialize() {
        super.initialize();
    }
}
