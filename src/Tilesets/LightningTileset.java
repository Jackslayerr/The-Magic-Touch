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

// This class represents a "lightning" tileset of standard tiles defined in the LightningTileset.png file
public class LightningTileset extends Tileset {

    public LightningTileset() {
        super(ImageLoader.load("LightningTileset.png"), 16, 16, 3);
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


        Frame topBrickFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder topBrickTile = new MapTileBuilder(topBrickFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topBrickTile);

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

        Frame backgroundFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder backgroundTile = new MapTileBuilder(backgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(backgroundTile);

        Frame windowFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder windowTile = new MapTileBuilder(windowFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(windowTile);

        Frame castleFloatingPlatformFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder castleFloatingPlatformTile = new MapTileBuilder(castleFloatingPlatformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(castleFloatingPlatformTile);

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

        // right 30 degree slope top
        Frame rightStairsTopFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightStairsTopTile = new MapTileBuilder(rightStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopRight30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightStairsTopTile);

        // right 30 degree slope bottom
        Frame rightStairsBottomFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightStairsBottomTile = new MapTileBuilder(rightStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomRight30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightStairsBottomTile);

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

        Frame gateFrame = new FrameBuilder(getSubImage(3,3))
                .withScale(tileScale)
                .build();

        MapTileBuilder gateTile = new MapTileBuilder(gateFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(gateTile);

        Frame bridgeFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder bridgeTile = new MapTileBuilder(bridgeFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bridgeTile);

        Frame bottomPillarWithBackgroundFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder bottomPillarWithBackgroundTile = new MapTileBuilder(bottomPillarWithBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(bottomPillarWithBackgroundTile);

        Frame middlePillarWithBackgroundFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder middlePillarWithBackgroundTile = new MapTileBuilder(middlePillarWithBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(middlePillarWithBackgroundTile);

        Frame topPillarWithBackgroundFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder topPillarWithBackgroundTile = new MapTileBuilder(topPillarWithBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(topPillarWithBackgroundTile);

        Frame bottomPillarWithoutBackgroundFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder bottomPillarWithoutBackgroundTile = new MapTileBuilder(bottomPillarWithoutBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(bottomPillarWithoutBackgroundTile);

        Frame middlePillarWithoutBackgroundFrame = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder middlePillarWithoutBackgroundTile = new MapTileBuilder(middlePillarWithoutBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(middlePillarWithoutBackgroundTile);

        Frame topPillarWithoutBackgroundFrame = new FrameBuilder(getSubImage(4, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder topPillarWithoutBackgroundTile = new MapTileBuilder(topPillarWithoutBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(topPillarWithoutBackgroundTile);







        Frame cloudFloorFrame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudFloorTile = new MapTileBuilder(cloudFloorFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudFloorTile);


        Frame cloudCeilingFrame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudCeilingTile = new MapTileBuilder(cloudCeilingFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudCeilingTile);


        Frame cloudRegularBrickFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudRegularBrickTile = new MapTileBuilder(cloudRegularBrickFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudRegularBrickTile);


        Frame cloudInvertedBrickFrame = new FrameBuilder(getSubImage(5, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudInvertedBrickTile = new MapTileBuilder(cloudInvertedBrickFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudInvertedBrickTile);


        Frame cloudTopBrickFrame = new FrameBuilder(getSubImage(5, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudTopBrickTile = new MapTileBuilder(cloudTopBrickFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudTopBrickTile);

        Frame cloudMagentaFrame = new FrameBuilder(getSubImage(5, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudMagentaTile = new MapTileBuilder(cloudMagentaFrame);

        mapTiles.add(cloudMagentaTile);

        Frame cloudTopLeftCornerFrame = new FrameBuilder(getSubImage(6, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudTopLeftCornerTile = new MapTileBuilder(cloudTopLeftCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudTopLeftCornerTile);

        Frame cloudTopRightCornerFrame = new FrameBuilder(getSubImage(6, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudTopRightCornerTile = new MapTileBuilder(cloudTopRightCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudTopRightCornerTile);

        Frame cloudLeftWallFrame = new FrameBuilder(getSubImage(6, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudLeftWallTile = new MapTileBuilder(cloudLeftWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudLeftWallTile);

        Frame cloudRightWallFrame = new FrameBuilder(getSubImage(6, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudRightWallTile = new MapTileBuilder(cloudRightWallFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudRightWallTile);

        Frame cloudBottomLeftCornerFrame = new FrameBuilder(getSubImage(6, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudBottomLeftCornerTile = new MapTileBuilder(cloudBottomLeftCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudBottomLeftCornerTile);

        Frame cloudBottomRightCornerFrame = new FrameBuilder(getSubImage(6, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudBottomRightCornerTile = new MapTileBuilder(cloudBottomRightCornerFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudBottomRightCornerTile);

        Frame cloudBackgroundFrame = new FrameBuilder(getSubImage(7, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudBackgroundTile = new MapTileBuilder(cloudBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudBackgroundTile);

        Frame cloudWindowFrame = new FrameBuilder(getSubImage(7, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudWindowTile = new MapTileBuilder(cloudWindowFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudWindowTile);

        Frame cloudLightningFloatingPlatformFrame = new FrameBuilder(getSubImage(7, 2))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder cloudLightningFloatingPlatformTile = new MapTileBuilder(cloudLightningFloatingPlatformFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(cloudLightningFloatingPlatformTile);

        // left 30 degree slope bottom
        Frame cloudLeftStairsBottomFrame = new FrameBuilder(getSubImage(7, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudLeftStairsBottomTile = new MapTileBuilder(cloudLeftStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(cloudLeftStairsBottomTile);

        // left 30 degree slope top
        Frame cloudLeftStairsTopFrame = new FrameBuilder(getSubImage(7, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudLeftStairsTopTile = new MapTileBuilder(cloudLeftStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(cloudLeftStairsTopTile);

        // right 30 degree slope top
        Frame cloudRightStairsTopFrame = new FrameBuilder(getSubImage(7, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudRightStairsTopTile = new MapTileBuilder(cloudRightStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopRight30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(cloudRightStairsTopTile);

        // right 30 degree slope bottom
        Frame cloudRightStairsBottomFrame = new FrameBuilder(getSubImage(8, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudRightStairsBottomTile = new MapTileBuilder(cloudRightStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomRight30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(cloudRightStairsBottomTile);

        // left 45 degree slope
        Frame cloudLeftSlopeFrame = new FrameBuilder(getSubImage(8, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudLeftSlopeTile = new MapTileBuilder(cloudLeftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(cloudLeftSlopeTile);

        // right 45 degree slope
        Frame cloudRightSlopeFrame = new FrameBuilder(getSubImage(8, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudRightSlopeTile = new MapTileBuilder(cloudRightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(cloudRightSlopeTile);

        Frame cloudGateFrame = new FrameBuilder(getSubImage(8,3))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudGateTile = new MapTileBuilder(cloudGateFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudGateTile);

        Frame cloudBridgeFrame = new FrameBuilder(getSubImage(8, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudBridgeTile = new MapTileBuilder(cloudBridgeFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(cloudBridgeTile);

        Frame cloudBottomPillarWithBackgroundFrame = new FrameBuilder(getSubImage(9, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudBottomPillarWithBackgroundTile = new MapTileBuilder(cloudBottomPillarWithBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudBottomPillarWithBackgroundTile);

        Frame cloudMiddlePillarWithBackgroundFrame = new FrameBuilder(getSubImage(9, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudMiddlePillarWithBackgroundTile = new MapTileBuilder(cloudMiddlePillarWithBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudMiddlePillarWithBackgroundTile);

        Frame cloudTopPillarWithBackgroundFrame = new FrameBuilder(getSubImage(9, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudTopPillarWithBackgroundTile = new MapTileBuilder(cloudTopPillarWithBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudTopPillarWithBackgroundTile);

        Frame cloudBottomPillarWithoutBackgroundFrame = new FrameBuilder(getSubImage(9, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudBottomPillarWithoutBackgroundTile = new MapTileBuilder(cloudBottomPillarWithoutBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudBottomPillarWithoutBackgroundTile);

        Frame cloudMiddlePillarWithoutBackgroundFrame = new FrameBuilder(getSubImage(9, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudMiddlePillarWithoutBackgroundTile = new MapTileBuilder(cloudMiddlePillarWithoutBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudMiddlePillarWithoutBackgroundTile);

        Frame cloudTopPillarWithoutBackgroundFrame = new FrameBuilder(getSubImage(9, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder cloudTopPillarWithoutBackgroundTile = new MapTileBuilder(cloudTopPillarWithoutBackgroundFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(cloudTopPillarWithoutBackgroundTile);

        return mapTiles;
    }
}
