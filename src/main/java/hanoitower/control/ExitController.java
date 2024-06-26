package hanoitower.control;

import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static hanoitower.utilties.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;

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
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myDoNotSaveButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }
}
