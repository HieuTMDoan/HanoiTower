package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ViewManager {
    public static final String FXML_DIRECTORY_PATH = "src/main/resources/view/fxml/";

    public static final String HOME_VIEW_PATH = "fxml/home-view.fxml";

    public static final String GAME_VIEW_PATH = "fxml/new-view.fxml";

    public static final String TITLE = "HANOI TOWER";

    @FXML
    public static final Image ICON = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/app-icon.png")));

    @FXML
    private static FXMLLoader myFXMLLoader;

    @FXML
    private static Scene myCurrentView;

    @FXML
    private static Stage myStage;

    @FXML
    private static Scene myPreviousView;

    @FXML
    public static void setView(MouseEvent theMouseEvent) throws IOException {
        String viewKeyWord = ((Button) theMouseEvent.getSource()).getText();

        switch (viewKeyWord) {
            case "Back" -> {
                myCurrentView = myPreviousView;
                myStage = (Stage) ((Node) theMouseEvent.getSource()).getScene().getWindow();
                loadStage(myStage, myCurrentView);
            }

            case "Restart" -> {
                myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(GAME_VIEW_PATH));
                myPreviousView = myCurrentView;
                Parent load = myFXMLLoader.load();
                myCurrentView = new Scene(load);
                myStage = (Stage) ((Node) theMouseEvent.getSource()).getScene().getWindow();
                loadStage(myStage, myCurrentView);
            }

            case "Exit" -> {
                myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile(viewKeyWord)));
                Parent load = myFXMLLoader.load();
                Scene popUpScene = new Scene(load);
                Stage popUpWindow = new Stage();
                popUpWindow.getIcons().add(ICON);
                popUpWindow.setTitle(TITLE);
                popUpWindow.setResizable(false);
                popUpWindow.setScene(popUpScene);
                popUpWindow.show();
            }

            default -> {
                myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(getViewFile(viewKeyWord)));
                myPreviousView = myCurrentView;
                Parent load = myFXMLLoader.load();
                myCurrentView = new Scene(load);
                myStage = (Stage) ((Node) theMouseEvent.getSource()).getScene().getWindow();
                loadStage(myStage, myCurrentView);
            }
        }
    }

    @FXML
    public static void loadStage(Stage theStage, Scene theScene) {
        if (myStage == null || myCurrentView == null) {
            myStage = theStage;
            myCurrentView = theScene;
            myPreviousView = myCurrentView;
        }

        myStage.getIcons().add(ICON);
        myStage.setTitle(TITLE);
        myStage.setResizable(false);
        myStage.setScene(myCurrentView);
        myStage.show();
    }

//    @FXML
//    private static void handleExit(WindowEvent event) {
//        // Show a confirmation dialog
//        Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setTitle("Exit Confirmation");
//        alert.setHeaderText(null);
//        alert.setContentText("Are you sure you want to exit?");
//
//        // Wait for the user's response
//        alert.showAndWait().ifPresent(response -> {
//            if (response != ButtonType.OK) {
//                // If the user chooses not to exit, consume the event
//                event.consume();
//            }
//        });
//    }

    private static String getViewFile(String theViewKeyword) {
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
