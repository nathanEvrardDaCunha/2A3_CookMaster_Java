package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.EventsLocationModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateEventLocation {

    private static final int MIN_EVENT_LOCATION_COST = 0;
    private static final int MAX_EVENT_LOCATION_COST = 1300;

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateEventLocation(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    void generateEventLocations() throws SQLException{
        int eventLocationCost = CommonDataGenerator.selectRandomInt(MIN_EVENT_LOCATION_COST, MAX_EVENT_LOCATION_COST);
        String eventLocationAddress = CommonDataGenerator.selectRandomAdress();
        String eventLocationPostalCode = CommonDataGenerator.selectRandomPostalCode();
        String eventLocationCity = CommonDataGenerator.selectRandomCity();

        String sql = "INSERT INTO EVENT_LOCATIONS(Address, Postal_code, City, Cost) VALUES (?, ?, ?, ?)";

        EventsLocationModel eventsLocationModel = new EventsLocationModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = eventsLocationModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, eventLocationAddress);
            pstmt.setString(2, eventLocationPostalCode);
            pstmt.setString(3, eventLocationCity);
            pstmt.setInt(4, eventLocationCost);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
