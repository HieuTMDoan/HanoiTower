package hanoitower.utilties;

import hanoitower.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ViewManager {
    private static final String FXML_DIRECTORY_PATH_FROM_CONTENT_ROOT = "src/main/resources/hanoitower/fxml/";

    public static final String FXML_DIRECTORY_PATH_FROM_SOURCE_ROOT = "/hanoitower/fxml/";

    public static final String HOME_VIEW_PATH = "/hanoitower/fxml/home-view.fxml";

    private static final String SAVE_VIEW_PATH = "/hanoitower/fxml/save-view.fxml";

    private static final String GAME_VIEW_PATH = "/hanoitower/fxml/new-view.fxml";

    private static final String END_VIEW_PATH = "/hanoitower/fxml/end-view.fxml";

    private static final String TITLE = "HANOI TOWER";

    public static final String VIEW_SWITCH_ERROR_MESSAGE = "Unable to switch view!";

    public static final String KEY_EVENT_ERROR_MESSAGE = "Unable to handle key event!";

    private static final Rectangle2D VISIBLE_WINDOW_DIMENSION = Screen.getPrimary().getVisualBounds();

    private static final Image ICON_IMAGE = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/hanoitower/image/game-logo.png")));

    private static final Image BACKGROUND_IMAGE_SOURCE = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/hanoitower/image/background-image.jpeg")));

    private static final BackgroundImage BACKGROUND_IMAGE = new BackgroundImage(
            BACKGROUND_IMAGE_SOURCE,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(100, 100, true, true, false, true));

    public static final Background BACKGROUND = new Background(BACKGROUND_IMAGE);

    private static FXMLLoader myFXMLLoader;

    private static Scene myCurrentView;

    private static Stage myMainStage;

    private static Scene myPreviousView;

    private static Scene myPopUpView;

    private static Stage myPopUpStage;

    private ViewManager() {

    }

    public static void setView(final InputEvent theInputEvent) throws IOException {
        if (theInputEvent instanceof MouseEvent) {
            String viewKeyWord = ((Button) theInputEvent.getSource()).getText();

            switch (viewKeyWord) {
                case "Back" -> {
                    myCurrentView = myPreviousView;
                    loadMainStage(myMainStage, myCurrentView);
                }
                case "Exit" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFilePath(viewKeyWord)));
                    initializePopUpView();
                    loadPopUpStage();
                }
                case "Save" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(SAVE_VIEW_PATH));
                    myPopUpStage.close();
                    initializePopUpView();
                    loadPopUpStage();
                }
                case "Don't Save", "No" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(HOME_VIEW_PATH));
                    myPopUpStage.close();
                    myMainStage.close();
                    initializeView();
                    loadMainStage(myMainStage, myCurrentView);
                }
                case "Yes" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(GAME_VIEW_PATH));
                    myPopUpStage.close();
                    myMainStage.close();
                    initializeView();
                    loadMainStage(myMainStage, myCurrentView);
                }
                default -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFilePath(viewKeyWord)));
                    myPreviousView = myCurrentView;
                    initializeView();
                    loadMainStage(myMainStage, myCurrentView);
                }
            }
        }
        else if (theInputEvent instanceof KeyEvent) {
            KeyCode viewKeyCode = ((KeyEvent) theInputEvent).getCode();

            switch (viewKeyCode) {
                case ENTER -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(HOME_VIEW_PATH));
                    myPopUpStage.close();
                    myMainStage.close();
                    initializeView();
                    loadMainStage(myMainStage, myCurrentView);
                }
                case ESCAPE -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFilePath("Exit")));
                    initializePopUpView();
                    loadPopUpStage();
                }
                case H -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFilePath("Help")));
                    myPreviousView = myCurrentView;
                    initializeView();
                    loadMainStage(myMainStage, myCurrentView);
                }
                case BACK_SPACE -> {
                    myCurrentView = myPreviousView;
                    loadMainStage(myMainStage, myCurrentView);
                }
            }
        }
    }

    public static void setEndView() {
        try {
            myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(END_VIEW_PATH));
            initializePopUpView();
            loadPopUpStage();
        } catch (IOException e) {
            throw new RuntimeException(VIEW_SWITCH_ERROR_MESSAGE);
        }
    }

    public static void initializeView() throws IOException {
        Pane load = myFXMLLoader.load();
        load.setBackground(BACKGROUND);
        myCurrentView = new Scene(load);
        load.requestFocus();
    }

    public static void loadMainStage(final Stage theStage, final Scene theScene) {
        if (myMainStage == null || myCurrentView == null) {
            myMainStage = theStage;
            myCurrentView = theScene;
            myPreviousView = myCurrentView;
        }

        myMainStage.getIcons().add(ICON_IMAGE);
        myMainStage.setTitle(TITLE);
        myMainStage.setResizable(false);
        myMainStage.setOnCloseRequest((final WindowEvent theWindowEvent) -> System.exit(0));
        myMainStage.setScene(myCurrentView);

        myMainStage.show();
    }

    private static void initializePopUpView() throws IOException {
        Pane load = myFXMLLoader.load();
        myPopUpView = new Scene(load);
        load.requestFocus();
    }

    private static void loadPopUpStage() {
        myPopUpStage = new Stage();
        myPopUpStage.getIcons().add(ICON_IMAGE);
        myPopUpStage.setTitle(TITLE);
        myPopUpStage.setOnCloseRequest(Event::consume);
        myPopUpStage.setResizable(false);
        myPopUpStage.initModality(Modality.WINDOW_MODAL);
        myPopUpStage.initOwner(myMainStage);
        myPopUpStage.setScene(myPopUpView);

        myPopUpStage.show();
    }

    private static String getViewFilePath(final String theKeyword) {
        String fxmlPath = "";
        File directory = new File(FXML_DIRECTORY_PATH_FROM_CONTENT_ROOT);

        if (directory.isDirectory()) {
            File[] listOfFiles = directory.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.getName().contains(theKeyword.toLowerCase())) {
                        fxmlPath = FXML_DIRECTORY_PATH_FROM_SOURCE_ROOT + file.getName();
                        break;
                    }
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }

        return fxmlPath;
    }
}
