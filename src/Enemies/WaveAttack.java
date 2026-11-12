package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.SoundEffect;
import EnhancedMapTiles.IceBlock;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.*;
import NPCs.Torch;
import Utils.Direction;
import Utils.Point;
import EnhancedMapTiles.MagmaBlock;
import EnhancedMapTiles.Vine;
import EnhancedMapTiles.VineSprout;

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
    ArrayList<EnhancedMapTile> enhancedMapTiles;
    private SoundEffect waveSound;
    private boolean hasPlayedSound = false;
    public WaveAttack(Point location, float movementSpeed, int existenceFrames, boolean fromPlayer, Player player) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("newwave.png"), 20, 20), "RIGHT");
        this.movementSpeed = movementSpeed;

        // how long the wave will exist for before disappearing
        this.existenceFrames = existenceFrames;
        // if the wave was shot by the player
        this.fromPlayer = fromPlayer;

        waveSound = new SoundEffect();
        waveSound.load("/Audio/WET.wav");
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
            // determine move direction and amount
            float moveAmount = (facingDirection == Direction.RIGHT) ? movementSpeed : -movementSpeed;
            // move WAVE forward
            moveXHandleCollision(movementSpeed);
            enemies = map.getEnemies();
            enhancedMapTiles = map.getEnhancedMapTiles();
            npcs = map.getNPCs();
            for (Enemy enemy : enemies) {
                if (fromPlayer && this.intersects(enemy) && this != enemy) {
                    if (enemy instanceof LavaMonster) { // Lava Monster dies on impact
                        if (waveSound != null) {
                            waveSound.play();
                            hasPlayedSound = true;// Play impact sound when hitting walls/blocks
                        }
                        enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                        System.out.println("PLAYER WAVE DEFEATED LAVA MONSTER!");
                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;

                    }
                    if (enemy instanceof LavaSlime) { // Lava Slime replaced by magma block
                        if (waveSound != null) {
                            waveSound.play();
                            hasPlayedSound = true; // Play impact sound when hitting walls/blocks
                        }
                        enemy.setMapEntityStatus(MapEntityStatus.REMOVED);

                        MagmaBlock magmaBlock = new MagmaBlock(enemy.getLocation());
                        map.addEnhancedMapTile(magmaBlock);

                        System.out.println("PLAYER WAVE DEFEATED LAVA SLIME!");

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    } 
                    else if (enemy.canBePushed == true) {
                        if (waveSound != null && !hasPlayedSound) {
                            waveSound.play();
                            hasPlayedSound = true;// Play impact sound when hitting walls/blocks
                        }
                        // OTHER ENEMIES GET PUSHED BACK


                        // push enemy in direction wave is moving
                        float pushDistance = 3.0f;

                        if (facingDirection == Direction.RIGHT) {
                            enemy.moveXHandleCollision(pushDistance);
                        } else {
                            enemy.moveXHandleCollision(-pushDistance);
                        }
                    }
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
            if (entityCollidedWith instanceof VineSprout) {
                this.mapEntityStatus = MapEntityStatus.REMOVED;
                Vine vine = new Vine(entityCollidedWith.getLocation().subtractY(222).subtractX(24));
                map.addEnhancedMapTile(vine);
                entityCollidedWith.setMapEntityStatus(MapEntityStatus.REMOVED);
            } else {
                this.mapEntityStatus = MapEntityStatus.REMOVED;
            }
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
                            .withScale(2)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
            put("LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
