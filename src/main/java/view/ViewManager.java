package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ViewManager {
    public static final String FXML_DIRECTORY_PATH = "src/main/resources/view/fxml/";

    public static final String HOME_VIEW_PATH = "fxml/home-view.fxml";

    public static final String TITLE = "HANOI TOWER";

    @FXML
    public static final Image ICON = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/app-icon.png")));

    private static final Rectangle2D SCREEN_SIZE = Screen.getPrimary().getVisualBounds();

    private static final double TWO_FIFTHS = (double) 2/5;

    private static final double ONE_TENTHS = (double) 1/10;

    public static final double WINDOW_WIDTH = SCREEN_SIZE.getWidth() * TWO_FIFTHS;

    public static final double WINDOW_HEIGHT = SCREEN_SIZE.getHeight() * TWO_FIFTHS;

    public static final double BUTTON_WIDTH = WINDOW_WIDTH * ONE_TENTHS;

    public static final double BUTTON_HEIGHT = WINDOW_HEIGHT * ONE_TENTHS;

    @FXML
    private static FXMLLoader myFXMLLoader;

    @FXML
    private static Scene myCurrentView;

    @FXML
    private static Stage myWindow;

    @FXML
    private static Scene myPreviousView;

    @FXML
    public static void setView(MouseEvent theMouseEvent) throws IOException {
        String viewKeyWord = ((Button) theMouseEvent.getSource()).getText();

        if (viewKeyWord.equals("BACK")) {
            myCurrentView = myPreviousView;
        } else {
            String viewFile = getViewFile(viewKeyWord);
            myFXMLLoader = new FXMLLoader(ViewManager.class.getResource(viewFile));
            Parent load = myFXMLLoader.load();
            myCurrentView = new Scene(load, WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        myWindow = (Stage) ((Node) theMouseEvent.getSource()).getScene().getWindow();
        loadWindow(myWindow, myCurrentView);
    }

    @FXML
    public static void loadWindow(Stage theStage, Scene theScene) {
        if (myWindow == null || myCurrentView == null) {
            myWindow = theStage;
            myCurrentView = theScene;
            myPreviousView = myCurrentView;
        }

        myWindow.getIcons().add(ICON);
        myWindow.setTitle(TITLE);
        myWindow.setResizable(false);
        myWindow.setScene(myCurrentView);
        myWindow.show();
    }

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
