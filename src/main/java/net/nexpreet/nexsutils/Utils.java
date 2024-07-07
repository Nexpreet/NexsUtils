package net.nexpreet.nexsutils;

import java.util.Random;

public class Utils {

    public static double randomNumber(double min, double max) {
        Random randomX = new Random();
        double number = min + (max - min) * randomX.nextDouble();
        return number;
    }

    public static float randomFloat(float min, float max) {
        return (float) (Math.random() * (max - min + 1)) + min;
    }


}
