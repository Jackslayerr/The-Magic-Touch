package Utils;

import java.util.ArrayList;

public class CheckpointManager {
    private static final CheckpointManager instance = new CheckpointManager();
    ArrayList<Point> checkPointLists = new ArrayList<>();
    // Change This For Testing
    static int currentIndex = 3;
    private CheckpointManager() {
        checkPointLists.add(new Point(144.0f, 768.0f)); // Level 1 Start Index 0
        checkPointLists.add(new Point(3120.0f, 864.0f)); // Level 1 Checkpoint Index 1
        checkPointLists.add(new Point(96.0f, 1248.0f)); // Level 2 Start Index 2
        checkPointLists.add(new Point(48.0f, 768.0f)); // Level 3 Start Index 3

    }
    public static CheckpointManager getInstance() {
        return instance;
    }
    public Point getCheckPoint() {
        return checkPointLists.get(currentIndex);
    }
    public static void setIndex(int index) {
        currentIndex = index;
    }
    
}
