package hanoitower.control;

import hanoitower.model.HanoiTower;
import hanoitower.utilties.SoundManager;
import hanoitower.utilties.ViewManager;
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

import static hanoitower.utilties.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;
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
    private TowerGUI myPreviousClickedTower;

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
            handlePoppedDisk(myLeftTower);
            myLeftTower.requestFocus();
            SoundManager.playSoundEffect();
        });

        myMiddleTower = new TowerGUI();
        myMiddleTower.setOnMouseClicked(theMouseEvent -> {
            handlePoppedDisk(myMiddleTower);
            myMiddleTower.requestFocus();
            SoundManager.playSoundEffect();
        });

        myRightTower = new TowerGUI();
        myRightTower.setOnMouseClicked(theMouseEvent -> {
            handlePoppedDisk(myRightTower);
            myRightTower.requestFocus();
            SoundManager.playSoundEffect();
        });

        myTowersHBox.getChildren().addAll(myLeftTower, myMiddleTower, myRightTower);
    }

    @FXML
    private void handlePoppedDisk(final TowerGUI theCurrentClickedTower) {
        if (myPreviousClickedTower != null) {   //if a tower is already clicked before
            if (myPreviousClickedTower.equals(theCurrentClickedTower)) {    //if clicks the same tower as before
                if (theCurrentClickedTower.getDiskCount() > 0) {   //if the clicked tower is not empty
                    DiskGUI topDisk = theCurrentClickedTower.peekDisk();
                    if (!topDisk.isPopped()) {  //if the top disk of the clicked tower is not popped
                        myPoppedDisk = theCurrentClickedTower.popDisk();
                    } else {    //if the top disk of the clicked tower is not popped
                        theCurrentClickedTower.pushDisk();
                        myPoppedDisk = null;
                    }
                }
            } else {    //else if clicks a different tower than before
                if (theCurrentClickedTower.getDiskCount() > 0) {   //if the clicked tower is not empty
                    if (myPoppedDisk != null && myPoppedDisk.isPopped()) {  //if the popped disk of the clicked tower is popped and not null
                        myPreviousClickedTower.removeDisk();
                        theCurrentClickedTower.addDisk(myPoppedDisk);
                    } else {    //else if the popped disk of the clicked tower is null or not popped
                        myPoppedDisk = theCurrentClickedTower.popDisk();
                    }
                } else {    //else if the clicked tower is empty
                    if (myPoppedDisk != null && myPoppedDisk.isPopped()) {  //if the popped disk of the clicked tower is popped and not null
                        myPreviousClickedTower.removeDisk();
                        theCurrentClickedTower.addDisk(myPoppedDisk);
                    }
                }
            }
        } else {    //if no towers are clicked before
            if (theCurrentClickedTower.getDiskCount() > 0) {   //if the clicked tower is not empty
                DiskGUI topDisk = theCurrentClickedTower.peekDisk();
                if (!topDisk.isPopped()) {  //if the top disk of the clicked tower is not popped
                    myPoppedDisk = theCurrentClickedTower.popDisk();
                } else {    //if the top disk of the clicked tower is already popped
                    theCurrentClickedTower.pushDisk();
                    myPoppedDisk = null;
                }
            }
        }

        myPreviousClickedTower = theCurrentClickedTower;
    }

    @FXML
    private void showDisks() {
        int level = HanoiTower.getInstance().getLevel();
        DiskGUI[] disks = IntStream.iterate(level - 1, i -> i >= 0, i -> i - 1).mapToObj(this::createDisk).toArray(DiskGUI[]::new);
        myLeftTower.addAllDisks(disks);
    }

    @FXML
    private void resetTowers() {
        myLeftTower.removeAllDisks();
        myMiddleTower.removeAllDisks();
        myRightTower.removeAllDisks();
        myPreviousClickedTower = null;
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
        resetTowers();
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
