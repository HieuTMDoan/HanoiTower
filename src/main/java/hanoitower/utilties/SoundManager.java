package hanoitower.utilties;

import hanoitower.model.HanoiTower;
import hanoitower.view.TowerGUI;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.Objects;

import static hanoitower.model.HanoiTower.Mode.DEFAULT_MODE;
import static hanoitower.model.HanoiTower.Mode.TIMED_MODE;

public class SoundManager {
    private static final double SOUNDTRACK_VOLUME = 0.5;

    private static final double SOUND_FX_VOLUME = 1.0;

    private static final String SOUND_DIRECTORY_PATH_FROM_CONTENT_ROOT = "src/main/resources/hanoitower/sound/";

    public static final String SOUND_DIRECTORY_PATH_FROM_SOURCE_ROOT = "/hanoitower/sound/";

    private static String mySoundFxFilePath;

    private static String mySoundtrackFilePath;

    private static AudioClip mySoundFx;

    private static MediaPlayer mySoundtrack;

    private SoundManager() {

    }

    public static void playSoundEffect(final Node theSourceNode) {
        switch (theSourceNode.getTypeSelector()) {
            case "TowerGUI" -> {
                TowerGUI tower = ((TowerGUI) theSourceNode);

                if (tower.peekDisk().isPopped()) {
                    mySoundFxFilePath = getSoundFilePath("push");
                } else {
                    mySoundFxFilePath = getSoundFilePath("pop");
                }
            }

            case "HBox" -> {
                if (HanoiTower.getInstance().hasWon()) {
                    mySoundFxFilePath = getSoundFilePath("win");
                } else {
                    mySoundFxFilePath = getSoundFilePath("lose");
                }
            }

            case "Button" -> mySoundFxFilePath = getSoundFilePath("click");

            case "Spinner" -> mySoundFxFilePath = getSoundFilePath("change");
        }

        URL mySoundFxURL = Objects.requireNonNull(SoundManager.class.getResource(mySoundFxFilePath));
        mySoundFx = new AudioClip(mySoundFxURL.toString());
        mySoundFx.setVolume(SOUND_FX_VOLUME);
        mySoundFx.play();
    }

    public static void stopSoundEffect() {
        mySoundFx.stop();
    }

    public static void playSoundtrack(final Pane theRootNode) {
        switch (theRootNode.getId()) {
            case "myHomeHBox" -> mySoundtrackFilePath = getSoundFilePath("intro");

            case "myGameBorderPane" -> {
                mySoundtrack.stop();

                if (HanoiTower.getInstance().getMode() == TIMED_MODE) {
                    mySoundtrackFilePath = getSoundFilePath("timed");
                } else if (HanoiTower.getInstance().getMode() == DEFAULT_MODE) {
                    mySoundtrackFilePath = getSoundFilePath("default");
                }
            }
        }

        Media mySoundtrackMedia = new Media(Objects.requireNonNull(SoundManager.class.getResource(mySoundtrackFilePath)).toString());
        mySoundtrack = new MediaPlayer(mySoundtrackMedia);
        mySoundtrack.setCycleCount(MediaPlayer.INDEFINITE);
        mySoundtrack.setVolume(SOUNDTRACK_VOLUME);
        mySoundtrack.play();
    }

    public static void stopSoundtrack() {
        mySoundtrack.stop();
    }

    public static void pauseSoundtrack() {
        mySoundtrack.pause();
    }

    public static void resumeSoundtrack() {
        mySoundtrack.play();
    }

    private static String getSoundFilePath(final String theKeyword) {
        String soundPath = "";
        File directory = new File(SOUND_DIRECTORY_PATH_FROM_CONTENT_ROOT);

        if (directory.isDirectory()) {
            File[] listOfFiles = directory.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.getName().contains(theKeyword.toLowerCase())) {
                        soundPath = SOUND_DIRECTORY_PATH_FROM_SOURCE_ROOT + file.getName();
                        break;
                    }
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }

        return soundPath;
    }
}
