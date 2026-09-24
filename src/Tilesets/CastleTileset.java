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


        Frame brick1Frame = new FrameBuilder(getSubImage(0,2))
                .withScale(tileScale)
                .build();

        MapTileBuilder brick1Tile = new MapTileBuilder(brick1Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brick1Tile);


        Frame brick2Frame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder brick2Tile = new MapTileBuilder(brick2Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brick2Tile);


        Frame brick3Frame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder brick3Tile = new MapTileBuilder(brick3Frame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brick3Tile);

        Frame magentaFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder magentaTile = new MapTileBuilder(magentaFrame);

        mapTiles.add(magentaTile);


        return mapTiles;
    }
}
