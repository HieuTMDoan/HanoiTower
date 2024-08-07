package hanoitower.controllers;

import hanoitower.model.HanoiTower;
import hanoitower.utilties.SoundManager;
import hanoitower.utilties.TimerManager;
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

import static hanoitower.model.HanoiTower.*;
import static hanoitower.model.HanoiTower.Mode.DEFAULT_MODE;
import static hanoitower.model.HanoiTower.Mode.TIMED_MODE;
import static hanoitower.utilties.ViewManager.VIEW_SWITCH_ERROR_MESSAGE;

public class GameController implements Initializable {
    private final static String MINIMUM_MOVES_LABEL_PREFIX = "Minimum Moves: ";

    private final static String MOVES_LABEL_PREFIX = "Moves: ";

    @FXML
    private BorderPane myGameBorderPane;

    @FXML
    private HBox myTowersHBox;

    private TowerGUI myLeftTower;

    private TowerGUI myMiddleTower;

    private TowerGUI myRightTower;

    private TowerGUI myPreviousClickedTower;

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

    private final DoubleProperty myProgressProperty = new SimpleDoubleProperty();

    private final SpinnerValueFactory<Integer> myLevelFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(DEFAULT_LEVEL, MAXIMUM_LEVEL);

    @FXML
    private void attachEvents() {
        myGameBorderPane.getScene().setOnKeyPressed(theKeyEvent -> {
            switch (theKeyEvent.getCode()) {
                case T -> {
                    if (HanoiTower.getInstance().getMode() == DEFAULT_MODE &&
                            myMiddleTower.getDiskCount() == 0 &&
                            myRightTower.getDiskCount() == 0) {
                        HanoiTower.getInstance().setMode(TIMED_MODE);
                        SoundManager.playSoundtrack(myGameBorderPane);
                        showTimer();
                        showLevel();
                    }
                }
                case SPACE -> {
                    if (TimerManager.isRan()) {
                        HanoiTower.getInstance().pauseGame();
                        SoundManager.pauseSoundtrack();
                        myGameBorderPane.setDisable(true);
                    } else {
                        HanoiTower.getInstance().resumeGame();
                        SoundManager.resumeSoundtrack();
                        myGameBorderPane.setDisable(false);
                    }
                }
            }
        });

        myExitButton.setOnMouseClicked(theMouseEvent -> {
            try {
                if (HanoiTower.getInstance().getMode() == TIMED_MODE) {
                    TimerManager.cancelTimer();
                }
                SoundManager.playSoundEffect(myExitButton);
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myHelpButton.setOnMouseClicked(theMouseEvent -> {
            try {
                if (HanoiTower.getInstance().getMode() == TIMED_MODE) {
                    HanoiTower.getInstance().pauseGame();
                }

                SoundManager.playSoundEffect(myHelpButton);
                ViewManager.setView(theMouseEvent);
            } catch (IOException e) {
                System.out.println(VIEW_SWITCH_ERROR_MESSAGE);
            }
        });

        myRestartButton.setOnMouseClicked(theMouseEvent -> {
            SoundManager.playSoundEffect(myRestartButton);

            HanoiTower.getInstance().restartGame(HanoiTower.getInstance().getLevel());
            restartGame();
        });
    }

    private void showProgress() {
        double progress = HanoiTower.getInstance().getProgress();
        myProgressProperty.set(progress);
        myProgressBar.progressProperty().bind(myProgressProperty);
    }

    private void showTowers() {
        myLeftTower = new TowerGUI(LEFT_TOWER_ID);
        myLeftTower.setOnMouseClicked(theMouseEvent -> {
            handleDisk(myLeftTower);
            myLeftTower.requestFocus();
        });

        myMiddleTower = new TowerGUI(MIDDLE_TOWER_ID);
        myMiddleTower.setOnMouseClicked(theMouseEvent -> {
            handleDisk(myMiddleTower);
            myMiddleTower.requestFocus();
        });

        myRightTower = new TowerGUI(RIGHT_TOWER_ID);
        myRightTower.setOnMouseClicked(theMouseEvent -> {
            handleDisk(myRightTower);
            myRightTower.requestFocus();
        });

        myTowersHBox.getChildren().addAll(myLeftTower, myMiddleTower, myRightTower);
    }

    private void handleDisk(final TowerGUI theCurrentClickedTower) {
        if (myPreviousClickedTower != null) {   //if a tower is already clicked before
            if (myPreviousClickedTower.equals(theCurrentClickedTower)) {    //if clicks the same tower as before
                if (theCurrentClickedTower.getDiskCount() > 0) {   //if the clicked tower is not empty
                    DiskGUI topDisk = theCurrentClickedTower.peekDisk();

                    if (!topDisk.isPopped()) {  //if the top disk of the clicked tower is not popped
                        HanoiTower.getInstance().popDisk(theCurrentClickedTower.getTower());
                        myPoppedDisk = theCurrentClickedTower.popDisk();
                    }
                    else {    //if the top disk of the clicked tower is not popped
                        if (HanoiTower.getInstance().pushDisk(theCurrentClickedTower.getTower())) {
                            theCurrentClickedTower.pushDisk();
                            updateGameStatistics();

                            if (HanoiTower.getInstance().hasWon()) {
                                ViewManager.setEndView();
                                SoundManager.stopSoundtrack();

                                if (HanoiTower.getInstance().getMode() == TIMED_MODE) {
                                    TimerManager.cancelTimer();
                                }
                            }
                        }
                    }
                }
            }
            else {    //else if clicks a different tower than before
                if (theCurrentClickedTower.getDiskCount() > 0) {   //if the clicked tower is not empty
                    if (myPoppedDisk != null && myPoppedDisk.isPopped()) {  //if the popped disk of the clicked tower is popped and not null
                        myPreviousClickedTower.removeDisk();
                        theCurrentClickedTower.addDisk(myPoppedDisk);
                    }
                    else {    //else if the popped disk of the clicked tower is null or not popped
                        HanoiTower.getInstance().popDisk(theCurrentClickedTower.getTower());
                        myPoppedDisk = theCurrentClickedTower.popDisk();
                    }
                }
                else {    //else if the clicked tower is empty
                    if (myPoppedDisk != null && myPoppedDisk.isPopped()) {  //if the popped disk of the clicked tower is popped and not null
                        myPreviousClickedTower.removeDisk();
                        theCurrentClickedTower.addDisk(myPoppedDisk);
                    }
                }
            }
        }
        else {    //if no towers are clicked before
            if (theCurrentClickedTower.getDiskCount() > 0) {   //if the clicked tower is not empty
                HanoiTower.getInstance().popDisk(theCurrentClickedTower.getTower());
                myPoppedDisk = theCurrentClickedTower.popDisk();
            }
        }

        myPreviousClickedTower = theCurrentClickedTower;
    }

    private void updateGameStatistics() {
        myPoppedDisk = null;
        showMoves();
        showProgress();

        if (HanoiTower.getInstance().getMode() == TIMED_MODE) {
            TimerManager.restartTimer();
        }
    }

    private void showDisks() {
        int level = HanoiTower.getInstance().getLevel();
        DiskGUI[] disks = IntStream.iterate(level - 1, i -> i >= 0, i -> i - 1).mapToObj(this::createDisk).toArray(DiskGUI[]::new);
        myLeftTower.addAllDisks(disks);
    }

    private void resetTowers() {
        myLeftTower.removeAllDisks();
        myMiddleTower.removeAllDisks();
        myRightTower.removeAllDisks();
        myPreviousClickedTower = null;
    }
    private DiskGUI createDisk(final int theLevel) {
        int randomR = new Random().nextInt(255);
        int randomG = new Random().nextInt(255);
        int randomB = new Random().nextInt(255);
        Color randomColor = Color.rgb(randomR, randomG, randomB);

        return new DiskGUI(theLevel, randomColor);
    }

    private void showMinimumMoves() {
        int level = HanoiTower.getInstance().getLevel();
        int minimumMoves = HanoiTower.MINIMUM_MOVES[level-DEFAULT_LEVEL];
        myMinimumMovesLabel.setText(MINIMUM_MOVES_LABEL_PREFIX + minimumMoves);
    }

    private void showMoves() {
        int moves = HanoiTower.getInstance().getMoves();
        myMovesLabel.setText(MOVES_LABEL_PREFIX + moves);
    }

    private void showLevel() {
        if (HanoiTower.getInstance().isPlayed() && HanoiTower.getInstance().getMode() == TIMED_MODE) {
            myLevelSpinner.setDisable(true);
        }

        int level = HanoiTower.getInstance().getLevel();
        myLevelFactory.setValue(level);
        myLevelSpinner.setValueFactory(myLevelFactory);
        myLevelSpinner.valueProperty().addListener((theObservableValue, theOldValue, theNewValue) -> {
            SoundManager.playSoundEffect(myLevelSpinner);
            HanoiTower.getInstance().restartGame(theNewValue);
            restartGame();
        });
    }

    private void showTimer() {
        if (HanoiTower.getInstance().getMode() == DEFAULT_MODE) {
            myTimerLabel.textProperty().unbind();
            myTimerLabel.setText("∞");
        } else {
            myTimerLabel.textProperty().bind(TimerManager.getTimeProperty());
        }
    }

    private void restartGame() {
        resetTowers();
        showDisks();
        showProgress();
        showMoves();
        showMinimumMoves();
        showTimer();
    }

    @Override
    public void initialize(final URL theUrl, final ResourceBundle theResourceBundle) {
        if (!HanoiTower.getInstance().isPlayed()) {
            HanoiTower.getInstance().startGame();
        } else {
            HanoiTower.getInstance().restartGame(HanoiTower.getInstance().getLevel());
        }

        showTowers();
        showDisks();
        showLevel();
        showProgress();
        showMoves();
        showMinimumMoves();
        showTimer();
        SoundManager.playSoundtrack(myGameBorderPane);
    }
}
