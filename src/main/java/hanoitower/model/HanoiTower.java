package hanoitower.model;

import hanoitower.model.Tower.Disk;
import hanoitower.utilties.TimerManager;

import java.util.ArrayList;
import java.util.List;

import static hanoitower.model.HanoiTower.Mode.DEFAULT_MODE;
import static hanoitower.model.HanoiTower.Mode.TIMED_MODE;

public class HanoiTower {
    public static final int MAXIMUM_LEVEL = 8;

    public static final int DEFAULT_LEVEL = 3;

    public static final double DEFAULT_PROGRESS = 0.0;

    public static final double COMPLETE_PROGRESS = 1.00;

    public static final int DEFAULT_MOVES = 0;

    private static final String DEFAULT_NAME = "N/A";

    public static final int[] MINIMUM_MOVES = {7, 15, 31, 63, 127, 255};

    private static final List<HanoiTower> SAVED_GAMES = new ArrayList<>();

    private static final long DEFAULT_COUNTDOWN = 30;

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

    public static HanoiTower getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new HanoiTower();
        }
        return SINGLE_INSTANCE;
    }

    private HanoiTower() {
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
    }

    private void updateGame() {
        setMoves(++myMoves);
        setProgress((double) myRightTower.getDiskCount() / myLevel);
    }

    public void pauseGame() {
        TimerManager.pauseCountDownTimer();
    }

    public void resumeGame() {
        TimerManager.resumeCountDownTimer();
    }

    public void restartGame(final int theLevel) {
        setLevel(theLevel);
        setMoves(DEFAULT_MOVES);
        setProgress(DEFAULT_PROGRESS);
        setTowers();
        if (myMode == TIMED_MODE) {
            TimerManager.restartCountDownTimer(DEFAULT_COUNTDOWN);
        }
    }

    public void endGame() {
        if (hasWon()) {
            SAVED_GAMES.remove(this);
        }
    }

    public static void saveGame(final HanoiTower theGame) {
        SAVED_GAMES.add(theGame);
    }

    public static HanoiTower loadGame(final String theName) {
        HanoiTower loadGame = null;

        for (final HanoiTower game : SAVED_GAMES) {
            if (game.myName.equals(theName)) {
                loadGame = game;
                break;
            }
        }

        if (loadGame == null) {
            throw new IllegalArgumentException(LOAD_ERROR_MESSAGE);
        }

        return loadGame;
    }

    /*******************************************************************************************************************
     *                                                 SETTER METHODS                                                  *
     *******************************************************************************************************************/
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
            TimerManager.startCountDownTimer(DEFAULT_COUNTDOWN);
        } else if (theMode == DEFAULT_MODE && myMode == TIMED_MODE) {
            TimerManager.cancelCountDownTimer();
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
        myLeftTower = new Tower(myLevel, LEFT_TOWER_ID);
        myMiddleTower = new Tower(DEFAULT_DISK_COUNT, MIDDLE_TOWER_ID);
        myRightTower = new Tower(DEFAULT_DISK_COUNT, RIGHT_TOWER_ID);
    }

    /*******************************************************************************************************************
     *                                                 GETTER METHODS                                                  *
     *******************************************************************************************************************/
    public static List<HanoiTower> getSavedGames() {
        return List.copyOf(SAVED_GAMES);
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

    public Tower[] getTowers() {
        return new Tower[] {myLeftTower, myMiddleTower, myRightTower};
    }

    public long getTime() {
        return TimerManager.getCurrentTime();
    }

    /*******************************************************************************************************************
     *                                                 HELPER METHODS                                                  *
     *******************************************************************************************************************/
    private boolean hasWon() {
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
        return "(" + myName + ", " + myLevel + ", " + myProgress + ", " + myMode + ")";
    }

    /*******************************************************************************************************************
     *                                                 PUBLIC ENUM                                                     *
     *******************************************************************************************************************/
    public enum Mode {
        DEFAULT_MODE,

        TIMED_MODE
    }
}
