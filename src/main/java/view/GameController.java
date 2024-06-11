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
                throw new RuntimeException(e);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        myRestartButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
