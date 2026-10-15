package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import EnhancedMapTiles.IceBlock;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.*;
import NPCs.Torch;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;


// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class WaveAttack extends Enemy {
    private float movementSpeed;
    private int existenceFrames;
    private boolean fromPlayer;
    private Direction facingDirection;
    ArrayList<Enemy> enemies;
    ArrayList<NPC> npcs;
    public WaveAttack(Point location, float movementSpeed, int existenceFrames, boolean fromPlayer, Player player) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("newwave.png"), 20, 20), "RIGHT");
        this.movementSpeed = movementSpeed;

        // how long the wave will exist for before disappearing
        this.existenceFrames = existenceFrames;
        // if the wave was shot by the player
        this.fromPlayer = fromPlayer;
        // Grabs map npcs
        facingDirection = player.getFacingDirection();
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "RIGHT";
        } else {
            currentAnimationName = "LEFT";
        }
        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward
            moveXHandleCollision(movementSpeed);
            enemies = map.getEnemies();
            npcs = map.getNPCs();
            for (Enemy enemy : enemies) {
                if (fromPlayer && this.intersects(enemy) && this != enemy) {
                    enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                   // player.healPlayer();
                    System.out.println("PLAYER WAVE DEFEATED ENEMY!");
                    this.setMapEntityStatus(MapEntityStatus.REMOVED);
                 }
            }
            super.update(player);
            
        }
        
        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (hasCollided && !(entityCollidedWith instanceof IceBlock)) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void touchedPlayer(Player player) {
        // if fireball touches player, it disappears, unless the fireball is from the player
        if (!fromPlayer) {
            super.touchedPlayer(player);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }
    public Direction getDirection() {
        return facingDirection;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
            put("LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
