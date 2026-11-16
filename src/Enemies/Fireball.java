
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
import NPCs.Torch;

import java.util.ArrayList;
import java.util.HashMap;

// This class is for fireballs shot by enemies or the player.
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

        if (existenceFrames <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {

            moveXHandleCollision(movementSpeed);

            enemies = map.getEnemies();
            npcs = map.getNPCs();

            for (Enemy enemy : enemies) {

                if (fromPlayer && this.intersects(enemy) && this != enemy) {

                    // SPORE PLANT
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

                    // ENEMIES THAT CANNOT BE DEFEATED BY PLAYER FIREBALLS
                    if (enemy instanceof LavaMonster ||
                            enemy instanceof Spike ||
                            enemy instanceof SnowBallBoss ||
                            enemy instanceof LavaSlime ||
                            enemy instanceof ElectricZap ||
                            enemy instanceof ElectricZapDown ||
                            enemy instanceof badOrb) {

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }

                    // SNOW GOBLIN
                    if (enemy instanceof SnowGoblinEnemy) {

                        if (hitSound != null) {
                            hitSound.play();
                        }

                        enemy.setMapEntityStatus(MapEntityStatus.REMOVED);

                        GoblinEnemy goblin = new GoblinEnemy(
                                enemy.getLocation(),
                                Direction.LEFT
                        );

                        map.addEnemy(goblin);

                        this.setMapEntityStatus(MapEntityStatus.REMOVED);
                        break;
                    }

                    // DEFAULT BEHAVIOR FOR OTHER ENEMIES
                    if (hitSound != null) {
                        hitSound.play();
                    }

                    enemy.setMapEntityStatus(MapEntityStatus.REMOVED);

                    System.out.println("PLAYER FIREBALL DEFEATED ENEMY!");

                    this.setMapEntityStatus(MapEntityStatus.REMOVED);
                    break;
                }
            }

            // TORCH INTERACTION
            for (NPC npc : npcs) {

                if (npc instanceof Torch &&
                        (this.intersects(npc) || this.touching(npc))) {

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

        if (hasCollided) {

            if (hitSound != null) {
                hitSound.play();
            }

            // MAGMA BLOCK
            if (entityCollidedWith instanceof MagmaBlock) {

                this.mapEntityStatus = MapEntityStatus.REMOVED;

                LavaSlime lavaSlime = new LavaSlime(
                        entityCollidedWith.getLocation()
                );

                map.addEnemy(lavaSlime);

                entityCollidedWith.setMapEntityStatus(
                        MapEntityStatus.REMOVED
                );

            // BURNABLE VINE
            } else if (entityCollidedWith instanceof BurnableVine) {

                this.mapEntityStatus = MapEntityStatus.REMOVED;

                BurnableVineAnimation burnableVineAnimation =
                        new BurnableVineAnimation(
                                entityCollidedWith.getLocation()
                        );

                map.addEnhancedMapTile(burnableVineAnimation);

                entityCollidedWith.setMapEntityStatus(
                        MapEntityStatus.REMOVED
                );

            } else {

                this.mapEntityStatus = MapEntityStatus.REMOVED;
            }
        }
    }

    @Override
    public void touchedPlayer(Player player) {

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
