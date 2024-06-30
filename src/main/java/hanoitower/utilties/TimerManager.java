package hanoitower.utilties;

import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
    private static final long DEFAULT_DELAY = 1000;

    private static final long RESUME_DELAY = 0;

    private static final long DEFAULT_PERIOD = 1000;

    public static final long END_TIME = 0;

    private static Timer myTimer;

    private static TimerTask myTimerTask;

    private static boolean myRunningState;

    private static long myTime;

    private TimerManager() {

    }

    public static void startCountDownTimer(final long theTime) {
        myRunningState = true;
        myTime = theTime;
        myTimer = new Timer();
        myTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (myRunningState) {
                    if (myTime > END_TIME) {
                        System.out.println(myTime--);
                    } else {
                        myTimer.cancel();
                    }
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTimerTask, DEFAULT_DELAY, DEFAULT_PERIOD);
    }

    public static void cancelCountDownTimer() {
        myTime = END_TIME;
        myTimer.cancel();
    }

    public static void pauseCountDownTimer() {
        myRunningState = false;
    }

    public static void resumeCountDownTimer() {
        myRunningState = true;
        myTimer.scheduleAtFixedRate(myTimerTask, RESUME_DELAY, DEFAULT_PERIOD);
    }

    public static void restartCountDownTimer(final long theTime) {
        cancelCountDownTimer();

        myTime = theTime;
        myTimer = new Timer();
        myTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (myRunningState) {
                    if (myTime > END_TIME) {
                        System.out.println(myTime--);
                    } else {
                        myTimer.cancel();
                    }
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTimerTask, DEFAULT_DELAY, DEFAULT_PERIOD);
    }

    public static long getCurrentTime() {
        return myTime;
    }
}

