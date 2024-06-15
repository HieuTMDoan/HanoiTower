package view.GUI;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TowerGUI extends StackPane {
    private final int myBaseWidth;

    private final int myBaseHeight;

    private final int myPoleWidth;

    private final int myPoleHeight;

    private final int myPoleYTranslate;

    public TowerGUI(final int theWidth, final int theHeight, final int thePoleHeight) {
        myBaseWidth = theWidth;
        myBaseHeight = theHeight;
        myPoleWidth = theHeight;
        myPoleHeight = thePoleHeight;
        myPoleYTranslate = -((myPoleHeight/2) + (myBaseHeight/2));
        createTower();
    }

    private void createTower() {
        Rectangle base = new Rectangle(myBaseWidth, myBaseHeight);
        base.setFill(Color.BLACK);

        Rectangle pole = new Rectangle(myPoleWidth, myPoleHeight);
        pole.setFill(Color.BLACK);
        pole.setTranslateY(myPoleYTranslate);

        super.getChildren().addAll(base, pole);
    }
}