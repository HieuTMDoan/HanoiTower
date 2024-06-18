package hanoitower.view;

import hanoitower.model.HanoiTower;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DiskGUI extends Rectangle {
    private final static int DISK_ARC = 20;

    public final static int DISK_HEIGHT = 30;

    private final static int WIDTH_DIFFERENCE = 30;

    private final static int MINIMUM_DISK_WIDTH = 90;

    private final int myDiskID;

    public DiskGUI(final int theDiskID, final Color theColor) {
        super(MINIMUM_DISK_WIDTH + (WIDTH_DIFFERENCE * (theDiskID)), DISK_HEIGHT);
        super.setFill(theColor);
        super.setArcWidth(DISK_ARC);
        super.setArcHeight(DISK_ARC);
        super.setTranslateY(-DISK_HEIGHT);

        myDiskID = theDiskID;
    }

    public int getDiskID() {
        return myDiskID;
    }
}
