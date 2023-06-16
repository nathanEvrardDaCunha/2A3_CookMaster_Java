package fr.esgi.cookmaster_java_2a3.tools;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonDataGenerator {

    private final static int POSTAL_CODE_MIN = 10000;
    private final static int POSTAL_CODE_MAX = 100000;
    private final static int ADDRESS_NUMBER_MIN = 0;
    private final static int ADDRESS_NUMBER_MAX = 150;
    private final static int CITY_MIN = 0;
    private final static int FIRSTNAME_MIN = 0;
    private final static int LASTNAME_MIN = 0;

    public static int selectRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static String selectRandomPostalCode(){
        return String.format("%05d", CommonDataGenerator.selectRandomInt(POSTAL_CODE_MIN, POSTAL_CODE_MAX));
    }

    public static String selectRandomAdress(){
        String[] eventLocationArray = {
                "Rue des Orfèvres", "Avenue de la République", "Boulevard de la Liberté",
                "Rue des Dames", "Avenue du Général Leclerc", "Boulevard Saint-Germain",
                "Rue de la Paix", "Avenue de Wagram", "Boulevard Haussmann",
                "Rue du Bac", "Avenue des Champs-Élysées", "Boulevard Montmartre",
                "Rue de Rivoli", "Avenue de l'Opéra", "Boulevard Saint-Michel",
                "Rue du Faubourg Saint-Antoine", "Avenue Foch", "Boulevard de Magenta",
                "Rue de Vaugirard", "Avenue George V", "Boulevard Malesherbes",
                "Rue de Passy", "Avenue Montaigne", "Boulevard de la Chapelle",
                "Rue La Fayette", "Avenue de la Bourdonnais", "Boulevard de Sébastopol",
                "Rue du Cherche-Midi", "Avenue de Clichy", "Boulevard de la Villette",
                "Rue Saint-Antoine", "Avenue de Friedland", "Boulevard de la Madeleine"
        };

        int randomIndex = CommonDataGenerator.selectRandomInt(0, CommonSettings.ALL_ARRAY_SIZE);
        return eventLocationArray[randomIndex] + " " + CommonDataGenerator.selectRandomInt(ADDRESS_NUMBER_MIN, ADDRESS_NUMBER_MAX);
    }

    public static String selectRandomCity() {

        /*
            Faire en sorte que la ville soit bien dans le bon code postal
         */

        String[] eventCityArray = {
                "Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Montpellier",
                "Strasbourg", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre", "Saint-Étienne",
                "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne", "Le Mans",
                "Aix-en-Provence", "Clermont-Ferrand", "Brest", "Limoges", "Tours", "Amiens",
                "Perpignan", "Metz", "Besançon", "Boulogne-Billancourt", "Orléans", "Mulhouse",
                "Rouen", "Caen", "Nancy", "Saint-Denis", "Saint-Denis", "Argenteuil", "Montreuil",
                "Roubaix", "Dunkerque", "Tourcoing", "Nanterre", "Avignon", "Créteil", "Poitiers",
                "Versailles", "Courbevoie", "Vitry-sur-Seine", "Colombes", "Pau", "Aulnay-sous-Bois",
                "Asnières-sur-Seine", "Rueil-Malmaison", "Saint-Pierre", "Antibes", "Saint-Maur-des-Fossés",
                "Champigny-sur-Marne", "La Rochelle", "Aubervilliers", "Calais", "Cannes", "Le Tampon",
                "Béziers", "Colmar", "Bourges", "Drancy", "Mérignac", "Saint-Nazaire", "Valence",
                "Ajaccio", "Issy-les-Moulineaux", "Noisy-le-Grand", "Villeneuve-d'Ascq", "Quimper",
                "Antony", "Troyes", "Neuilly-sur-Seine", "La Seyne-sur-Mer", "Les Abymes", "Lorient",
                "Sarcelles", "Pessac", "Ivry-sur-Seine", "Cergy", "Clichy", "Niort", "Chambéry", "Montauban",
                "Vénissieux", "Beauvais", "Hyères", "Charleville-Mézières", "Cholet", "Chelles", "Meaux",
                "Épinay-sur-Seine", "Saint-André", "La Roche-sur-Yon", "Bondy", "Levallois-Perret", "Issy-les-Moulineaux",
                "Évry-Courcouronnes", "Arles", "Valenciennes", "Cagnes-sur-Mer", "Bobigny", "Corbeil-Essonnes",
                "Saint-Quentin", "Pantin", "Maisons-Alfort", "Chalon-sur-Saône", "Meudon", "Fontenay-sous-Bois",
                "Châteauroux", "Saint-Joseph", "Narbonne", "Saint-Louis", "Saint-Paul", "Albi", "Villejuif",
        };

        int randomIndex = CommonDataGenerator.selectRandomInt(CITY_MIN, CommonSettings.ALL_ARRAY_SIZE);
        return eventCityArray[randomIndex];
    }

    public static String selectRandomLastname(){
        String[] userLastnameArray = {
                "RAMSAY", "CHILD", "BOCUSE", "LAWSON", "OLIVER", "STEWART", "RAY", "BATALI",
                "HERMÉ", "KELLER", "DUCASSE", "ROBUCHON", "FIERI", "BOULUD", "PUCK", "BLUMENTHAL",
                "REDZEPI", "ADRIÀ", "BOTTURA", "BOURDAIN", "CRENN", "PÉPIN", "AUGUSTE", "SAFFITZ",
                "OTTOLENGHI", "PIERREWHITE", "FLAY", "DE LAURENTIIS", "GARTEN", "SAMUELSSON"
        };

        int randomIndex = CommonDataGenerator.selectRandomInt(LASTNAME_MIN, CommonSettings.ALL_ARRAY_SIZE);
        return userLastnameArray[randomIndex];
    }

    public static String selectRandomFirstname(){
        String[] userFirstnameArray = {
                "Gordon", "Julia", "Paul", "Nigella", "Jamie", "Martha", "Rachel", "Mario", "Pierre",
                "Thomas", "Alain", "Joël", "Guy", "Daniel", "Wolfgang", "Heston", "René",
                "Ferran", "Massimo", "Anthony", "Dominique", "Jacques", "Georges",
                "Claire", "Yotam", "Marco", "Bobby", "Giada", "Ina", "Marcus"
        };

        int randomIndex = CommonDataGenerator.selectRandomInt(FIRSTNAME_MIN, CommonSettings.ALL_ARRAY_SIZE);
        return userFirstnameArray[randomIndex];
    }

    public static String selectRandomDate(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + 1);

        LocalDate randomDate = startLocalDate.plusDays(randomDay);

        return randomDate.format(formatter);
    }



}
