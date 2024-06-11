package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static view.ViewManager.*;

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
                setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        myLoadButton.setOnMouseClicked(theMouseEvent -> {
            try {
                setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}