package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.EventsModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;
import fr.esgi.poo.cookmaster.tools.CommonSettings;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DataGenerateEvent {

    private static final int RANDOM_INDEX_MIN = 0;
    private static final String ENDING_DATE = "2025-12-31";
    private static final int EVENT_STATE_ONGOING = 1;
    private static final int EVENT_STATE_PAST = 2;
    private static final int EVENT_STATE_NOT_STARTED = 0;
    private static final int EVENT_TYPE_GOURMET = 0;
    private static final int EVENT_TYPE_CONFERENCE = 1;
    private static final int EVENT_TYPE_ESCAPADE = 2;
    private static final int EVENT_TYPE_EXPO = 3;
    private static final int EVENT_TYPE_OTHERS = 4;
    private static final String EVENT_TITLE_GOURMET = "Gourmet";
    private static final String EVENT_TITLE_CONFERENCE = "Conference";
    private static final String EVENT_TITLE_ESCAPADE = "Escapade";
    private static final String EVENT_TITLE_EXPO = "Expo";

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateEvent(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateEvents(int i) throws SQLException {
        int randomIndex = CommonDataGenerator.selectRandomInt(RANDOM_INDEX_MIN, CommonSettings.ALL_ARRAY_SIZE);
        String eventTitle = selectEventTitle(randomIndex);
        String eventDescription = selectEventDescription(randomIndex);

        int eventType = selectEventType(eventTitle);
        String eventStartingDate = CommonDataGenerator.selectRandomDate("2020-01-01", "2022-12-31");
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
        }
    }

    private String selectEventTitle(int randomIndex) {
        String[] eventTitlesArray = {
                "The Grand Gourmet Gala",
                "Feast of Flavours Festival",
                "The Chef Showdown",
                "Epicurean Escapade",
                "Bakers' Bonanza",
                "Cuisine Carnival",
                "Foodie Fiesta",
                "The Taste Test Triumph",
                "The Sizzling Summer Soirée",
                "International Ingredient Expo",
                "Culinary Creations Conference",
                "The Aromatic Artisan Assembly",
                "Decadent Desserts Display",
                "The Savory Symposium",
                "The Ultimate Umami Unveiling"
        };

        return eventTitlesArray[randomIndex];
    }

    private String selectEventDescription(int randomIndex) {
        String[] eventDescriptionsArray = {
                "The Grand Gourmet Gala: A high-end event gathering top chefs from all over the world to deliver a truly gourmet experience.",
                "Feast of Flavours Festival: A multi-day event where participants can taste dishes from a multitude of culinary traditions.",
                "The Chef Showdown: Watch as renowned chefs from diverse culinary backgrounds compete to create the most delicious and unique dishes.",
                "Epicurean Escapade: A journey through various exquisite cuisines from around the globe. Perfect for true food lovers.",
                "Bakers' Bonanza: A baking extravaganza where the finest pastries, breads, and other baked goods take the spotlight.",
                "Cuisine Carnival: A festive event featuring food stalls with a wide array of different cuisines. A perfect day out for the family.",
                "Foodie Fiesta: A celebration of food in all its glory, with numerous restaurants and food trucks showcasing their specialties.",
                "The Taste Test Triumph: Attendees get to be the judge in a cook-off between emerging culinary talents, tasting and scoring a range of dishes.",
                "The Sizzling Summer Soirée: A seasonal outdoor event featuring barbecues, grills and refreshing summer beverages.",
                "International Ingredient Expo: An event for food industry professionals and enthusiasts to explore a vast range of ingredients from all over the world.",
                "Culinary Creations Conference: A place where chefs, restaurateurs and foodies can exchange ideas and inspire each other with their culinary creations.",
                "The Aromatic Artisan Assembly: Artisan producers showcase their aromatic products ranging from cheeses to cured meats and spices.",
                "Decadent Desserts Display: A must-visit event for the sweet-toothed, featuring the most indulgent desserts from across the globe.",
                "The Savory Symposium: An event focussing on savoury delicacies, including seminars and workshops by leading chefs in the industry.",
                "The Ultimate Umami Unveiling: An event dedicated to exploring umami, the so-called 'fifth taste', with a variety of umami-rich foods to sample."
        };

        return eventDescriptionsArray[randomIndex];
    }

    private int selectEventType(String eventTitle) {
        if (eventTitle.contains(EVENT_TITLE_GOURMET)){
            return EVENT_TYPE_GOURMET;
        } else if (eventTitle.contains(EVENT_TITLE_ESCAPADE)){
            return EVENT_TYPE_ESCAPADE;
        } else if (eventTitle.contains(EVENT_TITLE_CONFERENCE)){
            return EVENT_TYPE_CONFERENCE;
        } else if (eventTitle.contains(EVENT_TITLE_EXPO)){
            return EVENT_TYPE_EXPO;
        } else {
            return EVENT_TYPE_OTHERS;
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
