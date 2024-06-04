package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static view.Controller.*;

public class Main extends Application {
    @Override
    public void start(Stage theStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(HOME_VIEW_PATH));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        Controller.loadWindow(theStage, scene);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}