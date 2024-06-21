package hanoitower.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import hanoitower.model.HanoiTower;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static hanoitower.control.ViewManager.*;
import static hanoitower.model.HanoiTower.Mode;
import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCode.BACK_SPACE;

public class LoadController implements Initializable {
    @FXML
    public AnchorPane myLoadAnchorPane;

    @FXML
    private TableColumn<HanoiTower, String> myNameColumn;

    @FXML
    private TableColumn<HanoiTower, Integer> myLevelColumn;

    @FXML
    private TableColumn<HanoiTower, Double> myProgressColumn;

    @FXML
    private TableColumn<HanoiTower, Mode> myModeColumn;

    @FXML
    private TableView<HanoiTower> mySavedGamesTable;

    private final ObservableList<HanoiTower> mySavedGamesList = FXCollections.observableArrayList(HanoiTower.getSavedGames());

    @FXML
    private Button myBackButton;

    @FXML
    private void attachEvents() {
         myLoadAnchorPane.setOnKeyPressed(theKeyEvent -> {
            try {
                if (theKeyEvent.getCode() == BACK_SPACE) {
                    ViewManager.setView(theKeyEvent);
                }
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }

    @FXML
    private void showSavedGames() {
        myNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        myLevelColumn.setCellValueFactory(new PropertyValueFactory<>("Level"));
        myProgressColumn.setCellValueFactory(new PropertyValueFactory<>("Progress"));
        myModeColumn.setCellValueFactory(new PropertyValueFactory<>("Mode"));
        mySavedGamesTable.setItems(mySavedGamesList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSavedGames();
    }
}
