package hanoitower.view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static hanoitower.view.DiskGUI.*;

public class TowerGUI extends StackPane {
    private static final int BASE_WIDTH = 300;

    private static final int BASE_HEIGHT = 30;

    private static final int POLE_WIDTH = BASE_HEIGHT - 10;

    private static final int POLE_HEIGHT = BASE_WIDTH - 20;

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
        if (this.getChildren().size() > 2) {
            theDisk.setTranslateY(-DISK_HEIGHT * (this.getChildren().size() - 1));
        }
        this.getChildren().add(theDisk);
    }

    public void addAllDisks(final DiskGUI... theDisks) {
        for (final DiskGUI disk : theDisks) {
            this.addDisk(disk);
        }
    }
}