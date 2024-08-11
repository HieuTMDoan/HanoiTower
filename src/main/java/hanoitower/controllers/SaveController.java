package hanoitower.controllers;

import hanoitower.model.HanoiTower;
import hanoitower.utilties.SoundManager;
import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;

import static hanoitower.utilties.ViewManager.*;

public class SaveController {
    @FXML
    private TextField myNameTextField;

    @FXML
    private void attachEvents() {
        myNameTextField.setOnKeyPressed(theKeyEvent -> {
            if (theKeyEvent.getCode() == KeyCode.ENTER) {
                try {
                    String typedName = myNameTextField.getText();
                    HanoiTower.getInstance().setName(typedName);
                    HanoiTower.getInstance().saveGame();

                    SoundManager.stopSoundtrack();
                    ViewManager.setView(theKeyEvent);
                } catch (IOException e) {
                    System.out.println(KEY_EVENT_ERROR_MESSAGE);
                }
            }
        });
    }
}
