package view.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DiskGUI extends Rectangle {
    private final static int DISK_ARC = 10;

    private final int myWidth;

    private final int myHeight;

    private final Color myColor;

    public DiskGUI(final int theWidth, final int theHeight, final Color theColor) {
        super(theWidth, theHeight);
        super.setFill(theColor);
        super.setArcWidth(DISK_ARC);
        super.setArcHeight(DISK_ARC);

        myWidth = theWidth;
        myHeight = theHeight;
        myColor = theColor;
    }
}
