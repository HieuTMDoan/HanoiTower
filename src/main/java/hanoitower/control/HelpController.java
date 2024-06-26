package hanoitower.control;

import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static hanoitower.utilties.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;
import static javafx.scene.input.KeyCode.BACK_SPACE;

public class HelpController {
    @FXML
    private AnchorPane myHelpAnchorPane;

    @FXML
    private Button myBackButton;

    @FXML
    private void attachEvents() {
        myHelpAnchorPane.getScene().setOnKeyPressed(theKeyEvent -> {
            try {
                if (theKeyEvent.getCode() == BACK_SPACE) {
                    ViewManager.setView(theKeyEvent);
                }
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }
}
