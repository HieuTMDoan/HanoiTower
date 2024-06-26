package hanoitower.control;

import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import hanoitower.model.HanoiTower;

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
                    HanoiTower.saveGame(HanoiTower.getInstance());
                    System.out.println(HanoiTower.getSavedGames());

                    ViewManager.setView(theKeyEvent);
                } catch (IOException e) {
                    System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
                }
            }
        });
    }
}
