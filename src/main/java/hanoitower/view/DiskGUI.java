package hanoitower.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DiskGUI extends Rectangle {
    private final static int DISK_ARC = 20;

    public final static int DISK_HEIGHT = 30;

    private final static int WIDTH_DIFFERENCE = 30;

    private final static int MINIMUM_DISK_WIDTH = 90;

    private final int myDiskID;

    private boolean myPopped;

    public DiskGUI(final int theDiskID, final Color theColor) {
        super(MINIMUM_DISK_WIDTH + (WIDTH_DIFFERENCE * (theDiskID)), DISK_HEIGHT);
        super.setFill(theColor);
        super.setArcWidth(DISK_ARC);
        super.setArcHeight(DISK_ARC);
        super.setTranslateY(-DISK_HEIGHT);

        myDiskID = theDiskID;
        myPopped = false;
    }

    public int getDiskID() {
        return myDiskID;
    }

    public void setPopped(final boolean thePopped) {
        myPopped = thePopped;
    }

    public boolean isPopped() {
        return myPopped;
    }
}
