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
         *     CAMEMBERT (Facile) Répartition des clients par type : Ce diagramme camembert illustrera la proportion de chaque type de client dans l'ensemble de la clientèle, mettant en évidence le type de client le plus fréquent.
         *     + Nb Client femme abonnement 1
         *     + Nb Client homme abonnement 1
         *     + Nb Client femme abonnement 2
         *     + Nb Client homme abonnement 2
         *     + Nb Client femme abonnement 3
         *     + Nb Client homme abonnement 3
         *
         *     LIGNE (Facile) Régularité d'achat des clients : Ce graphique en ligne indiquera la dynamique d'achat des clients en sur une période de temps (ex: 50 commandes sur cette dernière semaine...)
         *     + Nb Commande entre 0 et 7 jours
         *     + Nb Commande entre 7 jours et 1 mois
         *     + Nb Commande entre 1 mois et 3 mois
         *     + Nb Commande entre 3 mois et 6 mois
         *     + Nb Commande entre 6 mois et 1 an
         *     + Nb Commande entre 1 an et plus
         *
         *     BARRE (Facile) Top 5 des clients les plus fidèles : Ce graphique en barres affiche les cinq clients les plus fidèles, classés selon leur score de fidélité.
         *     - Récupérer le score de fidélité total de chaque client
         *
         * Page des Statistiques des Événements :
         *
         *     CAMEMBERT (Facile) Répartition des événements par type : Ce diagramme camembert montrera la proportion de chaque type d'événement organisé, révélant le type d'événement le plus populaire.
         *     + Nb Evenement d'Atelier
         *     + Nb Evenement de Dégustation
         *     + Nb Evenement de Formation
         *     (REFAIRE LES TYPES D'EVENEMENTS DANS LE CODE JAVA : Ateliers, Dégustation et Formations car j'ai mis des valeurs aléatoires comme exemple descriptif)
         *
         *     BARRE (Intermédiaire) Fréquence de planification des événements : Ce graphique en ligne indiquera combien d'événements sont planifiés sur une période de temps, révélant les périodes de pic d'activité.
         *     + Nb Evenement durant Janvier de l'année en cours
         *     + Nb Evenement durant Février de l'année en cours
         *     + Nb Evenement durant Mars de l'année en cours
         *     + Nb Evenement durant Avril de l'année en cours
         *     + Nb Evenement durant Mai de l'année en cours
         *     + Nb Evenement durant Juin de l'année en cours
         *     + Nb Evenement durant Juillet de l'année en cours
         *     + Nb Evenement durant Août de l'année en cours
         *     + Nb Evenement durant Septembre de l'année en cours
         *     + Nb Evenement durant Octobre de l'année en cours
         *     + Nb Evenement durant Novembre de l'année en cours
         *     + Nb Evenement durant Décembre de l'année en cours
         *     - Faire de même pour les mois des 2 dernière années passé
         *
         *     CAMEMBERT (Facile - Intermédiaire) Top 5 des événements les plus demandés : Ce graphique en barres affiche les cinq événements les plus demandés, classés par popularité.
         *     + Mettre les 5 événements avec le plus d'utilisateur inscrit
         *
         * Page des Statistiques des Prestations :
         *
         *     CAMEMBERT (Facile - Intermédiaire) Répartition des prestations par nombre d'événements associés : Ce graphique affiche le nombre de prestations en fonction du nombre d'événements associés, révélant la corrélation entre les deux)
         *     + Nb de prestation "Emplacement louer pour événement"
         *     + Nb de prestation "Equipement louer pour événement"
         *     + Nb de prestation "Presonnel louer pour événement"
         *     + Nb de prestation "Achat provenant de la boutique du site"
         *     + Nb de prestation "Abonnement au site"
         *
         *     BARRE (Facile - Intermédiaire) Répartition des prestations par coût : Ce graphique montrera la distribution des coûts des prestations, permettant d'identifier les gammes de coûts les plus fréquentes.
         *     + CA total de prestation "Emplacement louer pour événement"
         *     + CA total de prestation "Equipement louer pour événement"
         *     + CA total de prestation "Presonnel louer pour événement"
         *     + CA total de prestation "Achat provenant de la boutique du site"
         *     + CA total de prestation "Abonnement au site"
         *
         *     CAMEMBERT (Facile - Intermédiaire) Répartition des prestations par type : Ce diagramme camembert illustrera la proportion de chaque type de prestation offerte, permettant d'identifier les prestations les plus courantes.
         *     + Nb de prestation "Emplacer a louer" total
         *     + Nb de prestation "Equipement a louer" total
         *     + Nb de prestation "Personnel a louer" total
         *     + Nb de prestation "Achat provenant de la boutique du site" total
         *
         *     BARRE (Intermédiaire) Top 5 des prestations les plus fréquentes : Ce graphique en barres affiche les cinq prestations les plus fréquentes, classées selon le nombre d'occurrences.
         *     + Nb de prestation "Emplacement a louer pour un événement" sur les 6 derniers mois
         *     + Nb de prestation "Equipement a louer pour un événement" sur les 6 derniers mois
         *     + Nb de prestation "Personnel a louer pour un événement" sur les 6 derniers mois
         *     + Nb de prestation "Achat provenant de la boutique du site" sur les 6 derniers mois
         *     + Nb de prestation "Abonnement au site" sur les 6 derniers mois
         *
         */
    }
}