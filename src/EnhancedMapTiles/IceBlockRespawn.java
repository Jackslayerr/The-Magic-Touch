package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.*;
import Level.*;
import Enemies.*;

public class IceBlockRespawn extends EnhancedMapTile {
    ArrayList<Enemy> enemies;
    ArrayList<EnhancedMapTile> emts;
    Direction direction;
    Point location;
    boolean addBlock = false;
    boolean iceOnBlock = false;
    IceBlock iceBlock;
    public IceBlockRespawn(Point location) {
        //super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceBlock.png"), 16, 16), TileType.NOT_PASSABLE);
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("iceBlockRespawn.png"), 16, 16), TileType.PASSABLE);
        this.location = location;
    }
    public void checkBlockSpawn() {
        emts = map.getEnhancedMapTiles();
        enemies = map.getEnemies();
        iceOnBlock = false;
        for (EnhancedMapTile emt : emts) {
            if (emt instanceof IceBlock) {
               if (emt.touching(this) || emt.intersects(this)) {
                    iceOnBlock = true;
               }
            }
        }
        if (iceOnBlock == false) {
            for (Enemy enemy : enemies) {
                if (enemy instanceof WaveAttack && enemy.touching(this)) {
                    iceBlock = new IceBlock(location);
                    enemy.setMapEntityStatus(MapEntityStatus.REMOVED);
                    addBlock = true;
                }
            }
            if (addBlock) {
                map.addEnhancedMapTile(iceBlock);
                addBlock = false;
            }
        }
        
    }
    @Override
    public void update(Player player) {
        super.update(player);
        checkBlockSpawn();
    }

    @Override
    public void initialize() {
        super.initialize();
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
                new FrameBuilder(spriteSheet.getSprite(0, 2), 20)
                        .withScale(3)
                        .withBounds(0, 0, 16, 16)
                        .build()
            });

            }
        };
    
}}


