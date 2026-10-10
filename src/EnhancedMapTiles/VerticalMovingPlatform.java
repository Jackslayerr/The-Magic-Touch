package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Rectangle;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Direction;
import Utils.Point;

import java.awt.image.BufferedImage;

// This class is for a vertical moving platform
// the platform will move back and forth between its start location and end location
// if the player is standing on top of it, the player will be moved the same amount as the platform is moving (so the platform will not slide out from under the player)
public class VerticalMovingPlatform extends EnhancedMapTile {
    private Point startLocation;
    private Point endLocation;
    private int movementSpeed = 1;
    private Direction startDirection;
    private Direction direction;

    public VerticalMovingPlatform(BufferedImage image, Point startLocation, Point endLocation, TileType tileType, float scale, Rectangle bounds, Direction startDirection) {
        super(startLocation.x, startLocation.y, new FrameBuilder(image).withBounds(bounds).withScale(scale).build(), tileType);
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startDirection = startDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        direction = startDirection;
    }

    @Override
    public void update(Player player) {
        float gap = getBounds().getY1() - player.getBounds().getY2();
        boolean playerOnTop = ((gap >= 0 && gap <= 2) && player.getBounds().getX2() > getBounds().getX1() && player.getBounds().getX1() < getBounds().getX2());

        int move = (direction == Direction.DOWN) ? movementSpeed : -movementSpeed;
        moveY(move);

        if (getY() >= endLocation.y) {
            direction = Direction.UP;
        } else if (getY() <= startLocation.y) {
            direction = Direction.DOWN;
        }
        
        if (playerOnTop) {
            player.moveY(move);
        }

        super.update(player);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

}
