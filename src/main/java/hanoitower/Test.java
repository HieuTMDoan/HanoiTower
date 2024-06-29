package hanoitower;

import hanoitower.model.HanoiTower;
import hanoitower.model.Tower;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        HanoiTower instance = HanoiTower.getInstance();
        instance.startGame();
        Tower[] towers = instance.getTowers();
        System.out.println(Arrays.toString(towers) + "\n");

        instance.popDisk(towers[0]);
        System.out.println(Arrays.toString(towers));

        System.out.println(instance.pushDisk(towers[0]) + Arrays.toString(towers));

        instance.popDisk(towers[0]);
        System.out.println(Arrays.toString(towers));

        System.out.println(instance.pushDisk(towers[1]) + Arrays.toString(towers));

        instance.popDisk(towers[0]);
        System.out.println(Arrays.toString(towers));

        System.out.println(instance.pushDisk(towers[1]) + Arrays.toString(towers));

        System.out.println(instance.pushDisk(towers[2]) + Arrays.toString(towers));
    }
}
