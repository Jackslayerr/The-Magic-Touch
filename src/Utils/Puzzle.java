package Utils;
import Level.Map;
import Level.MapTile;

public class Puzzle {
    public static void getRidOfTiles(int x1, int x2, int y1, int y2, Map map) {
        MapTile changeToTile = map.getMapTile(0, 0);
        for (int i=y1;i<y2+1;i++) {
            for (int j=x1;j<x2+1;j++) {
                map.setMapTile(j, i, changeToTile);
            }
        }
    }
    public static void addTiles(int x1, int x2, int y1, int y2, int tileNum, Map map) {
        MapTile currentTile;
        MapTile tileBuilt;
        for (int i=y1;i<y2+1;i++) {
            for (int j=x1;j<x2+1;j++) {
                currentTile = map.getMapTile(j, i);
                tileBuilt = map.getTileset().getTile(tileNum).build(currentTile.getX(), currentTile.getY());
                tileBuilt.setMap(map);
                map.setMapTile(j, i, tileBuilt);
            }
        }

    }
}
