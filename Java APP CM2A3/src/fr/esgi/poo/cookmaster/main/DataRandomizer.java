package fr.esgi.poo.cookmaster.main;

import java.time.LocalDate;
import java.util.Random;

public class DataRandomizer {



    public String generateRandomStartingDates(int startingYear, int endingYear) {
        long maxDay = LocalDate.of(startingYear, 1, 1).toEpochDay();
        long minDay = LocalDate.of(endingYear, 12, 31).toEpochDay();

        Random random = new Random();
        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return randomDate.toString();
    }

    public String generateRandomEndingDates(String startingDate, int minDays, int maxDays) {
        LocalDate startDate = LocalDate.parse(startingDate);
        long startDay = startDate.toEpochDay();

        Random random = new Random();
        int additionalDays = random.nextInt(maxDays - minDays) + minDays;
        long endDay = startDay + additionalDays;
        LocalDate endDate = LocalDate.ofEpochDay(endDay);

        return endDate.toString();
    }

    public int generateRandomPickInArray(int[] array) {
        int index = new Random().nextInt(array.length);
        return array[index];
    }

    public String generateRandomPickInArray(String[] array) {
        int index = new Random().nextInt(array.length);
        return array[index];
    }

    public String generateRandomPostalCodes() {
        Random random = new Random();
        int postalCode = 10000 + random.nextInt(90000);
        return String.valueOf(postalCode);
    }

    public String generateRandomAddresses(String[] array) {
        int maxStreetNumber = 100;
        Random random = new Random();
        String randomStreetName = array[random.nextInt(array.length)];
        int randomStreetNumber = random.nextInt(maxStreetNumber) + 1;

        return randomStreetNumber + " " + randomStreetName;
    }
}
