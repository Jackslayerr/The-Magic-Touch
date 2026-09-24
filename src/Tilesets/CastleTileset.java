package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;

import java.util.ArrayList;

// This class represents a "castle" tileset of standard tiles defined in the CastleTileset.png file
public class CastleTileset extends Tileset {

    public CastleTileset() {
        super(ImageLoader.load("CastleTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        Frame floorFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder floorTile = new MapTileBuilder(floorFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(floorTile);


        Frame ceilingFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder ceilingTile = new MapTileBuilder(ceilingFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(ceilingTile);


        Frame regularBrickFrame = new FrameBuilder(getSubImage(0,2))
                .withScale(tileScale)
                .build();

        MapTileBuilder regularBrickTile = new MapTileBuilder(regularBrickFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(regularBrickTile);


        Frame invertedBrickFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder invertedBrickTile = new MapTileBuilder(invertedBrickFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(invertedBrickTile);


        Frame placeholderFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder placeholderTile = new MapTileBuilder(placeholderFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(placeholderTile);

        Frame magentaFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder magentaTile = new MapTileBuilder(magentaFrame);

        mapTiles.add(magentaTile);

        Frame topLeftCornerFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder topLeftCornerTile = new MapTileBuilder(topLeftCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topLeftCornerTile);

        Frame topRightCornerFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder topRightCornerTile = new MapTileBuilder(topRightCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topRightCornerTile);

        Frame leftWallFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftWallTile = new MapTileBuilder(leftWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leftWallTile);

        Frame rightWallFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightWallTile = new MapTileBuilder(rightWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightWallTile);

        Frame bottomLeftCornerFrame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder bottomLeftCornerTile = new MapTileBuilder(bottomLeftCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomLeftCornerTile);

        Frame bottomRightCornerFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder bottomRightCornerTile = new MapTileBuilder(bottomRightCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomRightCornerTile);

        Frame rightCeilingFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightCeilingTile = new MapTileBuilder(rightCeilingFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightCeilingTile);

        Frame leftCeilingFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftCeilingTile = new MapTileBuilder(leftCeilingFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leftCeilingTile);

        Frame castleFloatingPlatformFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder castleFloatingPlatformTile = new MapTileBuilder(castleFloatingPlatformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(castleFloatingPlatformTile);

        return mapTiles;
    }
}
