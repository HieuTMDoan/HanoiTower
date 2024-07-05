package hanoitower.utilties;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class TimerManager {
    private static final long DEFAULT_PERIOD = 1000;

    public static final long END_TIME = 0;

    private static Timeline myTimeline;

    private static boolean myRunningState = true;

    private static long myTime;

    private static final StringProperty myTimeProperty = new SimpleStringProperty();

    private TimerManager() {

    }

    public static void startCountDownTimer(final long theTime) {
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
                    SoundManager.stopInGame();
                    ViewManager.setEndView();
                }
            }
        }));
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.play();
        myTimeline.getKeyFrames().get(0).getOnFinished().handle(new ActionEvent());
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

    public static void restartCountDownTimer() {
        cancelCountDownTimer();
        startCountDownTimer(myTime);
    }

    private static void updateTimeProperty() {
        myTimeProperty.set(String.valueOf(myTime));
    }

    public static long getCurrentTime() {
        return myTime;
    }

    public static boolean isRan() {
        return myRunningState;
    }

    public static StringProperty getTimeProperty() {
        return myTimeProperty;
    }
}

