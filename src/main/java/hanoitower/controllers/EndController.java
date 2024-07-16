package hanoitower.controllers;

import hanoitower.model.HanoiTower;
import hanoitower.utilties.SoundManager;
import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static hanoitower.utilties.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;

public class EndController implements Initializable {
    private static final String WIN_STATEMENT = "You won!";

    private static final String LOSS_STATEMENT = "Game Over.";

    @FXML
    private Button myYesButton;

    @FXML
    private Button myNoButton;

    @FXML
    private Label myEndGameLabel;

    @FXML
    private void attachEvents() {
        myYesButton.setOnMouseClicked(theMouseEvent -> {
            try {
                SoundManager.playSoundEffect(myYesButton);
                SoundManager.stopSoundEffect();
                ViewManager.setView(theMouseEvent);

                HanoiTower.getInstance().restartGame(HanoiTower.getInstance().getLevel());
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myNoButton.setOnMouseClicked(theMouseEvent -> {
            try {
                SoundManager.playSoundEffect(myNoButton);
                SoundManager.stopSoundEffect();
                ViewManager.setView(theMouseEvent);

                HanoiTower.getInstance().setPlayed(false);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myEndGameLabel.setText(HanoiTower.getInstance().hasWon() ? WIN_STATEMENT : LOSS_STATEMENT);
        SoundManager.playSoundEffect(myYesButton.getParent());
    }
}

