package fr.esgi.cookmaster_java_2a3.main;

import fr.esgi.cookmaster_java_2a3.data.*;
import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-ca-view.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("CookMaster's Dashboard");
        //icon : Image icon = new Image("/fr/esgi/cookmaster_java_2a3/assets/icon.png");
        //stage.getIcons().add(icon);
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataPurger dataPurger = new DataPurger(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        dataPurger.purgeData();

        DataGenerator dataGenerator = new DataGenerator(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
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
         * - S'assurer que chaque XArray est au minimum 50 éléments dedans
         *
         * - S'assurer que la limite dans le choix Random est bien de la taille des array
         *
         */

        PublicationsModel publicationsModel = new PublicationsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
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

        launch(args);

        /**
         * Page des Statistiques des Clients :
         *
         *     (Difficile) Répartition des clients par chiffre d'affaires (CA) : Ce graphique montrera la distribution du CA parmi tous les clients, permettant d'identifier les gammes de CA les plus communes.
         *      + Pour Ville => Cout abonnement + Dépense total pour achat de produit
         */
    }
}