package fr.esgi.cookmaster_java_2a3.main;

import fr.esgi.cookmaster_java_2a3.data.*;
import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fr/esgi/cookmaster_java_2a3/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
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
    }
}