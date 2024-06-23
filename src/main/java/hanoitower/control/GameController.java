package hanoitower.control;

import hanoitower.model.HanoiTower;
import hanoitower.view.DiskGUI;
import hanoitower.view.TowerGUI;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
import static javafx.scene.input.KeyCode.*;

public class GameController implements Initializable {
    private final static String MINIMUM_MOVES_LABEL_PREFIX = "Minimum Moves: ";

    private final static String MOVES_LABEL_PREFIX = "Moves: ";

    @FXML
    private BorderPane myGameBorderPane;

    @FXML
    private HBox myTowersHBox;

    @FXML
    private TowerGUI myLeftTower;

    @FXML
    private TowerGUI myMiddleTower;

    @FXML
    private TowerGUI myRightTower;

    @FXML
    private DiskGUI myPoppedDisk;

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

    @FXML
    private final DoubleProperty myProgressProperty = new SimpleDoubleProperty();

    @FXML
    private final SpinnerValueFactory<Integer> myLevelFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(DEFAULT_LEVEL, MAXIMUM_LEVEL);

    @FXML
    private void attachEvents() {
        myGameBorderPane.getScene().setOnKeyPressed(theKeyEvent -> {
            try {
                if (theKeyEvent.getCode() == ESCAPE || theKeyEvent.getCode() == H) {
                    ViewManager.setView(theKeyEvent);
                }
                else if (theKeyEvent.getCode() == R) {
                    HanoiTower.getInstance().restartGame(HanoiTower.getInstance().getLevel());
                    this.restartGame();
                }
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

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
            HanoiTower.getInstance().restartGame(HanoiTower.getInstance().getLevel());
            this.restartGame();
        });
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
        myLeftTower.setOnMouseClicked(theMouseEvent -> {
            myLeftTower.requestFocus();
            DiskGUI poppedDisk = (DiskGUI) myLeftTower.getChildren().get(myLeftTower.getChildren().size()-1);
            if (!poppedDisk.isPopped()) {
                myPoppedDisk = myLeftTower.popDisk();
            } else {
                myLeftTower.pushDisk();
            }
        });
        myLeftTower.setOnKeyPressed(theKeyEvent -> {
            if (theKeyEvent.getCode() == RIGHT && myPoppedDisk != null) {
                myLeftTower.removeDisk();
                myPoppedDisk.setPopped(false);
                myMiddleTower.addDisk(myPoppedDisk);
                myMiddleTower.popDisk();
            }
        });

        myMiddleTower = new TowerGUI();
        myMiddleTower.setOnMouseClicked(theMouseEvent -> {
            myMiddleTower.requestFocus();
            DiskGUI poppedDisk = (DiskGUI) myMiddleTower.getChildren().get(myMiddleTower.getChildren().size()-1);
            if (!poppedDisk.isPopped()) {
                myPoppedDisk = myMiddleTower.popDisk();
            } else {
                myMiddleTower.pushDisk();
            }
        });
        myMiddleTower.setOnKeyPressed(theKeyEvent -> {
            if (theKeyEvent.getCode() == RIGHT && myPoppedDisk != null) {
                myMiddleTower.removeDisk();
                myPoppedDisk.setPopped(false);
                myRightTower.addDisk(myPoppedDisk);
                myRightTower.popDisk();
            } else if (theKeyEvent.getCode() == LEFT && myPoppedDisk != null) {
                myMiddleTower.removeDisk();
                myPoppedDisk.setPopped(false);
                myLeftTower.addDisk(myPoppedDisk);
                myLeftTower.popDisk();
            }
        });

        myRightTower = new TowerGUI();
        myRightTower.setOnMouseClicked(theMouseEvent -> {
            myRightTower.requestFocus();
            DiskGUI poppedDisk = (DiskGUI) myRightTower.getChildren().get(myRightTower.getChildren().size()-1);
            if (!poppedDisk.isPopped()) {
                myPoppedDisk = myRightTower.popDisk();
            } else {
                myRightTower.pushDisk();
            }
        });
        myRightTower.setOnKeyPressed(theKeyEvent -> {
            if (theKeyEvent.getCode() == LEFT && myPoppedDisk.isPopped()) {
                myRightTower.removeDisk();
                myPoppedDisk.setPopped(false);
                myMiddleTower.addDisk(myPoppedDisk);
                myMiddleTower.popDisk();
            }
        });

        myTowersHBox.getChildren().addAll(myLeftTower, myMiddleTower, myRightTower);
    }

    @FXML
    private void showDisks() {
        int level = HanoiTower.getInstance().getLevel();
        DiskGUI[] disks = IntStream.iterate(level - 1, i -> i >= 0, i -> i - 1).mapToObj(this::createDisk).toArray(DiskGUI[]::new);
        myLeftTower.addAllDisks(disks);
    }

    @FXML
    private void removeAllDisks() {
        myLeftTower.removeAllDisks();
        myMiddleTower.removeAllDisks();
        myRightTower.removeAllDisks();
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
        myLevelSpinner.valueProperty().addListener((theObservableValue, theOldValue, theNewValue) -> {
            HanoiTower.getInstance().restartGame(theNewValue);
            this.restartGame();
        });
    }

    @FXML
    private void restartGame() {
        removeAllDisks();
        showDisks();
        showProgress();
        showMoves();
        showMinimumMoves();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HanoiTower.getInstance().startGame();
        showTowers();
        showDisks();
        showLevel();
        showProgress();
        showMoves();
        showMinimumMoves();
    }
}
