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

    public static void playTranslateTransition(final Node theNode, final double theYTranslate) {
        TranslateTransition translateUp = new TranslateTransition(TRANSITION_DURATION, theNode);
        translateUp.setToY(theYTranslate);
        translateUp.play();
    }

    public static void playFadeTransition(final Node theNode, final boolean theIsFadeIn) {
        FadeTransition fadeOut = new FadeTransition(TRANSITION_DURATION, theNode);
        fadeOut.setFromValue(theIsFadeIn ? INVISIBLE : VISIBLE);
        fadeOut.setToValue(theIsFadeIn ? VISIBLE : INVISIBLE);
        fadeOut.play();
    }
}
