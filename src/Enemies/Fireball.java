
package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.SoundEffect;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;
import EnhancedMapTiles.BurnableVine;
import EnhancedMapTiles.BurnableVineAnimation;
import EnhancedMapTiles.MagmaBlock;

import java.util.ArrayList;
import java.util.HashMap;
import NPCs.Torch;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out.
// It travels in a straight line along the x-axis and disappears after a set time.
public class Fireball extends Enemy {

    private float movementSpeed;
    private int existenceFrames;
    private boolean fromPlayer;
    private Direction direction;
    ArrayList<Enemy> enemies;
    ArrayList<NPC> npcs;
    private SoundEffect hitSound;

    public Fireball(Point location, float movementSpeed, int existenceFrames,
                    boolean fromPlayer, Direction direction) {

        super(location.x, location.y,
                new SpriteSheet(ImageLoader.load("newfireball2.png"), 15, 15),
                "RIGHT");

        this.movementSpeed = movementSpeed;
        this.existenceFrames = existenceFrames;
        this.fromPlayer = fromPlayer;
        this.direction = direction;

        hitSound = new SoundEffect();
        hitSound.load("/Audio/FIREBALL_HIT_THING.wav");

        if (direction == Direction.LEFT) {
            currentAnimationName = fromPlayer ? "LEFT" : "LEFT_LAVA";
        } else {
            currentAnimationName = fromPlayer ? "RIGHT" : "RIGHT_LAVA";
        }

        initialize();
    }

    protected Fireball(Point location, float movementSpeed, int existenceFrames,
                       boolean fromPlayer, Direction direction,
                       String imageName, int frameWidth, int frameHeight) {

        super(location.x, location.y,
                new SpriteSheet(ImageLoader.load(imageName), frameWidth, frameHeight),
                "LEFT_LAVA");

        this.movementSpeed = movementSpeed;
        this.existenceFrames = existenceFrames;
        this.fromPlayer = fromPlayer;
        this.direction = direction;

        if (direction == Direction.LEFT) {
            currentAnimationName = fromPlayer ? "LEFT" : "LEFT_LAVA";
        } else {
            currentAnimationName = fromPlayer ? "RIGHT" : "RIGHT_LAVA";
        }

        initialize();
    }

    @Override
    public void update(Player player) {

        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;

        } else {

            moveXHandleCollision(movementSpeed);

            enemies = map.getEnemies();
            npcs = map.getNPCs();

            for (Enemy enemy : enemies) {

                if (fromPlayer && this.intersects(enemy) && this != enemy) {

                    // IMMUNE ENEMIES
                    // Only the regular Mawtrap and horizontal Mawtrap
                    // are newly protected here.
                    if (enemy instanceof LavaMonster
                            || enemy instanceof Spike
                            || enemy instanceof SnowBallBoss
                            || enemy instanceof LavaSlime
                            || enemy instanceof ElectricZap
                            || enemy instanceof ElectricZapDown
                            || enemy instanceof badOrb
                            || enemy instanceof StoneGolem
                            || enemy instanceof Mawtrap
                            || enemy instanceof MawtrapHorizontal) {

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }

                    // SPORE PLANT
                    // Each player fireball deals 1 damage.
                    // The plant takes 3 fireballs to defeat.
                    if (enemy instanceof SporePlant) {

                        if (hitSound != null) {
                            hitSound.play();
                        }

                        ((SporePlant) enemy).takeDamage(1);

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }

                    // DRAGON
                    if (enemy instanceof DragonEnemy) {

                        if (hitSound != null) {
                            hitSound.play();
                        }

                        ((DragonEnemy) enemy).takeDamage(1.0f);

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }

                    // LAVA MONSTER OR SPIKE
                    if (enemy instanceof LavaMonster
                            || enemy instanceof Spike
                            || enemy instanceof SnowBallBoss
                            || enemy instanceof LavaSlime
                            || enemy instanceof ElectricZap
                            || enemy instanceof badOrb) {

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }

                    // SNOW GOBLIN
                    if (enemy instanceof SnowGoblinEnemy) {

                        if (hitSound != null) {
                            hitSound.play();
                        }

                        enemy.setMapEntityStatus(MapEntityStatus.REMOVED);

                        // Replace the snow goblin with a normal goblin.
                        GoblinEnemy goblin = new GoblinEnemy(
                                enemy.getLocation(),
                                Direction.LEFT
                        );

                        map.addEnemy(goblin);

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;

                    } else {

                        // DEFAULT BEHAVIOR FOR ALL OTHER ENEMIES
                        if (hitSound != null) {
                            hitSound.play();
                        }

                        enemy.setMapEntityStatus(MapEntityStatus.REMOVED);

                        System.out.println("PLAYER FIREBALL DEFEATED ENEMY!");

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }
                }
            }

            // NPC COLLISIONS
            for (NPC npc : npcs) {

                if (npc instanceof Torch
                        && (this.intersects(npc) || this.touching(npc))) {

                    if (hitSound != null) {
                        hitSound.play();
                    }

                    npc.talkedTo = true;
                    this.setMapEntityStatus(MapEntityStatus.REMOVED);
                }
            }

            super.update(player);
        }

        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided,
                                     Direction direction,
                                     MapEntity entityCollidedWith) {

        // Remove fireball when it collides with something solid.
        if (hasCollided) {

            if (hitSound != null) {
                hitSound.play();
            }

            if (entityCollidedWith instanceof MagmaBlock) {

                this.mapEntityStatus = MapEntityStatus.REMOVED;

                LavaSlime lavaSlime = new LavaSlime(
                        entityCollidedWith.getLocation()
                );

                map.addEnemy(lavaSlime);
                entityCollidedWith.setMapEntityStatus(MapEntityStatus.REMOVED);

            } else if (entityCollidedWith instanceof BurnableVine) {

                this.mapEntityStatus = MapEntityStatus.REMOVED;

                BurnableVineAnimation burnableVineAnimation =
                        new BurnableVineAnimation(entityCollidedWith.getLocation());

                map.addEnhancedMapTile(burnableVineAnimation);
                entityCollidedWith.setMapEntityStatus(MapEntityStatus.REMOVED);

            } else {

                this.mapEntityStatus = MapEntityStatus.REMOVED;

                if (hitSound != null) {
                    hitSound.play();
                }
            }
        }
    }

    @Override
    public void touchedPlayer(Player player) {

        // Enemy fireballs hurt the player.
        // Player fireballs do not hurt the player.
        if (!fromPlayer) {
            super.touchedPlayer(player);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{

            put("RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(2)
                            .withBounds(0, 0, 7, 7)
                            .build()
            });

            put("LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(0, 0, 7, 7)
                            .build()
            });

            put("LEFT_LAVA", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(0, 0, 7, 7)
                            .build()
            });

            put("RIGHT_LAVA", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withBounds(0, 0, 7, 7)
                            .build()
            });

        }};
    }
}
