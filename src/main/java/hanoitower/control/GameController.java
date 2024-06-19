package hanoitower.control;

import hanoitower.model.HanoiTower;
import hanoitower.view.DiskGUI;
import hanoitower.view.TowerGUI;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import static hanoitower.control.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;
import static hanoitower.model.HanoiTower.DEFAULT_LEVEL;
import static hanoitower.model.HanoiTower.MAXIMUM_LEVEL;

public class GameController implements Initializable {
    private final static String MINIMUM_MOVES_LABEL_PREFIX = "Minimum Moves: ";

    private final static String MOVES_LABEL_PREFIX = "Moves: ";

    @FXML
    private HBox myTowersHBox;

    @FXML
    private TowerGUI myLeftTower;

    @FXML
    private TowerGUI myMiddleTower;

    @FXML
    private TowerGUI myRightTower;

    @FXML
    private Button myExitButton;

    @FXML
    private Button myRestartButton;

    @FXML
    private Button myHelpButton;

    @FXML
    private Label myMinimumMovesLabel;

    @FXML
    private Label myMovesLabel;

    @FXML
    private ProgressBar myProgressBar;

    @FXML
    private Spinner<Integer> myLevelSpinner;

    @FXML
    private Label myTimerLabel;

//    @FXML
//    private final Scene myGameScene = myTowersHBox.getScene();

    @FXML
    private final DoubleProperty myProgressProperty = new SimpleDoubleProperty();

    @FXML
    private final SpinnerValueFactory<Integer> myLevelFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(DEFAULT_LEVEL, MAXIMUM_LEVEL);

    @FXML
    private void attachEvents() {
//        myGameScene.setOnKeyPressed(this::handleKeyPress);

        myExitButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myRestartButton.setOnMouseClicked(theMouseEvent -> {
            try {
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });
    }

    private void handleKeyPress(final KeyEvent theKeyEvent) {
        switch (theKeyEvent.getCode()) {
            case LEFT, RIGHT -> {

            }
            case ESCAPE, R, H -> {
                try {
                    ViewManager.setView(theKeyEvent);
                } catch (IOException e) {
                    System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
                }
            }
            case PAUSE -> {
                HanoiTower.getInstance().pauseGame();
            }
            case PLAY -> {
                HanoiTower.getInstance().resumeGame();
            }
        }
    }

    @FXML
    private void showProgress() {
        double progress = HanoiTower.getInstance().getProgress();
        myProgressProperty.set(progress);
        myProgressBar.progressProperty().bind(myProgressProperty);
    }

    @FXML
    private void showTowers() {
        myLeftTower = new TowerGUI();
        myMiddleTower = new TowerGUI();
        myRightTower = new TowerGUI();
        myTowersHBox.getChildren().addAll(myLeftTower, myMiddleTower, myRightTower);
    }

    @FXML
    private void showDisks() {
        int level = HanoiTower.getInstance().getLevel();
        DiskGUI[] disks = IntStream.iterate(level - 1, i -> i >= 0, i -> i - 1).mapToObj(this::createDisk).toArray(DiskGUI[]::new);
        myLeftTower.addAllDisks(disks);
    }

    @FXML
    private DiskGUI createDisk(final int theLevel) {
        int randomR = new Random().nextInt(255);
        int randomG = new Random().nextInt(255);
        int randomB = new Random().nextInt(255);
        Color randomColor = Color.rgb(randomR, randomG, randomB);

        return new DiskGUI(theLevel, randomColor);
    }

    @FXML
    private void showMinimumMoves() {
        int level = HanoiTower.getInstance().getLevel();
        int minimumMoves = HanoiTower.MINIMUM_MOVES[level-DEFAULT_LEVEL];
        myMinimumMovesLabel.setText(MINIMUM_MOVES_LABEL_PREFIX + minimumMoves);
    }

    @FXML
    private void showMoves() {
        int moves = HanoiTower.getInstance().getMoves();
        myMovesLabel.setText(MOVES_LABEL_PREFIX + moves);
    }

    @FXML
    private void showLevel() {
        int level = HanoiTower.getInstance().getLevel();
        myLevelFactory.setValue(level);
        myLevelSpinner.setValueFactory(myLevelFactory);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTowers();
        showDisks();
        showLevel();
        showProgress();
        showMoves();
        showMinimumMoves();
    }
}
