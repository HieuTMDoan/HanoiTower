package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import model.HanoiTower;

import java.io.IOException;

public class SaveController {
    @FXML
    private TextField myNameTextField;

    @FXML
    private void attachEvents() {
        myNameTextField.setOnKeyPressed(theKeyEvent -> {
            if (theKeyEvent.getCode() == KeyCode.ENTER) {
                String typedName = myNameTextField.getText();
                HanoiTower.getInstance().setName(typedName);
                HanoiTower.saveGame(HanoiTower.getInstance());
                System.out.println(HanoiTower.getSavedGames());
                try {
                    ViewManager.setView(theKeyEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
