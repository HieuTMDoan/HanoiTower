package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static view.ViewManager.*;

public class HomeController {
    @FXML
    private AnchorPane myRoot;

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