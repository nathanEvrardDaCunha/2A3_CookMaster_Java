package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.*;

import java.sql.*;

public class DataGenerator {

    private static final int NUMBER_OF_SUBSCRIPTIONS = 200;
    private static final int NUMBER_OF_EVENTS = 200;
    private static final int NUMBER_OF_PROVIDERS = 200;
    private static final int NUMBER_OF_EVENT_LOCATIONS = 200;
    private static final int NUMBER_OF_USERS = 200;
    private static final int NUMBER_OF_EQUIPMENTS = 200;
    private static final int NUMBER_OF_BILLS = 200;
    private static final int NUMBER_OF_PRODUCTS = 200;
    private static final int NUMBER_OF_PUBLICATIONS = 200;

    private final String dbName;
    private final String userName;
    private final String password;
    public DataGenerator(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateDataForDB() {
        DataGenerateSubscription dataGenerateSubscription = new DataGenerateSubscription(dbName, userName, password);
        DataGenerateEvent dataGenerateEvent = new DataGenerateEvent(dbName, userName, password);
        DataGenerateProvider dataGenerateProvider = new DataGenerateProvider(dbName, userName, password);
        DataGenerateEventLocation dataGenerateEventLocation = new DataGenerateEventLocation(dbName, userName, password);
        DataGenerateUser dataGenerateUser = new DataGenerateUser(dbName, userName, password);
        DataGenerateEquipment dataGenerateEquipment = new DataGenerateEquipment(dbName, userName, password);
        DataGenerateBill dataGenerateBill = new DataGenerateBill(dbName, userName, password);
        DataGenerateProduct dataGenerateProduct = new DataGenerateProduct(dbName, userName, password);
        DataGeneratePublication dataGeneratePublication = new DataGeneratePublication(dbName, userName, password);

        try {
            for (int i = 1; i <= NUMBER_OF_SUBSCRIPTIONS; i++) {
                dataGenerateSubscription.generateSubscriptions(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_EVENTS; i++) {
                dataGenerateEvent.generateEvents(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_PROVIDERS; i++) {
                dataGenerateProvider.generateProviders(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_EVENT_LOCATIONS; i++) {
                dataGenerateEventLocation.generateEventLocations(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= NUMBER_OF_USERS; i++) {
                dataGenerateUser.generateUsers(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_EQUIPMENTS; i++) {
                dataGenerateEquipment.generateEquipments(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= NUMBER_OF_BILLS; i++) {
                dataGenerateBill.generateBills(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_PRODUCTS; i++) {
                dataGenerateProduct.generateProducts(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= NUMBER_OF_PUBLICATIONS; i++) {
                dataGeneratePublication.generatePublications(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
            Pour 1 - 1 => relation directe
            Pour 1 - n => relation ou 1 est aléatoirement mis a n données mais seulement de la table faible de 1
            Pour n - n => relation ou n est aléatoirement mis a n données pour n fois
         */

        /*

            Type 1 :
            + Pour 0.1 a 0.1 => faire qu'il a 1/2 chance d'avoir une relation
            Si il la alors faire une relation directe de nId a nId
            - Pas besoin de vérifier qu'une relation existe déjà entre ces deux données


            Type 2 :
            + Pour une relation 0.1 0.n => faire qu'il a 1/2 chance d'avoir une relation
            Si il la alors faire une relation directe de nId a randomId
            - Pas besoin de vérifier qu'une relation existe déjà entre ces deux données


            Type 3 :
            + Pour une relation de 0.n a 0.n => faire qu'il a 1/2 chance d'avoir une relation
            Si il la alors faire une relation directe de randomId a randomId
            - Besoin de vérifier qu'une relation existe déjà entre ces deux données


            Type 4 :
            + Pour une relation de 1,1 a 0.n => faire que chaque donné de 1.1 soit rattaché a une donné
            Vu qu'il a une relation, faire relation directe ou nId a randomId
            - Pas besoin de vérifier qu'une relation existe déjà entre ces deux données


            Type 5 :
            + Pour une relation de 1.n a 0.n => faire que chaque donné de 1.n soit rattaché a une donné
            (En premier) Vu qu'il a une relation, faire relation directe ou nId a randomId
            (En second) Vu qu'il a une relation, faire relation directe ou randomId a randomId
            - Besoin de vérifier qu'une relation existe déjà entre ces deux données

         */

        /*

            Subscribe : Type 2 => Le faire directement dans la classe de génération de donnée (Users)
            - Register : Type 3 (Alter)
            - Buy :  Type 3 (Alter)
            - Print : Type 3 (Alter)
            - Organise : Type 5 (Alter)
            - Rent : Type 5 (Alter)
            Own : Type 2 => Le faire directement dans la classe de génération de donnée (Equipments)
            Linked : Type 2 => Le faire directement dans la classe de génération de donnée (Bills)
            Generate : Type 2 => Le faire directement dans la classe de génération de donnée (Bills)


         */

    }





























    /**
     *
     * TEST MANUEL DE L'AJOUT DE CES FONCTION AVEC GITCOPILOT
     * @param i
     *
     */


    private void generateRegister(int i) {
        String sql = "INSERT INTO Register(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        RegisterModel registerModel = new RegisterModel(dbName, userName, password);
        registerModel.executeUpdate(sql);
    }

    private void generateOrganise(int i) {
        String sql = "INSERT INTO Organise(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        OrganiseModel organiseModel = new OrganiseModel(dbName, userName, password);
        organiseModel.executeUpdate(sql);
    }

    private void generateRent(int i) {
        String sql = "INSERT INTO Rent(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        RentModel rentModel = new RentModel(dbName, userName, password);
        rentModel.executeUpdate(sql);
    }

    private void generateBuy(int i) {
        String sql = "INSERT INTO Buy(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        BuyModel buyModel = new BuyModel(dbName, userName, password);
        buyModel.executeUpdate(sql);
    }

    private void generatePrint(int i) {
        String sql = "INSERT INTO Print(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        PrintModel printModel = new PrintModel(dbName, userName, password);
        printModel.executeUpdate(sql);
    }
}
