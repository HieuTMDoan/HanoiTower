package hanoitower.control;

import hanoitower.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ViewManager {
    private static final String FXML_DIRECTORY_PATH = "src/main/resources/hanoitower/fxml/";

    public static final String HOME_VIEW_PATH = "/hanoitower/fxml/home-view.fxml";

    private static final String SAVE_VIEW_PATH = "/hanoitower/fxml/save-view.fxml";

    private static final String TITLE = "HANOI TOWER";

    @FXML
    private static final Rectangle2D VISIBLE_WINDOW_DIMENSION = Screen.getPrimary().getVisualBounds();

    @FXML
    private static final Image ICON_IMAGE = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/hanoitower/images/game-logo.png")));

    public static final String VIEW_SWITCH_ERROR_MESSAGE = "Unable to switch view!";

    @FXML
    private static FXMLLoader myFXMLLoader;

    @FXML
    private static Scene myCurrentView;

    @FXML
    private static Stage myMainStage;

    @FXML
    private static Scene myPreviousView;

    @FXML
    private static Scene myPopUpView;

    @FXML
    private static Stage myPopUpStage;

    private ViewManager() {

    }

    @FXML
    public static void setView(final InputEvent theInputEvent) throws IOException {
        if (theInputEvent instanceof MouseEvent) {
            String viewKeyWord = ((Button) theInputEvent.getSource()).getText();

            switch (viewKeyWord) {
                case "Back" -> {
                    myCurrentView = myPreviousView;
                    loadMainStage(myMainStage, myCurrentView);
                }
                case "Exit" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile(viewKeyWord)));
                    initializePopUpViewAndStage();
                    loadPopUpStage();
                }
                case "Save" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(SAVE_VIEW_PATH));
                    myPopUpStage.close();
                    initializePopUpViewAndStage();
                    loadPopUpStage();
                }
                case "Don't Save" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(HOME_VIEW_PATH));
                    myPopUpStage.close();
                    myMainStage.close();
                    initializeViewAndStage(theInputEvent);
                    loadMainStage(myMainStage, myCurrentView);
                }
                default -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile(viewKeyWord)));
                    myPreviousView = myCurrentView;
                    initializeViewAndStage(theInputEvent);
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
                    initializeViewAndStage(theInputEvent);
                    loadMainStage(myMainStage, myCurrentView);
                }
                case ESCAPE -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile("Exit")));
                    initializePopUpViewAndStage();
                    loadPopUpStage();
                }
                case H -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile("Help")));
                    myPreviousView = myCurrentView;
                    initializeViewAndStage(theInputEvent);
                    loadMainStage(myMainStage, myCurrentView);
                }
                case BACK_SPACE -> {
                    myCurrentView = myPreviousView;
                    loadMainStage(myMainStage, myCurrentView);
                }
            }
        }
    }

    @FXML
    private static void initializeViewAndStage(final InputEvent theInputEvent) throws IOException {
        Parent load = myFXMLLoader.load();
        myCurrentView = new Scene(load);
    }

    @FXML
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

    @FXML
    private static void initializePopUpViewAndStage() throws IOException {
        Parent load = myFXMLLoader.load();
        myPopUpView = new Scene(load);
        myPopUpStage = new Stage();
    }

    @FXML
    private static void loadPopUpStage() {
        myPopUpStage.getIcons().add(ICON_IMAGE);
        myPopUpStage.setTitle(TITLE);
        myPopUpStage.setResizable(false);
        myPopUpStage.initModality(Modality.WINDOW_MODAL);
        myPopUpStage.initOwner(myMainStage);
        myPopUpStage.setScene(myPopUpView);

        myPopUpStage.show();
    }

    private static String getViewFile(final String theViewKeyword) {
        String fxmlPath = "";
        File directory = new File(FXML_DIRECTORY_PATH);

        if (directory.isDirectory()) {
            File[] listOfFiles = directory.listFiles();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.getName().contains(theViewKeyword.toLowerCase())) {
                        fxmlPath = "/hanoitower/fxml/" + file.getName();
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
