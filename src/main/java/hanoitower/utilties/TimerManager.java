package hanoitower.utilties;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

public class TimerManager {
    private static final long DEFAULT_PERIOD = 1000;

    public static final long END_TIME = 0;

    private static Timeline myTimeline;

    private static boolean myRunningState;

    private static long myTime;

    private static final StringProperty timeProperty = new SimpleStringProperty();

    private TimerManager() {

    }

    public static void startCountDownTimer(final long theTime) {
        myRunningState = true;
        myTime = theTime;
        updateTimeProperty();

        myTimeline = new Timeline(new KeyFrame(Duration.millis(DEFAULT_PERIOD), event -> {
            if (myRunningState) {
                if (myTime > END_TIME) {
                    updateTimeProperty();
                    System.out.println(myTime--);
                } else {
                    updateTimeProperty();
                    myTimeline.stop();
                    ViewManager.showEndView();
                }
            }
        }));
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.play();
    }

    public static void cancelCountDownTimer() {
        if (myTimeline != null) {
            myTimeline.stop();
        }
    }

    public static void pauseCountDownTimer() {
        myRunningState = false;
    }

    public static void resumeCountDownTimer() {
        myRunningState = true;
    }

    public static void restartCountDownTimer(final long theTime) {
        cancelCountDownTimer();
        startCountDownTimer(theTime);
    }

    private static void updateTimeProperty() {
        timeProperty.set(String.valueOf(myTime));
    }

    public static long getCurrentTime() {
        return myTime;
    }

    public static boolean getRunningState() {
        return myRunningState;
    }

    public static StringProperty getTimeProperty() {
        return timeProperty;
    }
}

