package hanoitower.utilties;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;

public class SoundManager {
    private static final double DEFAULT_VOLUME = 0.5;

    private static final double IN_GAME_VOLUME = 0.1;

    private static final String SOUND_DIRECTORY_PATH = "src/main/resources/hanoitower/sound/";

    private static final String PUSH_SOUND_FILE_PATH = "/hanoitower/sound/push-sound.mp3";

    private static final String POP_SOUND_FILE_PATH = "/hanoitower/sound/pop-sound.mp3";

    private static final String CLICK_SOUND_FILE_PATH = "/hanoitower/sound/click-sound.mp3";

    private static final String INTRO_SOUNDTRACK_FILE_PATH = "/hanoitower/sound/intro-soundtrack.mp3";

    private static final String GAME_SOUNDTRACK_FILE_PATH = "/hanoitower/sound/game-soundtrack.mp3";

    private static final String OUTRO_SOUNDTRACK_FILE_PATH = "/hanoitower/sound/outro-soundtrack.mp3";

    private static final URL PUSH_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(PUSH_SOUND_FILE_PATH));

//    private static final URL POP_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(POP_SOUND_FILE_PATH));
//
//    private static final URL CLICK_SOUND_URL = Objects.requireNonNull(SoundManager.class.getResource(CLICK_SOUND_FILE_PATH));

    private static final AudioClip PUSH_SOUND = new AudioClip(PUSH_SOUND_URL.toString());

//    private static final AudioClip POP_SOUND = new AudioClip(POP_SOUND_URL.toString());
//
//    private static final AudioClip CLICK_SOUND = new AudioClip(CLICK_SOUND_URL.toString());

    private static final Media INTRO_SOUNDTRACK_MEDIA = new Media(Objects.requireNonNull(SoundManager.class.getResource(INTRO_SOUNDTRACK_FILE_PATH)).toString());

    private static final MediaPlayer INTRO_SOUNDTRACK = new MediaPlayer(INTRO_SOUNDTRACK_MEDIA);

//    private static final Media GAME_SOUNDTRACK_MEDIA = new Media(Objects.requireNonNull(SoundManager.class.getResource(GAME_SOUNDTRACK_FILE_PATH)).toString());
//
//    private static final MediaPlayer GAME_SOUNDTRACK = new MediaPlayer(GAME_SOUNDTRACK_MEDIA);
//
//    private static final Media OUTRO_SOUNDTRACK_MEDIA = new Media(Objects.requireNonNull(SoundManager.class.getResource(OUTRO_SOUNDTRACK_FILE_PATH)).toString());
//
//    private static final MediaPlayer OUTRO_SOUNDTRACK = new MediaPlayer(OUTRO_SOUNDTRACK_MEDIA);

    private SoundManager() {

    }

    @FXML
    public static void playPush() {
        PUSH_SOUND.setVolume(DEFAULT_VOLUME);
        PUSH_SOUND.play();
    }

//    @FXML
//    public static void playPop() {
//        POP_SOUND.setVolume(DEFAULT_VOLUME);
//        POP_SOUND.play();
//    }
//
//    @FXML
//    public static void playClick() {
//        CLICK_SOUND.setVolume(DEFAULT_VOLUME);
//        CLICK_SOUND.play();
//    }

    @FXML
    public static void playIntro() {
        INTRO_SOUNDTRACK.stop();
        INTRO_SOUNDTRACK.setCycleCount(MediaPlayer.INDEFINITE);
        INTRO_SOUNDTRACK.setVolume(DEFAULT_VOLUME);
        INTRO_SOUNDTRACK.play();
    }

//    @FXML
//    public static void playInGame() {
//        GAME_SOUNDTRACK.stop();
//        GAME_SOUNDTRACK.setCycleCount(MediaPlayer.INDEFINITE);
//        GAME_SOUNDTRACK.setVolume(IN_GAME_VOLUME);
//        GAME_SOUNDTRACK.play();
//    }
//
//    @FXML
//    public static void playOutro() {
//        OUTRO_SOUNDTRACK.stop();
//        OUTRO_SOUNDTRACK.setCycleCount(MediaPlayer.INDEFINITE);
//        OUTRO_SOUNDTRACK.setVolume(IN_GAME_VOLUME);
//        OUTRO_SOUNDTRACK.play();
//    }
//
//    @FXML
//    public static void pauseInGame() {
//        GAME_SOUNDTRACK.pause();
//    }
}
