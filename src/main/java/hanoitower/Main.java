package hanoitower;

import hanoitower.utilties.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static hanoitower.utilties.ViewManager.*;

public class Main extends Application {
    @Override
    public void start(Stage theStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(HOME_VIEW_PATH));
        Parent load = fxmlLoader.load();
        Scene scene = new Scene(load);
        ViewManager.loadMainStage(theStage, scene);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}