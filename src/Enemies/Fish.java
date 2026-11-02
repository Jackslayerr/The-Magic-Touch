package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.*;
import NPCs.Torch;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class Fish extends Enemy {
    private float movementSpeed;
    private int existenceFrames;
    private boolean fromPlayer;
    private Direction direction;
    ArrayList<Enemy> enemies;
    ArrayList<NPC> npcs;

    public Fish(Point location, float movementSpeed, int existenceFrames, boolean fromPlayer, Direction direction) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("fishspin.png"), 99, 148), "RIGHT");

        this.movementSpeed = movementSpeed;
        this.existenceFrames = existenceFrames;
        this.fromPlayer = fromPlayer;
        this.direction = direction;

        if (direction == Direction.LEFT) {
            currentAnimationName = "LEFT";
        } else {
            currentAnimationName = "RIGHT";
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
                    enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                    this.setMapEntityStatus(MapEntityStatus.REMOVED);
                    break;
                }
            }

            for (NPC npc : npcs) {
                if (npc instanceof Torch && (this.intersects(npc) || this.touching(npc))) {
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
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
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
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                            .withScale(0.3f)
                            .withBounds(10, 10, 80, 130)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(0.3f)
                            .withBounds(10, 10, 80, 130)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(0.3f)
                            .withBounds(10, 10, 80, 130)
                            .build()
            });
            put("LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                            .withScale(0.3f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 80, 130)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(0.3f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 80, 130)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(0.3f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(10, 10, 80, 130)
                            .build()
            });
        }};
    }
}