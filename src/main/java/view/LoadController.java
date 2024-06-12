package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.HanoiTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.HanoiTower.*;
import static model.HanoiTower.Mode.*;

public class LoadController implements Initializable {
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
        myBackButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

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
