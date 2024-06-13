package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ViewManager {
    public static final String FXML_DIRECTORY_PATH = "src/main/resources/view/fxml/";

    public static final String HOME_VIEW_PATH = "fxml/home-view.fxml";

    public static final String GAME_VIEW_PATH = "fxml/new-view.fxml";

    public static final String SAVE_VIEW_PATH = "fxml/save-view.fxml";

    public static final String TITLE = "HANOI TOWER";

    @FXML
    public static final Image ICON = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/app-icon.png")));

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

    @FXML
    public static void setView(final InputEvent theInputEvent) throws IOException {
        if (theInputEvent instanceof MouseEvent) {
            String viewKeyWord = ((Button) theInputEvent.getSource()).getText();

            switch (viewKeyWord) {
                case "Back" -> {
                    myCurrentView = myPreviousView;
                    myMainStage = (Stage) ((Node) theInputEvent.getSource()).getScene().getWindow();
                    loadStage(myMainStage, myCurrentView);
                }
                case "Restart" -> {
                    myPreviousView = myCurrentView;
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(GAME_VIEW_PATH));
                    initializeViewAndStage(theInputEvent);
                    loadStage(myMainStage, myCurrentView);
                }
                case "Exit" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile(viewKeyWord)));
                    Parent load = myFXMLLoader.load();
                    myPopUpView = new Scene(load);
                    loadPopUpStage();
                }
                case "Save" -> {
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(SAVE_VIEW_PATH));
                    Parent load = myFXMLLoader.load();
                    myPopUpView = new Scene(load);
                    loadPopUpStage();
                }
                case "Don't Save" -> {
                    myPopUpStage.close();
                    myMainStage.close();
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(HOME_VIEW_PATH));
                    initializeViewAndStage(theInputEvent);
                    loadStage(myMainStage, myCurrentView);
                }
                default -> {
                    myPreviousView = myCurrentView;
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile(viewKeyWord)));
                    initializeViewAndStage(theInputEvent);
                    loadStage(myMainStage, myCurrentView);
                }
            }
        }
        else if (theInputEvent instanceof KeyEvent) {
            KeyCode viewKeyCode = ((KeyEvent) theInputEvent).getCode();

            switch (viewKeyCode) {
                case ENTER -> {
                    myPopUpStage.close();
                    myMainStage.close();
                    myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(HOME_VIEW_PATH));
                    initializeViewAndStage(theInputEvent);
                    loadStage(myMainStage, myCurrentView);
                }
            }
        }
    }

    @FXML
    public static void loadStage(final Stage theStage, final Scene theScene) {
        if (myMainStage == null || myCurrentView == null) {
            myMainStage = theStage;
            myCurrentView = theScene;
            myPreviousView = myCurrentView;
        }

        myMainStage.getIcons().add(ICON);
        myMainStage.setTitle(TITLE);
        myMainStage.setResizable(false);
        myMainStage.setScene(myCurrentView);
        myMainStage.show();
    }

    @FXML
    private static void initializeViewAndStage(final InputEvent theInputEvent) throws IOException {
        Parent load = myFXMLLoader.load();
        myCurrentView = new Scene(load);
        myMainStage = (Stage) ((Node) theInputEvent.getSource()).getScene().getWindow();
    }

    @FXML
    private static void loadPopUpStage() {
        myPopUpStage = new Stage();
        myPopUpStage.getIcons().add(ICON);
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
                        fxmlPath = "fxml/" + file.getName();
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
