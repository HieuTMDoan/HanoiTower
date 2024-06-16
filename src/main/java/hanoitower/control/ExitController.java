package hanoitower.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExitController {
    @FXML
    private Button mySaveButton;

    @FXML
    private Button myDoNotSaveButton;

    @FXML
    private void attachEvents() {
        mySaveButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        myDoNotSaveButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
