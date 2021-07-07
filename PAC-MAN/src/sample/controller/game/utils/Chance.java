package sample.controller.game.utils;

import java.util.Random;

abstract public class Chance {
    private static Random random = new Random();

    public static int dice() {
        int num = 0;
        num = random.nextInt(6);
        num++;
        return num;
    }

    public static int coin() {
        int num = 0;
        num = random.nextInt(2);
        num++;
        return num;
    }
}