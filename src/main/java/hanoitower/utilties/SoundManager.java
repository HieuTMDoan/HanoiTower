package hanoitower.utilties;

import hanoitower.model.HanoiTower;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;

public class SoundManager {
    private static final double SOUNDTRACK_VOLUME = 1.0;

    private static final double SOUND_FX_VOLUME = 0.5;

    private static final String PUSH_SOUND_FILE_PATH = "/hanoitower/sound/push-sound.m4a";

    private static final String POP_SOUND_FILE_PATH = "/hanoitower/sound/pop-sound.m4a";

    private static final String CLICK_SOUND_FILE_PATH = "/hanoitower/sound/click-sound.m4a";

    private static final String CHANGE_LEVEL_SOUND_FILE_PATH = "/hanoitower/sound/change-level-sound.m4a";

    private static final String WIN_SOUND_FILE_PATH = "/hanoitower/sound/win-sound.mp3";

    private static final String LOSE_SOUND_FILE_PATH = "/hanoitower/sound/lose-sound.mp3";

    private static final String INTRO_SOUNDTRACK_FILE_PATH = "/hanoitower/sound/intro-soundtrack.mp3";

    private static final String GAME_SOUNDTRACK_FILE_PATH = "/hanoitower/sound/game-soundtrack.mp3";

    private static final URL PUSH_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(PUSH_SOUND_FILE_PATH));

    private static final URL POP_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(POP_SOUND_FILE_PATH));

    private static final URL CLICK_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(CLICK_SOUND_FILE_PATH));

    private static final URL CHANGE_LEVEL_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(CHANGE_LEVEL_SOUND_FILE_PATH));

    private static final URL WIN_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(WIN_SOUND_FILE_PATH));

    private static final URL LOSE_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(LOSE_SOUND_FILE_PATH));

    private static final AudioClip PUSH_SOUND = new AudioClip(PUSH_SOUND_URL.toString());

    private static final AudioClip POP_SOUND = new AudioClip(POP_SOUND_URL.toString());

    private static final AudioClip CHANGE_LEVEL_SOUND = new AudioClip(CHANGE_LEVEL_SOUND_URL.toString());

    private static final AudioClip CLICK_SOUND = new AudioClip(CLICK_SOUND_URL.toString());

    private static final AudioClip WIN_SOUND = new AudioClip(WIN_SOUND_URL.toString());

    private static final AudioClip LOSE_SOUND = new AudioClip(LOSE_SOUND_URL.toString());

    private static final Media INTRO_SOUNDTRACK_MEDIA = new Media(Objects.requireNonNull(SoundManager.class.getResource(INTRO_SOUNDTRACK_FILE_PATH)).toString());

    private static final MediaPlayer INTRO_SOUNDTRACK = new MediaPlayer(INTRO_SOUNDTRACK_MEDIA);

    private static final Media GAME_SOUNDTRACK_MEDIA = new Media(Objects.requireNonNull(SoundManager.class.getResource(GAME_SOUNDTRACK_FILE_PATH)).toString());

    private static final MediaPlayer GAME_SOUNDTRACK = new MediaPlayer(GAME_SOUNDTRACK_MEDIA);

    private SoundManager() {

    }

    @FXML
    public static void playPush() {
        PUSH_SOUND.setVolume(SOUND_FX_VOLUME);
        PUSH_SOUND.play();
    }

    @FXML
    public static void playPop() {
        POP_SOUND.setVolume(SOUND_FX_VOLUME);
        POP_SOUND.play();
    }

    @FXML
    public static void playClick() {
        CLICK_SOUND.setVolume(SOUND_FX_VOLUME);
        CLICK_SOUND.play();
    }

    @FXML
    public static void playChangeLevel() {
        CHANGE_LEVEL_SOUND.setVolume(SOUND_FX_VOLUME);
        CHANGE_LEVEL_SOUND.play();
    }

    @FXML
    public static void playIntro() {
        INTRO_SOUNDTRACK.setCycleCount(MediaPlayer.INDEFINITE);
        INTRO_SOUNDTRACK.setVolume(SOUNDTRACK_VOLUME);
        INTRO_SOUNDTRACK.play();
    }

    @FXML
    public static void stopIntro() {
        INTRO_SOUNDTRACK.stop();
    }

    @FXML
    public static void playInGame() {
        GAME_SOUNDTRACK.stop();
        GAME_SOUNDTRACK.setCycleCount(MediaPlayer.INDEFINITE);
        GAME_SOUNDTRACK.setVolume(SOUNDTRACK_VOLUME - 0.5);
        GAME_SOUNDTRACK.play();
    }

    @FXML
    public static void pauseInGame() {
        GAME_SOUNDTRACK.pause();
    }

    @FXML
    public static void resumeInGame() {
        GAME_SOUNDTRACK.play();
    }

    @FXML
    public static void stopInGame() {
        GAME_SOUNDTRACK.stop();
    }

    @FXML
    public static void playOutro() {
        if (HanoiTower.getInstance().hasWon()) {
            WIN_SOUND.setVolume(SOUNDTRACK_VOLUME);
            WIN_SOUND.play();
        } else {
            LOSE_SOUND.setVolume(SOUNDTRACK_VOLUME);
            LOSE_SOUND.play();
        }
    }

    @FXML
    public static void stopOutro() {
        if (HanoiTower.getInstance().hasWon()) {
            WIN_SOUND.stop();
        } else {
            LOSE_SOUND.stop();
        }
    }
}
