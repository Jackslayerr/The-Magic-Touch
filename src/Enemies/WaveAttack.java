package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
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
    ArrayList<Enemy> enemies;
    ArrayList<NPC> npcs;
    public WaveAttack(Point location, float movementSpeed, int existenceFrames, boolean fromPlayer) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("wave3.png"), 9, 9), "DEFAULT");
        this.movementSpeed = movementSpeed;

        // how long the fireball will exist for before disappearing
        this.existenceFrames = existenceFrames;
        
        // if the fireball was shot by the player
        this.fromPlayer = fromPlayer;
        // Grabs map npcs
        
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
                    player.healPlayer();
                    System.out.println("PLAYER FIREBALL DEFEATED ENEMY!");
                    this.setMapEntityStatus(MapEntityStatus.REMOVED);
                 }
            }
            for (NPC npc : npcs) {
                if (npc instanceof Torch && this.intersects(npc)) {
                    npc.talkedTo = true;
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
        if (hasCollided) {
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

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
