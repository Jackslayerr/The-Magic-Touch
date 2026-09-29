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

        // left 45 degree slope
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftSlopeTile);

        // right 45 degree slope
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightSlopeTile);

        // left 30 degree slope bottom
        Frame leftStairsBottomFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftStairsBottomTile = new MapTileBuilder(leftStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftStairsBottomTile);

        // left 30 degree slope top
        Frame leftStairsTopFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftStairsTopTile = new MapTileBuilder(leftStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftStairsTopTile);

        // right 30 degree slope bottom
        Frame rightStairsBottomFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightStairsBottomTile = new MapTileBuilder(rightStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomRight30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightStairsBottomTile);

        // right 30 degree slope top
        Frame rightStairsTopFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightStairsTopTile = new MapTileBuilder(rightStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopRight30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightStairsTopTile);

        return mapTiles;
    }
}
