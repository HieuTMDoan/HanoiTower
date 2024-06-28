package hanoitower.view;

import hanoitower.model.Tower;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    public TowerGUI() {
        Rectangle myBase = new Rectangle(BASE_WIDTH, BASE_HEIGHT);
        myBase.setFill(Color.BLACK);

        Rectangle myPole = new Rectangle(POLE_WIDTH, POLE_HEIGHT);
        myPole.setFill(Color.BLACK);
        myPole.setTranslateY(-BASE_HEIGHT);

        super.getChildren().addAll(myBase, myPole);
        super.setAlignment(Pos.BOTTOM_CENTER);
    }

    public void addDisk(final DiskGUI theDisk) {
        myChildrenNodes.add(theDisk);
        myPoppedDisk = theDisk;

//        FadeTransition fadeIn = new FadeTransition(TRANSITION_DURATION, theDisk);
//        fadeIn.setFromValue(0.0);
//        fadeIn.setToValue(1.0);
//        fadeIn.play();
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
            myChildrenNodes.remove(myPoppedDisk);
            myPoppedDisk = (this.getDiskCount() > 0) ? this.peekDisk() : null;
        }
//        if (this.getDiskCount() > 0) {
//            FadeTransition fadeOut = new FadeTransition(TRANSITION_DURATION, myPoppedDisk);
//            fadeOut.setFromValue(1.0);
//            fadeOut.setToValue(0.0);
//            fadeOut.setOnFinished(event -> {
//                myChildrenNodes.remove(myPoppedDisk);
//                myPoppedDisk = (this.getDiskCount() > 0) ? (DiskGUI) myChildrenNodes.get(myChildrenNodes.size() - 1) : null;
//            });
//            fadeOut.play();
//        }

    }

    public DiskGUI peekDisk() {
        return (this.getDiskCount() > 0) ? (DiskGUI) myChildrenNodes.get(myChildrenNodes.size() - 1) : null;
    }

    public DiskGUI popDisk() {
//        TranslateTransition translateTransition = new TranslateTransition(TRANSITION_DURATION, myPoppedDisk);
//        translateTransition.setToY(-POP_DISK_TRANSLATE_Y);
//        translateTransition.setOnFinished(event -> myPoppedDisk.setPopped(true));
//        translateTransition.play();
//
//        return myPoppedDisk;
        myPoppedDisk.setTranslateY(-POP_DISK_TRANSLATE_Y);
        myPoppedDisk.setPopped(true);
        return myPoppedDisk;
    }

    public void pushDisk() {
        double translateY = -BASE_HEIGHT * (this.getDiskCount());

//        TranslateTransition translateTransition = new TranslateTransition(TRANSITION_DURATION, myPoppedDisk);
//        translateTransition.setToY(translateY);
//        translateTransition.setOnFinished(event -> myPoppedDisk.setPopped(false));
//        translateTransition.play();

        myPoppedDisk.setTranslateY(translateY);
        myPoppedDisk.setPopped(false);
    }

    public int getDiskCount() {
        return myChildrenNodes.size() - DEFAULT_CHILDREN_SIZE;
    }

    public Tower getTower() {
        return new Tower(this.getDiskCount());
    }
}