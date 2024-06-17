package hanoitower.control;

import hanoitower.view.DiskGUI;
import hanoitower.view.TowerGUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private HBox myTowersHBox;

    @FXML
    private TowerGUI myLeftTower;

    @FXML
    private TowerGUI myMiddleTower;

    @FXML
    private TowerGUI myRightTower;

    @FXML
    private Button myExitButton;

    @FXML
    private Button myRestartButton;

    @FXML
    private Button myHelpButton;

    @FXML
    private Label myMinimumMovesLabel;

    @FXML
    private Label myMovesLabel;

    @FXML
    private ProgressBar myProgressBar;

    @FXML
    private Spinner<Integer> myLevelSpinner;

    @FXML
    private Label myTimerLabel;

    @FXML
    private void attachEvents() {
        myExitButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println("Unable to switch view!");
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println("Unable to switch view!");
            }
        });

        myRestartButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println("Unable to switch view!");
            }
        });
    }

    @FXML
    private void showTowers() {
        myLeftTower = new TowerGUI();
        myMiddleTower = new TowerGUI();
        myRightTower = new TowerGUI();

        DiskGUI disk = new DiskGUI(3, Color.rgb(255, 99, 71));
        DiskGUI disk1 = new DiskGUI(4, Color.rgb(99, 99, 71));
        DiskGUI disk2 = new DiskGUI(5, Color.rgb(99, 255, 71));
        DiskGUI disk3 = new DiskGUI(6, Color.rgb(99, 99, 255));
        DiskGUI disk4 = new DiskGUI(8, Color.rgb(0, 0, 255));

//        myLeftTower.addDisk(disk);
//        myLeftTower.addDisk(disk1);
//        myLeftTower.addDisk(disk2);
//        myLeftTower.addDisk(disk3);
//        myLeftTower.addDisk(disk4);

        myMiddleTower.addAllDisks(disk4, disk3, disk2, disk1);

        myTowersHBox.getChildren().addAll(myLeftTower, myMiddleTower, myRightTower);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTowers();
    }
}
