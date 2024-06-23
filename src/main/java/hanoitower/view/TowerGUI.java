package hanoitower.view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

import static hanoitower.view.DiskGUI.DISK_HEIGHT;

public class TowerGUI extends StackPane {
    private static final int BASE_WIDTH = 300;

    private static final int BASE_HEIGHT = DISK_HEIGHT;

    private static final int POLE_WIDTH = BASE_HEIGHT - 5;

    private static final int POLE_HEIGHT = BASE_WIDTH - 40;

    private static final int POP_DISK_TRANSLATE_Y = POLE_HEIGHT + BASE_HEIGHT + POLE_WIDTH;

    private static final int DEFAULT_CHILDREN_SIZE = 2;

    public final ObservableList<Node> myChildrenNodes = this.getChildren();

    private DiskGUI myPoppedDisk;

    public TowerGUI() {
        Rectangle myBase = new Rectangle(BASE_WIDTH, BASE_HEIGHT);
        myBase.setFill(Color.BLACK);

        Rectangle myPole = new Rectangle(POLE_WIDTH, POLE_HEIGHT);
        myPole.setFill(Color.BLACK);
        myPole.setTranslateY(-BASE_HEIGHT);

        super.getChildren().addAll(myBase, myPole);
        super.setAlignment(Pos.BOTTOM_CENTER);
        super.setFocusTraversable(true);
    }

    public void addDisk(final DiskGUI theDisk) {
        double translateY = -BASE_HEIGHT * (this.getChildren().size() - 1);
        theDisk.setTranslateY(translateY);
        myChildrenNodes.add(theDisk);
        myPoppedDisk = theDisk;
    }

    public DiskGUI popDisk() {
        if (myChildrenNodes.size() > DEFAULT_CHILDREN_SIZE && !myPoppedDisk.isPopped()) {
            double distanceToPeak = -POP_DISK_TRANSLATE_Y;
            myPoppedDisk.setTranslateY(distanceToPeak);
            myPoppedDisk.setPopped(true);
            return myPoppedDisk;
        }
        return null;
    }

    public void pushDisk() {
        if (myPoppedDisk.isPopped()) {
            double translateY = -BASE_HEIGHT * (this.getDiskCount());
            myPoppedDisk.setTranslateY(translateY);
            myPoppedDisk.setPopped(false);
        }
    }

    public void addAllDisks(final DiskGUI... theDisks) {
        Arrays.stream(theDisks).forEach(this::addDisk);
    }

    public void removeAllDisks() {
        myChildrenNodes.removeIf(DiskGUI.class::isInstance);
    }

    public void removeDisk() {
        if (myChildrenNodes.size() > DEFAULT_CHILDREN_SIZE) {
            myChildrenNodes.remove(myPoppedDisk);
        }
    }

    public int getDiskCount() {
        return myChildrenNodes.size() - DEFAULT_CHILDREN_SIZE;
    }
}