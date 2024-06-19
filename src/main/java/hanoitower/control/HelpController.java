package hanoitower.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static hanoitower.control.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;

public class HelpController {
    @FXML
    private Button myBackButton;

    @FXML
    private void attachEvents() {
        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }
}
