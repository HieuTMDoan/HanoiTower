package model;

import model.Tower.Disk;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static model.HanoiTower.Mode.*;

public final class HanoiTower {
    public static final int MAXIMUM_LEVEL = 8;

    public static final int DEFAULT_LEVEL = 3;

    public static final String DEFAULT_NAME = "N/A";

    public static final int[] MINIMUM_MOVES = {7, 15, 31, 63, 127, 255};

    private static final List<HanoiTower> SAVED_GAMES = new ArrayList<>();

    private static final int DEFAULT_COUNTDOWN = 30;

    private static HanoiTower SINGLE_INSTANCE;

    private String myName;

    private int myLevel;

    private double myProgress;

    private Mode myMode;

    private int myMoves;

    private int myTime;

    private final Timer myTimer = new Timer();

    private Tower myLeft;

    private Tower myMiddle;

    private Tower myRight;

    private Disk myPopDisk;

    public static HanoiTower getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new HanoiTower();
        }
        return SINGLE_INSTANCE;
    }

    public HanoiTower() {
        startGame(DEFAULT_NAME, DEFAULT_LEVEL, 0.0, DEFAULT_MODE);
    }

    public HanoiTower(final String theName, final int theCount, double theProgress, final Mode theMode) {
        startGame(theName, theCount, theProgress, theMode);
    }

    private void startGame(final String theName, final int theCount, double theProgress, final Mode theMode) {
        setName(theName);
        setLevel(theCount);
        setProgress(theProgress);
        setMode(theMode);
        setTowers();
    }

    public void popDisk(final Tower theTower) {
        if (myPopDisk != null) {
            return;
        }

        if (myLeft.equals(theTower)) {
            myPopDisk = myLeft.popDisk();
        } 
        else if (myMiddle.equals(theTower)) {
            myPopDisk = myMiddle.popDisk();
        } 
        else {
            myPopDisk = myRight.popDisk();
        }
        updateGame();
    }

    public void pushDisk(final Tower theTower) {
        if (myPopDisk == null) {
            return;
        }

        boolean canPush;
        if (myLeft.equals(theTower)) {
            canPush = myLeft.canPush(myPopDisk);
            myLeft.pushDisk(myPopDisk);
        } 
        else if (myMiddle.equals(theTower)) {
            canPush = myMiddle.canPush(myPopDisk);
            myMiddle.pushDisk(myPopDisk);
        } 
        else {
            canPush = myRight.canPush(myPopDisk);
            myRight.pushDisk(myPopDisk);
        }
        myPopDisk = canPush ? null : myPopDisk;
        updateGame();
    }

    private void updateGame() {
        myMoves++;
        setProgress((double) myRight.getDiskCount() / myLevel);
    }

    public void pauseGame() {
        pauseTimer();
    }

    public void resumeGame() {
        resumeTimer();
    }

    public void restartGame() {
        startGame(myName, myLevel, myProgress, myMode);
    }

    public void endGame() {
        if (hasWon()) {
            SAVED_GAMES.remove(this);
        }

        System.out.println(hasWon() ? "You won." : "You lost.");
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
            throw new IllegalArgumentException("Can't find the game with the given name!");
        }

        return loadGame;
    }

    /*******************************************************************************************************************
     *                                                 SETTER METHODS                                                  *
     *******************************************************************************************************************/
    public void setLevel(final int theLevel) {
        if (theLevel > MAXIMUM_LEVEL) {
            myLevel = MAXIMUM_LEVEL;
        } else {
            myLevel = Math.max(theLevel, DEFAULT_LEVEL);
        }
    }

    private void setProgress(final double theProgress) {
        if (theProgress < 0.00) {
            throw new IllegalArgumentException("Initial progress can't be smaller than 0.00!");
        } else {
            myProgress = theProgress;
        }
    }

    public void setMode(final Mode theMode) {
        if (theMode != DEFAULT_MODE && theMode != TIMED_MODE) {
            throw new IllegalArgumentException("Mode is either default or timed!");
        }

        myMode = theMode;

        if (theMode == TIMED_MODE) {
            myTime = DEFAULT_COUNTDOWN;
            startTimer();
        }
    }

    public void setName(final String theName) {
        List<String> savedNames = SAVED_GAMES.stream().map(HanoiTower::getName).toList();
        String tempName = (theName.isEmpty() || theName.isBlank()) ? DEFAULT_NAME : theName;

        if (!savedNames.contains(tempName)) {
            myName = tempName;
        } else {
            throw new IllegalArgumentException("Name must be new!");
        }
    }

    private void setTowers() {
        myLeft = new Tower(myLevel);
        myMiddle = new Tower(0);
        myRight = new Tower(0);
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
        return new Tower[] {myLeft, myMiddle, myRight};
    }

    /*******************************************************************************************************************
     *                                                 HELPER METHODS                                                  *
     *******************************************************************************************************************/
    private boolean hasWon() {
        boolean hasWon;
        
        if (myMode == DEFAULT_MODE) {
            hasWon = (myRight.getDiskCount() == myLevel) && (myProgress == 1.00);
        } else {
            hasWon = (myTime > 0) && (myRight.getDiskCount() == myLevel) && (myProgress == 1.00);
        }
        
        return hasWon;
    }

    private void startTimer() {
        TimerTask countDown = new TimerTask() {
            @Override
            public void run() {
                System.out.println(myTime--);
                if (myTime < 0) {
                    myTimer.cancel();
                    endGame();
                }
            }
        };

        myTimer.scheduleAtFixedRate(countDown, 1000, 1000);
    }

    private void pauseTimer() {
        myTimer.cancel();
    }

    private void resumeTimer() {
        startTimer();
    }

    private void resetTimer() {
        myTime = DEFAULT_COUNTDOWN;
        startTimer();
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
