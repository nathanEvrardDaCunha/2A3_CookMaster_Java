package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.*;

import java.sql.*;
import java.util.Random;

public class DataGenerator {

    private final String dbName;
    private final String userName;
    private final String password;



    public DataGenerator(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateDataForDB() {
        DataRandomizer dataRandomizer = new DataRandomizer();
        DataTemplateValues dataTemplateValues = new DataTemplateValues();

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
            for (int i = 1; i <= 200; i++) {
                dataGenerateSubscription.generateSubscriptions(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateEvent.generateEvents(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateProvider.generateProviders(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateEventLocation.generateEventLocations(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateUser.generateUsers(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateEquipment.generateEquipments(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateBill.generateBills(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= 200; i++) {
                dataGenerateProduct.generateProducts(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FAIRE UN UPDATE DES VALEURS DE CETTE CLASSE
        try {
            for (int i = 1; i <= 200; i++) {
                dataGeneratePublication.generatePublications(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        /*for (int i = 1; i <= 200; i++) {
            try {
                //IL VA FALLOIR FAIRE UNE BOUCLE FOR SEPARER POUR CHAQUE FONCTION
                generateProducts
                generateBills(i, dataRandomizer, dataTemplateValues);
                generatePublications(i, dataRandomizer, dataTemplateValues);

                1 - 1 : relation directe de 1 a 1
                n - 1 : relation partant de 1 vers n (donnée aléatoire)
                n - n : relation partant de n (donnée aléatoire) vers n (donnée aléatoire)

                //generateRegister(i);
                //generateOrganise(i);
                //generateRent(i);
                //generateBuy(i);
                //generateGenerate(i);
                //generatePrint(i);

                POUR L'UTILISATEUR FREQUENCE DE SON ABONNEMENT (ENDING DATE SUBSCRIPTION) :
                        //Récupérer la fréquence de l'abonnement est l'ajouté a la date => En réalité je vais faire un UPDATE a cette utilisateur
        //durant le moment ou je créé la table des relation entre les abonnements et les utilisateurs pour que l'utilisateur (id: 1) soit abonné a l'abonnement (id: 1)
        //et ensuite je vais faire un UPDATE sur l'utilisateur (id: 1) pour lui ajouter la date de fin d'abonnement


            } catch (SQLException e) {
                System.err.println("An error occurred while generating data: " + e.getMessage());
            }
        }*/
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
