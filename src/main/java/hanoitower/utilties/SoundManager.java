package hanoitower.utilties;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.Objects;

public class SoundManager {
    private static final String SOUND_DIRECTORY_PATH = "src/main/resources/hanoitower/sound/";

    private static final String SOFT_IMPACT_FILE_PATH = "/hanoitower/sound/soft-impact.mp3";

    private static final String OFFICIAL_SOUNDTRACK_FILE_PATH = "/hanoitower/sound/official-soundtrack.mp3";

    private static final URL SOFT_IMPACT_URL = Objects.requireNonNull(SoundManager.class.getResource(SOFT_IMPACT_FILE_PATH));

    private static final URL OFFICIAL_SOUNDTRACK_URL = Objects.requireNonNull(SoundManager.class.getResource(OFFICIAL_SOUNDTRACK_FILE_PATH));

    private static final AudioClip SOFT_IMPACT = new AudioClip(SOFT_IMPACT_URL.toString());

    private static final AudioClip SOUNDTRACK = new AudioClip(OFFICIAL_SOUNDTRACK_URL.toString());

    private static final double DEFAULT_VOLUME = 0.5;

    private static final double IN_GAME_VOLUME = 0.3;

    private SoundManager() {

    }

    @FXML
    public static void playSoundEffect() {
        SOUNDTRACK.setVolume(DEFAULT_VOLUME);
        SOFT_IMPACT.play();
    }

    @FXML
    public static void playSoundtrack() {
        SOUNDTRACK.setVolume(DEFAULT_VOLUME);
        SOUNDTRACK.setCycleCount(AudioClip.INDEFINITE);
        SOUNDTRACK.play();
    }

    @FXML
    public static void playInGameSoundtrack() {
        SOUNDTRACK.setVolume(IN_GAME_VOLUME);
    }
}
