package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController {
    @FXML
    private Label myGameTitle;

    @FXML
    private Button myLoadButton;

    @FXML
    private Button myHelpButton;

    @FXML
    private void attachEvent() {
        myLoadButton.setOnMouseClicked(theMouseEvent -> {
            try {
                Controller.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                Controller.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}