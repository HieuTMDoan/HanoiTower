package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoadController {
    @FXML
    private Button myBackButton;

    @FXML
    private void attachEvent() {
        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
