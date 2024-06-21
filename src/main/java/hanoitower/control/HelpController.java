package hanoitower.control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static hanoitower.control.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;
import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCode.BACK_SPACE;

public class HelpController {
    @FXML
    private AnchorPane myHelpAnchorPane;

    @FXML
    private Button myBackButton;

    @FXML
    private void attachEvents() {
        myHelpAnchorPane.setOnKeyPressed(theKeyEvent -> {
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
