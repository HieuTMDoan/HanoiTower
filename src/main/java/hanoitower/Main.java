package hanoitower;

import hanoitower.utilties.SoundManager;
import hanoitower.utilties.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static hanoitower.utilties.ViewManager.BACKGROUND;
import static hanoitower.utilties.ViewManager.HOME_VIEW_PATH;

public class Main extends Application {
    @Override
    public void start(Stage theStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(HOME_VIEW_PATH));
        Pane load = fxmlLoader.load();
        load.setBackground(BACKGROUND);
        Scene scene = new Scene(load);

        ViewManager.loadMainStage(theStage, scene);
        SoundManager.playIntro();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}