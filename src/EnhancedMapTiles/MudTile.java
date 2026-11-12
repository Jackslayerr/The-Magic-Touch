package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.KeyLocker;
import Engine.Key;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

// This class is for the mud tile
public class MudTile extends EnhancedMapTile {

    public MudTile(Point location) {
        super(Math.round(location.x), Math.round(location.y), new SpriteSheet(ImageLoader.load("MudTile.png"), 32, 32), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.touching(this) ||  player.intersects(this)) {
            player.playerJumping(0);
        }
    }



    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 15)
                            .withScale(2)
                            .withBounds(0, 0, 32, 32)
                            .build()
                });
            }
        };}}