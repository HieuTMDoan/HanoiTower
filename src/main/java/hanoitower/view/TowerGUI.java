package hanoitower.view;

import hanoitower.model.Tower;
import hanoitower.utilties.TransitionManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    @FXML
    private final ObservableList<Node> myChildrenNodes = this.getChildren();

    @FXML
    private DiskGUI myPoppedDisk;

    private int myTowerID;

    public TowerGUI(final int theTowerID) {
        myTowerID = theTowerID;

        Rectangle myBase = new Rectangle(BASE_WIDTH, BASE_HEIGHT);
        myBase.setFill(Color.BLACK);

        Rectangle myPole = new Rectangle(POLE_WIDTH, POLE_HEIGHT);
        myPole.setFill(Color.BLACK);
        myPole.setTranslateY(-BASE_HEIGHT);

        super.getChildren().addAll(myBase, myPole);
        super.setAlignment(Pos.BOTTOM_CENTER);
    }

    public TowerGUI(final Tower theTower) {
        if (theTower != null) {
            new TowerGUI(theTower.getTowerID());
        }
    }

    public void addDisk(final DiskGUI theDisk) {
        TransitionManager.playFadeIn(theDisk);
        myChildrenNodes.add(theDisk);
        myPoppedDisk = theDisk;
    }

    public void addAllDisks(final DiskGUI... theDisks) {
        Arrays.stream(theDisks).forEach(theDisk -> {
            this.addDisk(theDisk);
            this.pushDisk();
        });
    }

    public void removeAllDisks() {
        myChildrenNodes.removeIf(DiskGUI.class::isInstance);
    }

    public void removeDisk() {
        if (this.getDiskCount() > 0) {
            TransitionManager.playFadeOut(myPoppedDisk);
            myChildrenNodes.remove(myPoppedDisk);
            myPoppedDisk = (this.getDiskCount() > 0) ? this.peekDisk() : null;
        }
    }

    public DiskGUI peekDisk() {
        return (this.getDiskCount() > 0) ? (DiskGUI) myChildrenNodes.get(myChildrenNodes.size() - 1) : null;
    }

    public DiskGUI popDisk() {
//        myPoppedDisk.setTranslateY(-POP_DISK_TRANSLATE_Y);
        TransitionManager.playSlideUp(myPoppedDisk, POP_DISK_TRANSLATE_Y);
        myPoppedDisk.setPopped(true);
        return myPoppedDisk;
    }

    public void pushDisk() {
        double translateY = -BASE_HEIGHT * (this.getDiskCount());
//        myPoppedDisk.setTranslateY(translateY);
        TransitionManager.playSlideDown(myPoppedDisk, translateY);
        myPoppedDisk.setPopped(false);
    }

    public int getDiskCount() {
        return myChildrenNodes.size() - DEFAULT_CHILDREN_SIZE;
    }

    public Tower getTower() {
        return new Tower(this.getDiskCount(), myTowerID);
    }
}