package hanoitower.model;

import hanoitower.model.Tower.Disk;
import hanoitower.utilties.MemoryManager;
import hanoitower.utilties.TimerManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static hanoitower.model.HanoiTower.Mode.DEFAULT_MODE;
import static hanoitower.model.HanoiTower.Mode.TIMED_MODE;

public class HanoiTower implements Serializable {
    @Serial
    private static final long serialVersionUID = 1744888976121807340L;

    public static final int MAXIMUM_LEVEL = 8;

    public static final int DEFAULT_LEVEL = 3;

    public static final double DEFAULT_PROGRESS = 0.0;

    public static final double COMPLETE_PROGRESS = 1.00;

    public static final int DEFAULT_MOVES = 0;

    private static final String DEFAULT_NAME = "N/A";

    public static final int[] MINIMUM_MOVES = {7, 15, 31, 63, 127, 255};

    private static final List<HanoiTower> SAVED_GAMES = MemoryManager.readListFromFile();

    public static final long DEFAULT_COUNTDOWN = 15;

    public static final int LEFT_TOWER_ID = 0;

    public static final int MIDDLE_TOWER_ID = 1;

    public static final int RIGHT_TOWER_ID = 2;

    public static final int DEFAULT_DISK_COUNT = 0;

    public static final String LOAD_ERROR_MESSAGE = "Can't find the game with the given name!";

    public static final String SET_MOVES_ERROR_MESSAGE = "Initial moves can't be smaller than 0!";

    public static final String SET_PROGRESS_ERROR_MESSAGE = "Initial progress can't be smaller than 0.00!";

    public static final String SET_MODE_ERROR_MESSAGE = "Mode is either default or timed!";

    public static final String SET_NAME_ERROR_MESSAGE = "Name must be new!";

    private static HanoiTower SINGLE_INSTANCE;

    private String myName;

    private int myLevel;

    private double myProgress;

    private Mode myMode;

    private int myMoves;

    private Tower myLeftTower;

    private Tower myMiddleTower;

    private Tower myRightTower;

    private Tower myPreviousTower;

    private Disk myPoppedDisk;

    private boolean myPlayedState = false;

