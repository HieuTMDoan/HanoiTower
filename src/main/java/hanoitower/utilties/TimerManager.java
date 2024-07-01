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

//import java.util.Timer;
//import java.util.TimerTask;
//
//public class TimerManager {
//    private static final long DEFAULT_DELAY = 1000;
//
//    private static final long RESUME_DELAY = 0;
//
//    private static final long DEFAULT_PERIOD = 1000;
//
//    public static final long END_TIME = 0;
//
//    private static Timer myTimer;
//
//    private static TimerTask myTimerTask;
//
//    private static boolean myRunningState;
//
//    private static long myTime;
//
//    private TimerManager() {
//
//    }
//
//    public static void startCountDownTimer(final long theTime) {
//        myRunningState = true;
//        myTime = theTime;
//        myTimer = new Timer();
//        myTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if (myRunningState) {
//                    if (myTime > END_TIME) {
//                        System.out.println(myTime--);
//                    } else {
//                        myTimer.cancel();
//                    }
//                }
//            }
//        };
//
//        myTimer.scheduleAtFixedRate(myTimerTask, DEFAULT_DELAY, DEFAULT_PERIOD);
//    }
//
//    public static void cancelCountDownTimer() {
//        myTime = END_TIME;
//        myTimer.cancel();
//    }
//
//    public static void pauseCountDownTimer() {
//        myRunningState = false;
//    }
//
//    public static void resumeCountDownTimer() {
//        myRunningState = true;
//        myTimer.scheduleAtFixedRate(myTimerTask, RESUME_DELAY, DEFAULT_PERIOD);
//    }
//
//    public static void restartCountDownTimer(final long theTime) {
//        cancelCountDownTimer();
//
//        myTime = theTime;
//        myTimer = new Timer();
//        myTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if (myRunningState) {
//                    if (myTime > END_TIME) {
//                        System.out.println(myTime--);
//                    } else {
//                        myTimer.cancel();
//                    }
//                }
//            }
//        };
//
//        myTimer.scheduleAtFixedRate(myTimerTask, DEFAULT_DELAY, DEFAULT_PERIOD);
//    }
//
//    public static long getCurrentTime() {
//        return myTime;
//    }
//}

