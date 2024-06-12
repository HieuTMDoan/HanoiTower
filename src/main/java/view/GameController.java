package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;

import java.io.IOException;

public class GameController {
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
}
