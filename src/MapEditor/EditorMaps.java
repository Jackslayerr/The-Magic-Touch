package MapEditor;

import Level.Map;
import Maps.LevelOneMap;
import Maps.LevelTwoMap;
import Maps.MovementTestMap;
import Maps.SprintOneTestMap;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("SprintOneTestMap");
            add("MovementTestMap");
            add("LevelOneMap");
            add("LevelTwoMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "SprintOneTestMap":
                return new SprintOneTestMap();
            case "MovementTestMap":
                return new MovementTestMap();
            case "LevelOneMap":
                return new LevelOneMap();
            case "LevelTwoMap":
                return new LevelTwoMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