    public static HanoiTower getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new HanoiTower();
        }
        return SINGLE_INSTANCE;
    }

    private HanoiTower() {

    }

    private HanoiTower(final HanoiTower theGame) {
        myName = theGame.myName;
        myMoves = theGame.myMoves;
        myLevel = theGame.myLevel;
        myProgress = theGame.myProgress;
        myMode = theGame.myMode;
        myPlayedState = theGame.myPlayedState;
        myPoppedDisk = theGame.myPoppedDisk;
        myPreviousTower = theGame.myPreviousTower;
        myLeftTower = theGame.myLeftTower;
        myMiddleTower = theGame.myMiddleTower;
        myRightTower = theGame.myRightTower;
    }

    public void popDisk(final Tower theTower) {
        if (myPoppedDisk != null) {
            return;
        }

        if (myLeftTower.equals(theTower)) {
            myPoppedDisk = myLeftTower.popDisk();
        } 
        else if (myMiddleTower.equals(theTower)) {
            myPoppedDisk = myMiddleTower.popDisk();
        } 
        else {
            myPoppedDisk = myRightTower.popDisk();
        }

        myPreviousTower = theTower;
    }

    public boolean pushDisk(final Tower theTower) {
        if (myPoppedDisk == null) {
            return false;
        }

        boolean canPush;
        if (myLeftTower.equals(theTower)) {
            canPush = myLeftTower.canPush(myPoppedDisk);
            myLeftTower.pushDisk(myPoppedDisk);
        } 
        else if (myMiddleTower.equals(theTower)) {
            canPush = myMiddleTower.canPush(myPoppedDisk);
            myMiddleTower.pushDisk(myPoppedDisk);
        } 
        else {
            canPush = myRightTower.canPush(myPoppedDisk);
            myRightTower.pushDisk(myPoppedDisk);
        }

        if (!myPreviousTower.equals(theTower) && canPush) {
            updateGame();
            myPreviousTower = theTower;
        }

        myPoppedDisk = canPush ? null : myPoppedDisk;
        return canPush;
    }

    public void startGame() {
        setLevel(DEFAULT_LEVEL);
        setMoves(DEFAULT_MOVES);
        setProgress(DEFAULT_PROGRESS);
        setMode(DEFAULT_MODE);
        setTowers();
        setPlayed(true);
    }

    private void updateGame() {
        setMoves(++myMoves);
        setProgress((double) myRightTower.getDiskCount() / myLevel);

        if (hasWon()) {
            SAVED_GAMES.remove(this);
        }
    }

    public void pauseGame() {
        TimerManager.pauseTimer();
    }

    public void resumeGame() {
        TimerManager.resumeTimer();
    }

    public void restartGame(final int theLevel) {
        setLevel(theLevel);
        setMoves(DEFAULT_MOVES);
        setProgress(DEFAULT_PROGRESS);
        setTowers();
        if (myMode == TIMED_MODE) {
            TimerManager.restartTimer();
        }
    }

    public void saveGame() {
        HanoiTower savedGame = new HanoiTower(SINGLE_INSTANCE);
        SAVED_GAMES.add(savedGame);
        MemoryManager.writeListToFile(SAVED_GAMES);

        SINGLE_INSTANCE = null;
    }

    public void loadGame(final String theName) {
        HanoiTower loadedGame = SAVED_GAMES.stream().filter(game -> game.myName.equals(theName)).findFirst().orElse(null);

        if (loadedGame == null) {
            throw new IllegalArgumentException(LOAD_ERROR_MESSAGE);
        }

        SINGLE_INSTANCE = loadedGame;
    }

    /*******************************************************************************************************************
     *                                                 SETTER METHODS                                                  *
     *******************************************************************************************************************/
    public void setPlayed(final boolean thePlayedState) {
        myPlayedState = thePlayedState;
    }

    private void setLevel(final int theLevel) {
        if (theLevel > MAXIMUM_LEVEL) {
            myLevel = MAXIMUM_LEVEL;
        } else {
            myLevel = Math.max(theLevel, DEFAULT_LEVEL);
        }
    }

    private void setMoves(final int theMoves) {
        if (theMoves < DEFAULT_MOVES) {
            throw new IllegalArgumentException(SET_MOVES_ERROR_MESSAGE);
        } else {
            myMoves = theMoves;
        }
    }

    private void setProgress(final double theProgress) {
        if (theProgress < DEFAULT_PROGRESS) {
            throw new IllegalArgumentException(SET_PROGRESS_ERROR_MESSAGE);
        } else {
            myProgress = theProgress;
        }
    }

    public void setMode(final Mode theMode) {
        if (theMode != DEFAULT_MODE && theMode != TIMED_MODE) {
            throw new IllegalArgumentException(SET_MODE_ERROR_MESSAGE);
        }

        if (theMode == TIMED_MODE && myMode == DEFAULT_MODE) {
            TimerManager.startTimer(DEFAULT_COUNTDOWN);
        } else if (theMode == DEFAULT_MODE && myMode == TIMED_MODE) {
            TimerManager.cancelTimer();
        }

        myMode = theMode;
    }

    public void setName(final String theName) {
        List<String> savedNames = SAVED_GAMES.stream().map(HanoiTower::getName).toList();
        String tempName = (theName.isEmpty() || theName.isBlank()) ? DEFAULT_NAME : theName;

        if (!savedNames.contains(tempName)) {
            myName = tempName;
        } else {
            throw new IllegalArgumentException(SET_NAME_ERROR_MESSAGE);
        }
    }

    private void setTowers() {
        myPreviousTower = null;
        myPoppedDisk = null;
        myLeftTower = new Tower(myLevel, LEFT_TOWER_ID);
        myMiddleTower = new Tower(DEFAULT_DISK_COUNT, MIDDLE_TOWER_ID);
        myRightTower = new Tower(DEFAULT_DISK_COUNT, RIGHT_TOWER_ID);
    }

    /*******************************************************************************************************************
     *                                                 GETTER METHODS                                                  *
     *******************************************************************************************************************/
    public static List<HanoiTower> getSavedGames() {
        /*
        ensures the returned list is unmodifiable,
        preventing unwanted changes to the original list
         */
        return List.copyOf(SAVED_GAMES);
    }

    public boolean isPlayed() {
        return myPlayedState;
    }

    public String getName() {
        return myName;
    }

    public Mode getMode() {
        return myMode;
    }

    public int getLevel() {
        return myLevel;
    }

    public int getMoves() {
        return myMoves;
    }

    public double getProgress() {
        return myProgress;
    }

    /*******************************************************************************************************************
     *                                                 HELPER METHODS                                                  *
     *******************************************************************************************************************/
    public boolean hasWon() {
        boolean hasWon;
        
        if (myMode == DEFAULT_MODE) {
            hasWon = (myRightTower.getDiskCount() == myLevel) && (myProgress == COMPLETE_PROGRESS);
        }
        else {
            hasWon = (TimerManager.getCurrentTime() > TimerManager.END_TIME) && (myRightTower.getDiskCount() == myLevel) && (myProgress == COMPLETE_PROGRESS);
        }
        
        return hasWon;
    }

    @Override
    public String toString() {
        return String.format("(Name: %s, Level: %d, Progress: %.2f, Mode: %s)", myName, myLevel, myProgress, myMode);
    }

    /*******************************************************************************************************************
     *                                                 PUBLIC ENUM                                                     *
     *******************************************************************************************************************/
    public enum Mode {
        DEFAULT_MODE,

        TIMED_MODE
    }
}
