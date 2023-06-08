package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.EventsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.time.LocalDate;


public class DataGenerateEvent {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateEvent(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateEvents(int i) throws SQLException {
        int randomIndex = selectRandomIndex();
        String eventTitle = selectEventTitle(randomIndex);
        String eventDescription = selectEventDescription(randomIndex);

        int eventType = selectEventType(eventTitle);
        String eventStartingDate = selectEventStartingDate();
        String eventEndingDate = selectEventEndingDate(eventStartingDate);
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

    private int selectRandomIndex() {
        Random random = new Random();
        return random.nextInt(15);
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
        if (eventTitle.contains("Gourmet") || eventTitle.contains("Gala") || eventTitle.contains("Showdown")){
            return 0;
        } else if (eventTitle.contains("Escapade") || eventTitle.contains("Bonanza") || eventTitle.contains("Symposium")){
            return 1;
        } else if (eventTitle.contains("Unveiling") || eventTitle.contains("Festival") || eventTitle.contains("Fiesta")){
            return 2;
        } else if (eventTitle.contains("Expo") || eventTitle.contains("Conference") || eventTitle.contains("Assembly") || eventTitle.contains("Display")){
            return 3;
        } else {
            return 4;
        }
    }

    private String selectEventStartingDate() {
        Random random = new Random();
        int year = random.nextInt(4) + 2020;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;

        return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
    }

    private String selectEventEndingDate(String eventStartingDate) {
        String[] eventStartingDateArray = eventStartingDate.split("-");
        int year = Integer.parseInt(eventStartingDateArray[0]);
        int month = Integer.parseInt(eventStartingDateArray[1]);
        int day = Integer.parseInt(eventStartingDateArray[2]);

        Random random = new Random();
        int yearOffset = random.nextInt(4) + 1;
        int monthOffset = random.nextInt(3) + 1;
        int dayOffset = random.nextInt(7) + 1;

        if (day + dayOffset > 28) {
            month += 1;
            day = day + dayOffset - 28;
        } else {
            day += dayOffset;
        }

        if (month + monthOffset > 12) {
            year += yearOffset + 1;
            month = month + monthOffset - 12;
        } else {
            year += yearOffset;
            month += monthOffset;
        }

        return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
    }


    private int selectEventState(String eventStartingDate, String eventEndingDate) {
        LocalDate today = LocalDate.now();
        LocalDate startEventDate = LocalDate.parse(eventStartingDate);
        LocalDate endEventDate = LocalDate.parse(eventEndingDate);

        if (today.isBefore(startEventDate)) {
            return 0;
        } else if (today.isEqual(startEventDate) || (today.isAfter(startEventDate) && today.isBefore(endEventDate))) {
            return 1;
        } else {
            return 2;
        }
    }

}
