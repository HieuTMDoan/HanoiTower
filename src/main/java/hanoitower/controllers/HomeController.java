package hanoitower.controllers;

import hanoitower.utilties.SoundManager;
import hanoitower.utilties.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static hanoitower.utilties.ViewManager.*;

public class HomeController implements Initializable {
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
                SoundManager.playSoundEffect(myNewButton);
                SoundManager.stopSoundtrack();
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myLoadButton.setOnMouseClicked(theMouseEvent -> {
            try {
                SoundManager.playSoundEffect(myLoadButton);
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                SoundManager.playSoundEffect(myHelpButton);
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void initialize(final URL theUrl, final ResourceBundle theResourceBundle) {
        SoundManager.playSoundtrack(myNewButton.getScene());
    }
}