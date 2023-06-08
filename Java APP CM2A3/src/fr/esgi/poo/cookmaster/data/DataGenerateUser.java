package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.UsersModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.security.*;
import java.util.Base64;


public class DataGenerateUser {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateUser(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    void generateUsers(int i) throws SQLException {

        UsersModel usersModel = new UsersModel(dbName, userName, password);

        String userBirthday;
        String userFirstname;
        String userLastname;

        String userPostalCode;
        String userAddress;
        String userCity;

        String userEmail;
        String userPassword;
        String userName;

        do {
            userBirthday = selectUserBirthDay(1980, 2000);
            userFirstname = selectUserFirstname();
            userLastname = selectUserLastname();

            userPostalCode = selectUserPostalCode();
            userAddress = selectUserAddress();
            userCity = selectUserCity();

            userEmail = selectUserEmail(userFirstname, userLastname);
            userPassword = selectUserPassword();
            userName = selectUserName(userFirstname, userLastname);
        }while (usersModel.userExistsWithNameAddress(userLastname, userFirstname, userCity, userAddress, userPostalCode) ||
                usersModel.userExistsWithNameBirthdate(userLastname, userFirstname, userBirthday) ||
                usersModel.userExistsWithEmail(userEmail) ||
                usersModel.userExistsWithUsername(userName));

        int userSex = selectUserSex();
        int userRole = selectUserRole();
        int userFidelityPoint = selectUserFidelityPoint();

        String userRegistrationDate = selectUserRegistrationDate(userBirthday);

        String userStartingSubscriptionDate = selectUserStartingSubscriptionDate(userRegistrationDate);
        String userLastPurchaseDate = selectUserLastPurchaseDate(Integer.parseInt(userStartingSubscriptionDate.substring(0, 4)), Integer.parseInt(userStartingSubscriptionDate.substring(0, 4)) + 2);

        //Récupérer la fréquence de l'abonnement est l'ajouté a la date => En réalité je vais faire un UPDATE a cette utilisateur
        //durant le moment ou je créé la table des relation entre les abonnements et les utilisateurs pour que l'utilisateur (id: 1) soit abonné a l'abonnement (id: 1)
        //et ensuite je vais faire un UPDATE sur l'utilisateur (id: 1) pour lui ajouter la date de fin d'abonnement
        String userEndingSubscriptionDate = selectUserEndingSubscriptionDate(userStartingSubscriptionDate);

        String sql = "INSERT INTO USERS(Username, Address, City, Firstname, Lastname, Postal_code, Role, Registration_date, Fidelity_point, Last_purchase_date, Ending_subscription_date, Starting_subscription_date, Sex, Birthday, Email, Password, Id_1) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = usersModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, userName);
            pstmt.setString(2, userAddress);
            pstmt.setString(3, userCity);
            pstmt.setString(4, userFirstname);
            pstmt.setString(5, userLastname);
            pstmt.setString(6, userPostalCode);
            pstmt.setInt(7, userRole);
            pstmt.setString(8, userRegistrationDate);
            pstmt.setInt(9, userFidelityPoint);
            pstmt.setString(10, userLastPurchaseDate);
            pstmt.setString(11, userEndingSubscriptionDate);
            pstmt.setString(12, userStartingSubscriptionDate);
            pstmt.setInt(13, userSex);
            pstmt.setString(14, userBirthday);
            pstmt.setString(15, userEmail);
            pstmt.setString(16, userPassword);
            pstmt.setInt(17, i);

            /*pstmt.setString(1, userAddress);
            pstmt.setString(2, userCity);
            pstmt.setString(3, userFirstname);
            pstmt.setString(4, userLastname);
            pstmt.setString(5, userPostalCode);
            pstmt.setInt(6, userRole);
            pstmt.setString(7, userRegistrationDate);
            pstmt.setInt(8, userFidelityPoint);
            pstmt.setString(9, userLastPurchaseDate);
            pstmt.setString(10, userEndingSubscriptionDate);
            pstmt.setString(11, userStartingSubscriptionDate);
            pstmt.setInt(12, userSex);
            pstmt.setString(13, userBirthday);
            pstmt.setString(14, userEmail);
            pstmt.setString(15, userPassword);
            pstmt.setInt(16, i);*/

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int selectUserSex(){
        int randomInt = (int) (Math.random() * 2);
        return randomInt;
    }

    private int selectUserRole(){
        int randomInt = (int) (Math.random() * 2);
        return randomInt;
    }

    private int selectUserFidelityPoint(){
        int randomInt = (int) (Math.random() * 1000);
        return randomInt;
    }

    private String selectUserBirthDay(int startingYear, int endingYear) {
        Random random = new Random();
        int year = random.nextInt(endingYear - startingYear + 1) + startingYear;
        int dayOfYear = random.nextInt(365) + 1;

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return String.format("%1$tF", calendar);
    }

    private String selectUserRegistrationDate(String userBirthday) {
        String[] userBirthdayArray = userBirthday.split("-");

        int birthYear = Integer.parseInt(userBirthdayArray[0]);

        int registrationYear = birthYear + new Random().nextInt(2023 - birthYear + 1);
        int dayOfYear = new Random().nextInt(365) + 1;

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, registrationYear);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return String.format("%1$tF", calendar);
    }

    private String selectUserStartingSubscriptionDate(String userRegistrationDate) {
        String[] userRegistrationDateArray = userRegistrationDate.split("-");

        int year = Integer.parseInt(userRegistrationDateArray[0]);

        Random random = new Random();
        int yearOffset = Math.max(0, random.nextInt(2024 - year + 1)); // Assurez-vous que l'offset ne soit pas négatif.
        int dayOfYear = random.nextInt(365) + 1;

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year + yearOffset);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return String.format("%1$tF", calendar);
    }

