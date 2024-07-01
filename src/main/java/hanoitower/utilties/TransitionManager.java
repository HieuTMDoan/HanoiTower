package hanoitower.utilties;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionManager {
    private static final Duration TRANSITION_DURATION = Duration.seconds(0.3);

    public static final double INVISIBLE = 0.0;

    public static final double VISIBLE = 1.0;

    private TransitionManager() {

    }

    public static void playSlideUp(final Node theNode, final double theYTranslate) {
        TranslateTransition translateUp = new TranslateTransition(TRANSITION_DURATION, theNode);
        translateUp.setToY(-theYTranslate);
        translateUp.play();
    }

    public static void playSlideDown(final Node theNode, final double theYTranslate) {
        TranslateTransition translateDown = new TranslateTransition(TRANSITION_DURATION, theNode);
        translateDown.setToY(theYTranslate);
        translateDown.play();
    }

    public static void playFadeIn(final Node theNode) {
        FadeTransition fadeIn = new FadeTransition(TRANSITION_DURATION, theNode);
        fadeIn.setFromValue(INVISIBLE);
        fadeIn.setToValue(VISIBLE);
        fadeIn.play();
    }

    public static void playFadeOut(final Node theNode) {
        FadeTransition fadeOut = new FadeTransition(TRANSITION_DURATION, theNode);
        fadeOut.setFromValue(VISIBLE);
        fadeOut.setToValue(INVISIBLE);
        fadeOut.play();
    }
}
