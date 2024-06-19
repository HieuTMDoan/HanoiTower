package hanoitower.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static hanoitower.control.ViewManager.*;

public class HomeController {
    @FXML
    public Button myNewButton;

    @FXML
    private Button myLoadButton;

    @FXML
    private Button myHelpButton;

    @FXML
    private void attachEvents() {
        myNewButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myLoadButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }
}