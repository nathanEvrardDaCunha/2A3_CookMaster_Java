package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.data.DataGenerator;
import fr.esgi.poo.cookmaster.data.DataPurger;
import fr.esgi.poo.cookmaster.model.*;

import java.sql.*;

import static fr.esgi.poo.cookmaster.tools.CommonSettings.*;

public class Launcher {

    public static void main(String[] args) {
        DataPurger dataPurger = new DataPurger(DB_NAME, USER_NAME, PASSWORD);
        dataPurger.purgeData();

        DataGenerator dataGenerator = new DataGenerator(DB_NAME, USER_NAME, PASSWORD);
        dataGenerator.generateDataForDB();


        /**
         *
         * Passer dans chaque fichier pour :
         *
         * - Rajouter des commentaires si besoin
         *
         * - Rajouter des exceptions si besoin
         *
         *
         *
         * - Séparer les identifiants de la BDD dans un fichier a ne pas commit
         *
         * - Utiliser les Calendar pour les dates dans tous les fichiers
         *
         * - S'assurer que chaque XArray est au minimum 50 éléments dedans
         *
         * - S'assurer que la limite dans le choix Random est bien de la taille des array
         *
         *
         * - Remplacer les valeurs brutes par des variables ou des constantes
         * - Faire en sorte que PostalCode, RandomInt, Adress
         */




        /*SubscriptionsModel model = new SubscriptionsModel(DB_NAME, USER_NAME, PASSWORD);

        try {
            ResultSet resultSet = model.executeQuery("SELECT * FROM subscriptions");
            while (resultSet.next()) {
                model.loadSubscriptionById(resultSet.getInt("Id"));
                System.out.println("ID: " + model.getSubscriptionId() + ", Title: " + model.getSubscriptionTitle() +
                        ", Cost: " + model.getSubscriptionCost() + ", Type: " + model.getSubscriptionType());
            }
            model.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*EventsModel eventsModel = new EventsModel(DB_NAME, USER_NAME, PASSWORD);

        try {
            ResultSet resultSet = eventsModel.executeQuery("SELECT * FROM events");
            while (resultSet.next()) {
                eventsModel.loadEventById(resultSet.getInt("Id"));
                System.out.println("ID: " + eventsModel.getEventId() + ", Title: " + eventsModel.getEventTitle() +
                        ", Type: " + eventsModel.getEventType() +
                        ", State: " + eventsModel.getEventState() + ", Starting date: " + eventsModel.getEventStartingDate() +
                        ", Ending date: " + eventsModel.getEventEndingDate());
            }
            eventsModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*
        ProvidersModel providersModel = new ProvidersModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = providersModel.executeQuery("SELECT * FROM providers");
            while (resultSet.next()) {
                providersModel.loadProviderById(resultSet.getInt("Id"));
                System.out.println("ID: " + providersModel.getProviderId() + ", Firstname: " + providersModel.getProviderFirstname() + ", Lastname: " + providersModel.getProviderLastname() +
                        ", CompagnyName: " + providersModel.getProviderCompanyName());
            }
            providersModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        /*EventsLocationModel eventsLocationModel = new EventsLocationModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = eventsLocationModel.executeQuery("SELECT * FROM event_locations");
            while (resultSet.next()) {
                eventsLocationModel.loadEventLocationById(resultSet.getInt("Id"));
                System.out.println("ID: " + eventsLocationModel.getEventLocationId() +
                        ", Address: " + eventsLocationModel.getEventLocationAddress() + ", City: " + eventsLocationModel.getEventLocationCity() +
                        ", PostalCode: " + eventsLocationModel.getEventLocationPostalCode());
            }
            eventsLocationModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*UsersModel usersModel = new UsersModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = usersModel.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                usersModel.loadUserById(resultSet.getInt("Id"));
                System.out.println("ID: " + usersModel.getUserId() +
                        ", Firstname: " + usersModel.getUserFirstname() + ", Lastname: " + usersModel.getUserLastname() +
                        ", Email: " + usersModel.getUserEmail() + ", Password: " + usersModel.getUserPassword() +
                        ", Role: " + usersModel.getUserRole());
            }
            usersModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*EquipmentsModel equipmentsModel = new EquipmentsModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = equipmentsModel.executeQuery("SELECT * FROM equipments");
            while (resultSet.next()) {
                equipmentsModel.loadEquipmentById(resultSet.getInt("Id"));
                System.out.println("ID: " + equipmentsModel.getEquipmentId() +
                        ", Title: " + equipmentsModel.getEquipmentTitle() + ", Type: " + equipmentsModel.getEquipmentType() +
                        ", Brand: " + equipmentsModel.getEquipmentBrand());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*ProductsModel productsModel = new ProductsModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = productsModel.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                productsModel.loadProductById(resultSet.getInt("Id"));
                System.out.println("ID: " + productsModel.getProductId() +
                        ", Title: " + productsModel.getProductTitle() + ", Type: " + productsModel.getProductCategory() +
                        ", Price: " + productsModel.getProductCost() +
                        ", Quantity: " + productsModel.getProductStockState());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        PublicationsModel publicationsModel = new PublicationsModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = publicationsModel.executeQuery("SELECT * FROM publications");
            while (resultSet.next()) {
                publicationsModel.loadPublicationById(resultSet.getInt("Id"));
                System.out.println("ID: " + publicationsModel.getPublicationId() +
                        ", Title: " + publicationsModel.getTitle() +
                        ", Description: " + publicationsModel.getDescription() +
                        ", Starting date: " + publicationsModel.getPublicationDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