    private String selectUserLastPurchaseDate(int minYear, int maxYear) {
        Random random = new Random();
        int year = random.nextInt(maxYear - minYear + 1) + minYear;
        int dayOfYear = random.nextInt(365) + 1;

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return String.format("%1$tF", calendar);
    }

    private String selectUserEndingSubscriptionDate(String userStartingSubscriptionDate) {
        String[] userStartingSubscriptionDateArray = userStartingSubscriptionDate.split("-");

        int year = Integer.parseInt(userStartingSubscriptionDateArray[0]);

        Random random = new Random();
        int yearOffset = Math.max(0, random.nextInt((year + 3) - year + 1));
        int dayOfYear = random.nextInt(365) + 1;

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year + yearOffset);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return String.format("%1$tF", calendar);
    }


    private int selectRandomIndex(int maxIndex) {
        Random random = new Random();
        return random.nextInt(maxIndex);
    }

    private String selectUserAddress(){
        String[] userAdressArray = {
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

        int randomIndex = selectRandomIndex(15);
        return userAdressArray[randomIndex] + " " + selectRandomIndex(100);
    }

    private String selectUserCity() {

        /*
            Faire en sorte que la ville soit bien dans le bon code postal
         */

        String[] userCityArray = {
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

        int randomIndex = selectRandomIndex(15);
        return userCityArray[randomIndex];
    }

    private String selectUserPostalCode(){
        return String.valueOf(selectRandomIndex(9999)) + 1;
    }

    private String selectUserLastname(){
        String[] userLastnameArray = {
                "RAMSAY", "CHILD", "BOCUSE", "LAWSON", "OLIVER", "STEWART", "RAY", "BATALI",
                "HERMÉ", "KELLER", "DUCASSE", "ROBUCHON", "FIERI", "BOULUD", "PUCK", "BLUMENTHAL",
                "REDZEPI", "ADRIÀ", "BOTTURA", "BOURDAIN", "CRENN", "PÉPIN", "AUGUSTE", "SAFFITZ",
                "OTTOLENGHI", "PIERREWHITE", "FLAY", "DE LAURENTIIS", "GARTEN", "SAMUELSSON"
        };

        int randomIndex = selectRandomIndex(15);
        return userLastnameArray[randomIndex];
    }

    private String selectUserFirstname(){
        String[] userFirstnameArray = {
                "Gordon", "Julia", "Paul", "Nigella", "Jamie", "Martha", "Rachel", "Mario", "Pierre",
                "Thomas", "Alain", "Joël", "Guy", "Daniel", "Wolfgang", "Heston", "René",
                "Ferran", "Massimo", "Anthony", "Dominique", "Jacques", "Georges",
                "Claire", "Yotam", "Marco", "Bobby", "Giada", "Ina", "Marcus"
        };

        int randomIndex = selectRandomIndex(15);
        return userFirstnameArray[randomIndex];
    }

    private String selectUserEmail(String fisrtname, String lastname){
        String[] userMailArray = {
                "gmail.com", "yahoo.fr", "hotmail.fr", "outlook.fr", "orange.fr", "sfr.fr", "free.fr"
        };

        int randomIndex = selectRandomIndex(6);
        return fisrtname + "." + lastname + "@" + userMailArray[randomIndex];
    }

    private String selectUserPassword(){

        /* Revoir cette méthode trouvé sur StackOverflow */

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24]; // Choisissez la longueur du mot de passe. Ici, 24 bytes donnera un mot de passe de 32 caractères.
        random.nextBytes(bytes);
        String generatedPassword = Base64.getEncoder().encodeToString(bytes);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(generatedPassword.getBytes());

            StringBuilder hexHash = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexHash.append('0');
                hexHash.append(hex);
            }
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String selectUserName(String firstname, String lastname){
        return firstname.substring(1) + lastname + selectRandomIndex(999);
    }
}
