package hanoitower.utilties;

import hanoitower.model.HanoiTower;

import java.util.*;
import java.io.*;

public class MemoryManager {
    private static final File SAVED_GAMES_FILE = new File("saved-games.ser");

    private MemoryManager() {

    }

    public static void writeListToFile(final List<HanoiTower> theWrittenList) {
        try (ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(SAVED_GAMES_FILE))) {
            fileWriter.writeObject(theWrittenList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static ArrayList<HanoiTower> readListFromFile() {
//        try (ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(SAVED_GAMES_FILE))) {
//            return (ArrayList<HanoiTower>) fileReader.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static ArrayList<HanoiTower> readListFromFile() {
        try (ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(SAVED_GAMES_FILE))) {
            Object object = fileReader.readObject();

            if (object instanceof ArrayList<?>) {
                ArrayList<?> list = (ArrayList<?>) object;
                if (!list.isEmpty() && !(list.get(0) instanceof HanoiTower)) {
                    throw new RuntimeException("The list does not contain HanoiTower objects.");
                }
                return (ArrayList<HanoiTower>) list;
            } else {
                throw new RuntimeException("The file does not contain an ArrayList.");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
