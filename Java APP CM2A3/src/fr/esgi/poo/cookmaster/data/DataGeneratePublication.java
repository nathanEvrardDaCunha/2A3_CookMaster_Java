package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.PublicationsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DataGeneratePublication {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGeneratePublication(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generatePublications(int i) throws SQLException{

        //PARTIE A REVOIR POUR ETRE SUR QUE CHAQUE CHOSE EST COHERENTE
        //Etre sûr que l'utilisateur qui publie est un utilisateur qui existe (donc vérifier date d'inscription)
        String publicationDate = selectTempPublicationDate();

        int randomInt = new Random().nextInt(10);
        String publicationTitle = selectRandomPublicationTitle(randomInt);
        String publicationDescription = selectRandomPublicationDescription(randomInt);

        String sql = "INSERT INTO PUBLICATIONS(Title, Publication_date, Description, Id_1) VALUES (?, ?, ?, ?)";

        PublicationsModel publicationsModel = new PublicationsModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = publicationsModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, publicationTitle);
            pstmt.setString(2, publicationDate);
            pstmt.setString(3, publicationDescription);
            pstmt.setInt(4, i);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String selectTempPublicationDate() {
        Random random = new Random();
        int randomInt = random.nextInt(30);
        return "2021-05-01";
    }

    private String selectRandomPublicationTitle(int randomInt) {
        String[] publicationTitlesArray = {
                "Ma Recette de Gâteau au Chocolat Fondant",
                "10 Astuces pour Améliorer vos Techniques de Cuisson",
                "Un Week-end de Barbecue : Mes Recettes Préférées",
                "Critique : Mon Avis sur le Nouveau Robot de Cuisine Multi-fonction",
                "Comment Faire la Parfaite Pâte à Pizza Maison",
                "Recette : Salade César Façon Gourmet",
                "Le Meilleur Moment pour Acheter des Fruits de Saison",
                "La Science Derrière le Pain Maison : Comment Ça Marche",
                "Comment Organiser Votre Cuisine pour Plus d'Efficacité",
                "Faire vos Propres Pâtes Fraîches : Un Guide Étape par Étape",
                "Les Épices que Tout le Monde devrait Avoir dans sa Cuisine",
                "Recette de Crêpes Légères et Moelleuses",
                "Mon Expérience avec le Jeûne Intermittent et la Cuisine Saine",
                "Conseils pour Préparer un Repas Romantique à la Maison",
                "Recette : Mes Cookies Américains Favoris"
        };

        return publicationTitlesArray[randomInt];
    }

    private String selectRandomPublicationDescription(int randomInt) {
        String[] publicationDescriptionArray = {
                "Aujourd'hui, je partage avec vous ma recette préférée de gâteau au chocolat. Suivez ces étapes pour un dessert incroyablement fondant!",
                "Vous voulez améliorer vos compétences en cuisine? Voici 10 astuces essentielles pour devenir un meilleur cuisinier.",
                "Le week-end est le moment idéal pour un barbecue. Voici quelques-unes de mes recettes préférées pour vous inspirer!",
                "J'ai récemment acheté un nouveau robot de cuisine. Voici mon avis détaillé sur cet appareil polyvalent.",
                "Rien ne vaut une pizza faite maison. Voici comment faire votre propre pâte à pizza pour une soirée pizza parfaite.",
                "Voici une délicieuse recette de salade César que vous pouvez facilement réaliser à la maison. C'est frais, croustillant et absolument savoureux!",
                "Savoir quand acheter des fruits peut faire une grande différence en termes de goût. Voici quelques conseils sur le choix des fruits de saison.",
                "Faire son propre pain peut sembler intimidant, mais c'est en fait assez simple. Voici une explication de la science derrière le pain maison.",
                "Une cuisine organisée rend la préparation des repas beaucoup plus facile. Voici quelques astuces pour maximiser votre espace de cuisine.",
                "Faire ses propres pâtes peut sembler difficile, mais avec ces étapes faciles à suivre, vous serez un expert en un rien de temps!",
                "Les épices sont essentielles pour créer de délicieux plats. Voici une liste des épices que je pense que tout le monde devrait avoir dans sa cuisine.",
                "Vous avez envie de crêpes pour le petit déjeuner ? Voici ma recette préférée pour des crêpes légères et moelleuses.",
                "Je suis récemment passé au jeûne intermittent et j'ai adopté une cuisine plus saine. Voici mon expérience.",
                "Planifier un dîner romantique à la maison peut être stressant, mais avec ces conseils, vous pouvez créer une soirée mémorable sans tracas.",
                "Je suis un grand fan de cookies américains et j'ai enfin perfectionné ma recette. Voici comment je fais mes cookies préférés!"
        };

        return publicationDescriptionArray[randomInt];
    }
}