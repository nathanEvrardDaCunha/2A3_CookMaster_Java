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

    private void generateGenerate(int i) {
        String sql = "INSERT INTO Generate(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        GenerateModel generateModel = new GenerateModel(dbName, userName, password);
        generateModel.executeUpdate(sql);
    }

    private void generatePrint(int i) {
        String sql = "INSERT INTO Print(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        PrintModel printModel = new PrintModel(dbName, userName, password);
        printModel.executeUpdate(sql);
    }
}
