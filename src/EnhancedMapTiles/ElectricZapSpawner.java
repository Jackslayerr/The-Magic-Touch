package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Enemies.snowball;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;
import Enemies.ElectricZap;
import Enemies.ElectricZapDown;

import java.util.HashMap;

public class ElectricZapSpawner extends EnhancedMapTile {
    private int timer = 0;

    public ElectricZapSpawner(Point location) {
        super(Math.round(location.x), Math.round(location.y), new SpriteSheet(ImageLoader.load("ElectricZapSpawner.png"), 16, 16), TileType.NOT_PASSABLE);
        
        updateCurrentFrame();
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (timer == 0) {
            spawnZap(true);
        }
        if (timer == 200) {
            spawnZap(false);
        }
        timer++;
    }

    public void spawnZap(boolean up) {
        // logic to shoot a zap
        if (up) { 
            int zapX = Math.round(getX() - 8);
            int zapY = Math.round(getY() - 191);

            // create zap enemy
            ElectricZap electricZap = new ElectricZap(new Point(zapX, zapY));

            // add zap enemy to the map for it to spawn in the level
            map.addEnemy(electricZap);
        }
        if (!up) {
            int zapX = Math.round(getX() - 8);
            int zapY = Math.round(getY());

            // create zap enemy
            ElectricZapDown electricZapDown = new ElectricZapDown(new Point(zapX, zapY));

            // add zap enemy to the map for it to spawn in the level
            map.addEnemy(electricZapDown);
        }
    }



    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 20)
                            .withScale(3)
                            .withBounds(0, 0, 16, 16)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 20)
                            .withScale(3)
                            .withBounds(0, 0, 16, 16)
                            .build(),
                });
            }
        };}}