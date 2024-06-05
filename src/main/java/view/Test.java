package view;

import model.HanoiTower;

import java.util.Arrays;

import static model.HanoiTower.Mode.DEFAULT_MODE;


public class Test {
    public static void main(String[] args) {
        final Runnable hieu = () -> {
            HanoiTower game1 = new HanoiTower("hieu", 9, 0.0, DEFAULT_MODE);

            System.out.println(game1);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());
            game1.popDisk(game1.getTowers()[0]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());
            game1.pushDisk(game1.getTowers()[2]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());

            game1.popDisk(game1.getTowers()[1]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());
            game1.popDisk(game1.getTowers()[2]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());
            game1.pushDisk(game1.getTowers()[1]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());

            game1.popDisk(game1.getTowers()[0]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());
            game1.pushDisk(game1.getTowers()[1]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());

            game1.pushDisk(game1.getTowers()[2]);
            System.out.println(Arrays.toString(game1.getTowers()) + " " + game1.getMoves() + " " + game1.getProgress());
        };
        hieu.run();

        System.out.println(ViewManager.WINDOW_HEIGHT + " " + ViewManager.WINDOW_WIDTH);
    }
}