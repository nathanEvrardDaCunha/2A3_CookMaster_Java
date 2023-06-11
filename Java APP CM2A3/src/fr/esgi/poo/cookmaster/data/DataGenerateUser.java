package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.UsersModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.*;
import java.util.Base64;

public class DataGenerateUser {

    private static final String USER_BIRTHDAY_DATE_MIN = "1980-01-01";
    private static final String USER_BIRTHDAY_DATE_MAX = "2002-12-31";
    private static final int USER_MIN_SEX = 0;
    private static final int USER_MAX_SEX = 2;
    private static final int USER_MIN_ROLE = 0;
    private static final int USER_MAX_ROLE = 2;
    private static final String USER_REGISTRATION_DATE_MAX = "2021-12-31";
    private static final String USER_STARTING_SUBSCRIPTION_DATE_MAX = "2022-12-31";
    private static final String USER_LAST_PURCHASE_DATE_MAX = "2023-12-31";
    private static final String USER_ENDING_SUBSCRIPTION_DATE_MAX = "2024-12-31";
    private static final int USER_MIN_FIDELITY_POINT = 0;
    private static final int USER_MAX_FIDELITY_POINT = 1000;
    private static final int USER_MIN_NICKNAME_NUMBER = 0;
    private static final int USER_MAX_NICKNAME_NUMBER = 1000;

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
            userBirthday = CommonDataGenerator.selectRandomDate(USER_BIRTHDAY_DATE_MIN, USER_BIRTHDAY_DATE_MAX);
            userFirstname = CommonDataGenerator.selectRandomFirstname();
            userLastname = CommonDataGenerator.selectRandomLastname();

            userPostalCode = CommonDataGenerator.selectRandomPostalCode();
            userAddress = CommonDataGenerator.selectRandomAdress();
            userCity = CommonDataGenerator.selectRandomCity();

            userEmail = selectUserEmail(userFirstname, userLastname);
            userPassword = selectUserPassword();
            userName = selectUserName(userFirstname, userLastname);
        }while (usersModel.userExistsWithNameAddress(userLastname, userFirstname, userCity, userAddress, userPostalCode) ||
                usersModel.userExistsWithNameBirthdate(userLastname, userFirstname, userBirthday) ||
                usersModel.userExistsWithEmail(userEmail) ||
                usersModel.userExistsWithUsername(userName));

        int userSex = CommonDataGenerator.selectRandomInt(USER_MIN_SEX, USER_MAX_SEX);
        int userRole = CommonDataGenerator.selectRandomInt(USER_MIN_ROLE, USER_MAX_ROLE);
        int userFidelityPoint = CommonDataGenerator.selectRandomInt(USER_MIN_FIDELITY_POINT, USER_MAX_FIDELITY_POINT);

        String userRegistrationDate = CommonDataGenerator.selectRandomDate(userBirthday, USER_REGISTRATION_DATE_MAX);

        String userStartingSubscriptionDate = CommonDataGenerator.selectRandomDate(userRegistrationDate, USER_STARTING_SUBSCRIPTION_DATE_MAX);
        String userLastPurchaseDate = CommonDataGenerator.selectRandomDate(userRegistrationDate, USER_LAST_PURCHASE_DATE_MAX);

        //Récupérer la fréquence de l'abonnement est l'ajouté a la date => En réalité je vais faire un UPDATE a cette utilisateur
        //durant le moment ou je créé la table des relation entre les abonnements et les utilisateurs pour que l'utilisateur (id: 1) soit abonné a l'abonnement (id: 1)
        //et ensuite je vais faire un UPDATE sur l'utilisateur (id: 1) pour lui ajouter la date de fin d'abonnement
        String userEndingSubscriptionDate = CommonDataGenerator.selectRandomDate(userStartingSubscriptionDate, USER_ENDING_SUBSCRIPTION_DATE_MAX);

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

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String selectUserEmail(String fisrtname, String lastname){
        String[] userMailArray = {
                "gmail.com", "yahoo.fr", "hotmail.fr", "outlook.fr", "orange.fr", "sfr.fr", "free.fr"
        };

        int randomIndex = CommonDataGenerator.selectRandomInt(0, 6);
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
        return firstname.substring(1) + lastname + CommonDataGenerator.selectRandomInt(USER_MIN_NICKNAME_NUMBER, USER_MAX_NICKNAME_NUMBER);
    }
}
