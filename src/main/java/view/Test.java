package view;

import model.HanoiTower;
import model.Tower;

import java.util.Arrays;

import static model.HanoiTower.Mode.DEFAULT_MODE;
import static model.HanoiTower.Mode.TIMED_MODE;


public class Test {
    public static void main(String[] args) {
        final Runnable hieu = () -> {
            HanoiTower game1 = new HanoiTower("hieu", 9, 0.0, DEFAULT_MODE);
            HanoiTower game2 = new HanoiTower("doan", 9, 0.0, DEFAULT_MODE);
            HanoiTower game3 = new HanoiTower("minh", 9, 0.0, TIMED_MODE);
            HanoiTower game4 = new HanoiTower("tran", 9, 0.0, DEFAULT_MODE);
            HanoiTower game5 = new HanoiTower("big", 9, 0.0, TIMED_MODE);
            HanoiTower game6 = new HanoiTower("city", 9, 0.0, DEFAULT_MODE);
            HanoiTower game7 = new HanoiTower("boi", 1, 0.0, TIMED_MODE);
            HanoiTower.saveGame(game1);
            HanoiTower.saveGame(game2);
            HanoiTower.saveGame(game3);
            HanoiTower.saveGame(game4);
            HanoiTower.saveGame(game5);
            HanoiTower.saveGame(game6);
            HanoiTower.saveGame(game7);
            System.out.println(HanoiTower.getSavedGames());
        };
        hieu.run();
    }
}