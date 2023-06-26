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
         *      + //
         *
         * Page des Statistiques des Prestations :
         *
         *     CAMEMBERT (Difficile) Répartition des prestations par nombre d'événements associés : Ce graphique affiche le nombre de prestations en fonction du nombre d'événements associés, révélant la corrélation entre les deux)
         *     + Nb de prestation "Emplacement louer pour événement"
         *     + Nb de prestation "Equipement louer pour événement"
         *     + Nb de prestation "Presonnel louer pour événement"
         *     // IL FAUT FAIRE UNE MOYENNE DU NOMBRE DE X LOUER PAR EVENEMENT
         *
         *     CAMEMBERT (Facile - Intermédiaire) Répartition des prestations par type : Ce diagramme camembert illustrera la proportion de chaque type de prestation offerte, permettant d'identifier les prestations les plus courantes.
         *     + Nb de prestation "Emplacement a louer"  pour événements
         *     + Nb de prestation "Equipement a louer"  pour événements
         *     + Nb de prestation "Personnel a louer"  pour événements
         *     + Nb de prestation "Achat provenant de la boutique du site" 
         *     + Nb de prestation "Abonnement au site" par client
         *
         *     BARRE (Intermédiaire) Top 5 des prestations les plus populaires : Ce graphique en barres affiche les cinq prestations les plus fréquentes, classées selon le nombre d'occurrences.
         *     + Nb de prestation "Emplacement a louer pour un événement" sur les 6 derniers mois
         *     + Nb de prestation "Equipement a louer pour un événement" sur les 6 derniers mois
         *     + Nb de prestation "Personnel a louer pour un événement" sur les 6 derniers mois
         *     + Nb de prestation "Achat provenant de la boutique du site" sur les 6 derniers mois
         *     + Nb de prestation "Abonnement au site" sur les 6 derniers mois
         *      // EMPLACEMENT / EQUIPEMENT / PERSONNEL / ACHAT / ABONNEMENT AVEC LE PLUS D'ASSOCIATION
         */
    }
}