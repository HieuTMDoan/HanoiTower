package hanoitower.control;

import hanoitower.model.HanoiTower;
import hanoitower.utilties.SoundManager;
import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static hanoitower.utilties.ViewManager.KEY_EVENT_ERROR_MESSAGE;
import static hanoitower.utilties.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;
import static javafx.scene.input.KeyCode.BACK_SPACE;
import static javafx.scene.input.KeyCode.S;

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
                    HanoiTower.getInstance().resumeGame();
                    ViewManager.setView(theKeyEvent);
                }
            } catch (IOException e) {
                System.out.println(KEY_EVENT_ERROR_MESSAGE);
            }
        });

        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                SoundManager.playClick();
                ViewManager.setView(theMouseEvent);
                HanoiTower.getInstance().resumeGame();
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }
}
