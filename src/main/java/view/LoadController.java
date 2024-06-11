package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class LoadController {
    @FXML
    private Button myBackButton;

    @FXML
    private void attachEvents() {
        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
