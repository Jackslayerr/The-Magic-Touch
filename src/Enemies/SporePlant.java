
package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class SporePlant extends Enemy {

    // Sprite settings
    private static final String IMAGE_NAME = "plantEnemy.png";
    private static final int FRAME_SIZE = 24;
    private static final float SCALE = 4.0f;

    // Health settings
    private static final int MAX_HEALTH = 3;
    private int currentHealth = MAX_HEALTH;

    // Attack settings
    private static final int ATTACK_COOLDOWN = 114;
    private static final int MAX_SPORES = 16;

    private int cooldown = ATTACK_COOLDOWN;
    private int lastAttackFrame = -1;
    private boolean sporesSpawned = false;

    public SporePlant(Point location) {

        super(location.x, location.y,
                new SpriteSheet(
                        ImageLoader.load(IMAGE_NAME),
                        FRAME_SIZE,
                        FRAME_SIZE
                ),
                "IDLE");

        initialize();
    }

    @Override
    public void update(Player player) {

        super.update(player);

        if (map == null ||
                mapEntityStatus == MapEntityStatus.REMOVED) {
            return;
        }

        // Handle attack animation
        if (currentAnimationName.equals("ATTACK")) {

            int frame = getCurrentFrameIndex();

            // Fourth original spritesheet frame
            // is index 2 in the ATTACK animation
            if (frame == 2 &&
                    lastAttackFrame != 2 &&
                    !sporesSpawned) {

                spawnSpores();
                sporesSpawned = true;
            }

            // Attack animation completed
            if (frame == 0 && lastAttackFrame == 2) {

                setCurrentAnimationName("IDLE");

                cooldown = ATTACK_COOLDOWN;
                sporesSpawned = false;
                lastAttackFrame = -1;

                return;
            }

            lastAttackFrame = frame;

        } else {

            // Wait before next attack
            if (cooldown > 0) {
                cooldown--;
            } else {

                lastAttackFrame = -1;
                sporesSpawned = false;

                setCurrentAnimationName("ATTACK");
            }
        }
    }

    // Spawn falling spores
    private void spawnSpores() {

        int activeSpores = 0;

        ArrayList<Enemy> enemies = map.getEnemies();

        for (Enemy enemy : enemies) {

            if (enemy instanceof FallingSpore &&
                    enemy.getMapEntityStatus() == MapEntityStatus.ACTIVE) {

                activeSpores++;
            }
        }

        if (activeSpores >= MAX_SPORES) {
            return;
        }

        // Horizontal spread
        float[] horizontalSpeeds = {
                -2.5f, -1.5f, -0.5f,
                 0.5f,  1.5f,  2.5f
        };

        // Initial upward velocities
        float[] upwardSpeeds = {
                -7.0f, -8.0f, -6.5f,
                -7.5f, -8.0f, -7.0f
        };

        // Spawn from the center of the plant
        int plantCenterX = Math.round(
                getX() + getWidth() / 2.0f
        );

        int spawnY = Math.round(getY() - 4);

        for (int i = 0; i < horizontalSpeeds.length; i++) {

            if (activeSpores >= MAX_SPORES) {
                break;
            }

            FallingSpore spore = new FallingSpore(
                    new Point(plantCenterX, spawnY),
                    horizontalSpeeds[i],
                    upwardSpeeds[i]
            );

            map.addEnemy(spore);

            activeSpores++;
        }

        System.out.println("SPORE PLANT RELEASED SPORES!");
    }

    // Plant takes damage from player attacks
    public void takeDamage(int damage) {

        currentHealth -= damage;

        System.out.println(
                "SPORE PLANT HEALTH: " +
                Math.max(0, currentHealth) +
                "/" + MAX_HEALTH
        );

        if (currentHealth <= 0) {

            currentHealth = 0;

            setMapEntityStatus(MapEntityStatus.REMOVED);

            System.out.println("SPORE PLANT DEFEATED!");
        }
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    // Damage dealt when player touches the plant
    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(
            SpriteSheet spriteSheet) {

        return new HashMap<String, Frame[]>() {{

            // Closed plant
            put("IDLE", new Frame[] {

                    new FrameBuilder(
                            spriteSheet.getSprite(0, 0), 0
                    )
                            .withScale(SCALE)
                            .withBounds(4, 8, 16, 14)
                            .build()
            });

            // Attack animation
            put("ATTACK", new Frame[] {

                    // Frame 2: Warning
                    new FrameBuilder(
                            spriteSheet.getSprite(0, 1), 12
                    )
                            .withScale(SCALE)
                            .withBounds(4, 8, 16, 14)
                            .build(),

                    // Frame 3: Opening
                    new FrameBuilder(
                            spriteSheet.getSprite(0, 2), 12
                    )
                            .withScale(SCALE)
                            .withBounds(4, 8, 16, 14)
                            .build(),

                    // Frame 4: Spore release
                    new FrameBuilder(
                            spriteSheet.getSprite(0, 3), 12
                    )
                            .withScale(SCALE)
                            .withBounds(4, 8, 16, 14)
                            .build()
            });

        }};
    }
}
