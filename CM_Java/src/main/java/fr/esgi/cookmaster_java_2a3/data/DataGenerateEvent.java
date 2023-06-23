package fr.esgi.cookmaster_java_2a3.data;

import fr.esgi.cookmaster_java_2a3.model.EventsModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DataGenerateEvent {

    private static final int RANDOM_INDEX_MIN = 0;
    private static final String ENDING_DATE = "2025-12-31";
    private static final int EVENT_STATE_ONGOING = 1;
    private static final int EVENT_STATE_PAST = 2;
    private static final int EVENT_STATE_NOT_STARTED = 0;
    private static final int EVENT_TYPE_ATELIER = 0;
    private static final int EVENT_TYPE_DEGUSTATION = 1;
    private static final int EVENT_TYPE_FORMATION = 2;
    private static final String EVENT_TITLE_ATELIER = "Atelier";
    private static final String EVENT_TITLE_DEGUSTATION = "Degustation";
    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateEvent(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateEvents() throws SQLException {
        int randomIndex = CommonDataGenerator.selectRandomInt(RANDOM_INDEX_MIN, CommonSettings.ALL_ARRAY_SIZE);
        String eventTitle = selectEventTitle(randomIndex);
        String eventDescription = selectEventDescription(randomIndex);

        int eventType = selectEventType(eventTitle);
        String eventStartingDate = CommonDataGenerator.selectRandomDate("2022-01-01", "2024-12-31");
        String eventEndingDate = CommonDataGenerator.selectRandomDate(eventStartingDate, ENDING_DATE);
        int eventState = selectEventState(eventStartingDate, eventEndingDate);

        String sql = "INSERT INTO EVENTS(Type, Title, Description, State, Starting_date, Ending_date) VALUES (?, ?, ?, ?, ?, ?)";
        EventsModel eventsModel = new EventsModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = eventsModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, eventType);
            pstmt.setString(2, eventTitle);
            pstmt.setString(3, eventDescription);
            pstmt.setInt(4, eventState);
            pstmt.setString(5, eventStartingDate);
            pstmt.setString(6, eventEndingDate);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            eventsModel.close();
        }
    }

    private String selectEventTitle(int randomIndex) {
        String[] eventTitlesArray = {
                "The Grand Gourmet Atelier",
                "Feast of Flavours Degustation",
                "The Chef Atelier",
                "Epicurean Atelier",
                "Bakers' Degustation",
                "Cuisine Degustation",
                "Foodie Formation",
                "The Taste Test Atelier",
                "The Sizzling Summer Formation",
                "International Ingredient Formation",
                "Culinary Creations Formation",
                "The Aromatic Artisan Atelier",
                "Decadent Desserts Atelier",
                "The Savory Formation",
                "The Ultimate Umami Degustation"
        };

        return eventTitlesArray[randomIndex];
    }

    private String selectEventDescription(int randomIndex) {
        String[] eventDescriptionsArray = {
                "The Grand Gourmet Atelier: Un atelier haut de gamme qui réunit des chefs de renommée mondiale pour offrir une véritable expérience gastronomique.",
                "Feast of Flavours Degustation: Un événement de plusieurs jours où les participants peuvent déguster des plats d'une multitude de traditions culinaires.",
                "The Chef Atelier: Regardez des chefs renommés de diverses traditions culinaires rivaliser pour créer les plats les plus délicieux et uniques.",
                "Epicurean Atelier: Un atelier qui traverse diverses cuisines exquises du monde entier. Parfait pour les véritables amoureux de la nourriture.",
                "Bakers' Degustation: Une dégustation de boulangerie où les meilleurs pâtisseries, pains et autres produits de boulangerie sont mis en avant.",
                "Cuisine Degustation: Un événement festif avec des stands de nourriture proposant un large éventail de différentes cuisines. Une journée parfaite en famille.",
                "Foodie Formation: Une formation pour célébrer la nourriture dans toute sa gloire, avec de nombreux restaurants et food trucks présentant leurs spécialités.",
                "The Taste Test Atelier: Les participants deviennent juges dans un concours entre talents culinaires émergents, goûtant et notant une gamme de plats.",
                "The Sizzling Summer Formation: Un événement saisonnier en plein air présentant des barbecues, des grillades et des boissons rafraîchissantes d'été.",
                "International Ingredient Formation: Une formation pour les professionnels et les passionnés de l'industrie alimentaire pour explorer une vaste gamme d'ingrédients du monde entier.",
                "Culinary Creations Formation: Un lieu où les chefs, les restaurateurs et les gourmets peuvent échanger des idées et s'inspirer mutuellement avec leurs créations culinaires.",
                "The Aromatic Artisan Atelier: Les producteurs artisans présentent leurs produits aromatiques allant des fromages aux viandes séchées et épices.",
                "Decadent Desserts Atelier: Un atelier incontournable pour les gourmands, présentant les desserts les plus indulgents du monde entier.",
                "The Savory Formation: Une formation axée sur les délices salés, comprenant des séminaires et des ateliers dirigés par des chefs de file de l'industrie.",
                "The Ultimate Umami Degustation: Un événement dédié à l'exploration de l'umami, le soi-disant 'cinquième goût', avec une variété d'aliments riches en umami à déguster."
        };

        return eventDescriptionsArray[randomIndex];
    }

    private int selectEventType(String eventTitle) {
        if (eventTitle.contains(EVENT_TITLE_ATELIER)){
            return EVENT_TYPE_ATELIER;
        } else if (eventTitle.contains(EVENT_TITLE_DEGUSTATION)){
            return EVENT_TYPE_DEGUSTATION;
        } else {
            return EVENT_TYPE_FORMATION;
        }
    }

    private int selectEventState(String eventStartingDate, String eventEndingDate) {
        LocalDate today = LocalDate.now();
        LocalDate startEventDate = LocalDate.parse(eventStartingDate);
        LocalDate endEventDate = LocalDate.parse(eventEndingDate);

        if (today.isBefore(startEventDate)) {
            return EVENT_STATE_NOT_STARTED;
        } else if (today.isEqual(startEventDate) || (today.isAfter(startEventDate) && today.isBefore(endEventDate))) {
            return EVENT_STATE_ONGOING;
        } else {
            return EVENT_STATE_PAST;
        }
    }
}

