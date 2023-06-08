package fr.esgi.poo.cookmaster.tools;

import java.util.Random;

public class CommonCode {

    public static int selectRandomIndex(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
