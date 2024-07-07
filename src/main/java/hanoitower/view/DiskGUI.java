package hanoitower.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DiskGUI extends Rectangle {
    private final static int DISK_ARC = 20;

    public final static int DISK_HEIGHT = 30;

    private final static int WIDTH_DIFFERENCE = 30;

    private final static int MINIMUM_DISK_WIDTH = 90;

    private boolean myPoppedState;

    public DiskGUI(final int theIndex, final Color theColor) {
        super(MINIMUM_DISK_WIDTH + (WIDTH_DIFFERENCE * (theIndex)), DISK_HEIGHT);
        super.setFill(theColor);
        super.setArcWidth(DISK_ARC);
        super.setArcHeight(DISK_ARC);
        super.setTranslateY(-DISK_HEIGHT);

        myPoppedState = false;
    }

    void setPopped(final boolean thePopped) {
        myPoppedState = thePopped;
    }

    public boolean isPopped() {
        return myPoppedState;
    }
}
