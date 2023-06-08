package fr.esgi.poo.cookmaster.main;

import java.time.LocalDate;
import java.util.Random;

public class DataRandomizer {

    /* EVENT */

    public String generateEventTitle(int eventTitleIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.eventTitlesArray[eventTitleIndex];
    }

    public String generateEventDescription(int eventDescriptionIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.eventDescriptionsArray[eventDescriptionIndex];
    }

    public int generateEventType(String eventTitle){
        if (eventTitle.contains("Atelier")) {
            return 1;
        } else if (eventTitle.contains("Cours")) {
            return 2;
        } else if (eventTitle.contains("Dégustation")) {
            return 3;
        } else {
            return 0;
        }
    }

    public int generateEventState(String eventStartingDate, String eventEndingDate) {
        LocalDate startDate = LocalDate.parse(eventStartingDate);
        LocalDate endDate = LocalDate.parse(eventEndingDate);
        LocalDate today = LocalDate.now();

        if (today.isBefore(startDate)) {
            return 0;
        } else if (today.isAfter(endDate)) {
            return 2;
        } else {
            return 1;
        }
    }


    /* SUBSCRIPTION */

    /*
    public int generateSubscriptionType(int subscriptionTypeIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.subscriptionTypesArray[subscriptionTypeIndex];
    }

    public int generateSubscriptionCost(int subscriptionCostIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.subscriptionCostsArray[subscriptionCostIndex];
    }

    public String generateSubscriptionTitle(int subscriptionTitleIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.subscriptionTitlesArray[subscriptionTitleIndex];
    }

    public String generateSubscriptionDescription(int subscriptionDescriptionIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.subscriptionDescriptionsArray[subscriptionDescriptionIndex];
    }


    /* PUBLICATION */

    public String generatePublicationTitle(int publicationTitleIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.publicationTitlesArray[publicationTitleIndex];
    }

    public String generatePublicationDescription(int publicationDescriptionIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.publicationDescriptionsArray[publicationDescriptionIndex];
    }





    /* PRODUCT */

    public String generateProductTitle(int productTitleIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.equipmentDescriptionArray[productTitleIndex];
    }

    public String generateProductDescription(int productDescriptionIndex, DataTemplateValues dataTemplateValues) {
        return dataTemplateValues.equipmentDescriptionArray[productDescriptionIndex];
    }

    public String generateRandomStartingDates(int startingYear, int endingYear) {
        long minDay = LocalDate.of(startingYear, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(endingYear, 12, 31).toEpochDay();

        Random random = new Random();
        long randomDay;
        if (minDay == maxDay) {
            randomDay = minDay;
        } else {
            randomDay = minDay + random.nextInt((int) (maxDay - minDay));
        }
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return randomDate.toString();
    }



    public String generateRandomEndingDates(String startingDate, int minDays, int maxDays) {
        LocalDate startDate = LocalDate.parse(startingDate);
        long startDay = startDate.toEpochDay();

        Random random = new Random();
        int additionalDays;
        if (minDays == maxDays) {
            additionalDays = minDays;
        } else {
            additionalDays = minDays + random.nextInt(maxDays - minDays);
        }

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

    public int generateRandomInt(int min, int max) {
        Random random = new Random();
        if (min == max) {
            return min;
        } else {
            return min + random.nextInt(max - min);
        }
    }
}
