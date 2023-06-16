package fr.esgi.cookmaster_java_2a3.data;

import java.sql.*;

public class DataGenerator {

    public static final int NUMBER_OF_SUBSCRIPTIONS = 30;
    public static final int NUMBER_OF_EVENTS = 30;
    public static final int NUMBER_OF_PROVIDERS = 30;
    public static final int NUMBER_OF_EVENT_LOCATIONS = 30;
    public static final int NUMBER_OF_USERS = 30;
    private static final int NUMBER_OF_EQUIPMENTS = 30;
    public static final int NUMBER_OF_BILLS = 30;
    public static final int NUMBER_OF_PRODUCTS = 30;
    private static final int NUMBER_OF_PUBLICATIONS = 30;
    private static final int NUMBER_OF_BUYS = 30;
    private static final int NUMBER_OF_ORGANISES = 30;
    private static final int NUMBER_OF_PRINTS = 30;
    private static final int NUMBER_OF_REGISTERS = 30;
    private static final int NUMBER_OF_RENTS = 30;

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

        DataGenerateBuy dataGenerateBuy = new DataGenerateBuy(dbName, userName, password);
        DataGenerateOrganise dataGenerateOrganise = new DataGenerateOrganise(dbName, userName, password);
        DataGeneratePrint dataGeneratePrint = new DataGeneratePrint(dbName, userName, password);
        DataGenerateRegister dataGenerateRegister = new DataGenerateRegister(dbName, userName, password);
        DataGenerateRent dataGenerateRent = new DataGenerateRent(dbName, userName, password);

        try {
            for (int i = 1; i <= NUMBER_OF_SUBSCRIPTIONS; i++) {
                dataGenerateSubscription.generateSubscriptions();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_EVENTS; i++) {
                dataGenerateEvent.generateEvents();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_PROVIDERS; i++) {
                dataGenerateProvider.generateProviders();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_EVENT_LOCATIONS; i++) {
                dataGenerateEventLocation.generateEventLocations();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_USERS; i++) {
                dataGenerateUser.generateUsers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_EQUIPMENTS; i++) {
                dataGenerateEquipment.generateEquipments();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= NUMBER_OF_BILLS; i++) {
                dataGenerateBill.generateBills();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_PRODUCTS; i++) {
                dataGenerateProduct.generateProducts();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_PUBLICATIONS; i++) {
                dataGeneratePublication.generatePublications();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /**
         *             Pour 1 - 1 => relation directe
         *             Pour 1 - n => relation ou 1 est aléatoirement mis a n données mais seulement de la table faible de 1
         *             Pour n - n => relation ou n est aléatoirement mis a n données pour n fois
         *
         *
         *             Type 1 :
         *             + Pour 0.1 a 0.1 => faire qu'il a 1/2 chance d'avoir une relation
         *             Si il la alors faire une relation directe de nId a nId
         *             - Pas besoin de vérifier qu'une relation existe déjà entre ces deux données
         *
         *
         *             Type 2 :
         *             + Pour une relation 0.1 0.n => faire qu'il a 1/2 chance d'avoir une relation
         *             Si il la alors faire une relation directe de nId a randomId
         *             - Pas besoin de vérifier qu'une relation existe déjà entre ces deux données
         *
         *
         *             Type 3 :
         *             + Pour une relation de 0.n a 0.n => faire qu'il a 1/2 chance d'avoir une relation
         *             Si il la alors faire une relation directe de randomId a randomId
         *             - Besoin de vérifier qu'une relation existe déjà entre ces deux données
         *
         *
         *             Type 4 :
         *             + Pour une relation de 1,1 a 0.n => faire que chaque donné de 1.1 soit rattaché a une donné
         *             Vu qu'il a une relation, faire relation directe ou nId a randomId
         *             - Pas besoin de vérifier qu'une relation existe déjà entre ces deux données
         *
         *
         *             Type 5 :
         *             + Pour une relation de 1.n a 0.n => faire que chaque donné de 1.n soit rattaché a une donné
         *             (En premier) Vu qu'il a une relation, faire relation directe ou nId a randomId
         *             (En second) Vu qu'il a une relation, faire relation directe ou randomId a randomId
         *             - Besoin de vérifier qu'une relation existe déjà entre ces deux données
         */

        /*

            - Organise : Type 5 (Alter)

        }*/

        try {
            for (int i = 1; i <= NUMBER_OF_REGISTERS ; i++) {
                dataGenerateRegister.generateRegister();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_RENTS ; i++) {
                dataGenerateRent.generateNormalRent(i);
            }
            for (int i = 1; i <= NUMBER_OF_RENTS ; i++) {
                dataGenerateRent.generateRandomRents();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_BUYS ; i++) {
                dataGenerateBuy.generateBuy();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_ORGANISES ; i++) {
                dataGenerateOrganise.generateNormalOrganise(i);
            }
            for (int i = 1; i <= NUMBER_OF_ORGANISES ; i++) {
                dataGenerateOrganise.generateRandomOrganises();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_PRINTS ; i++) {
                dataGeneratePrint.generatePrint();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= NUMBER_OF_USERS ; i++) {
                dataGenerateUser.updateSubscriptionEndingDate(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

