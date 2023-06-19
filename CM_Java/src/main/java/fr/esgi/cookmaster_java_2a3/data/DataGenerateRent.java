package fr.esgi.cookmaster_java_2a3.data;


import fr.esgi.cookmaster_java_2a3.model.RentModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateRent {

    private String dbName;
    private String userName;
    private String password;

    public DataGenerateRent(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateNormalRent(int i) throws SQLException{
        RentModel rentModel = new RentModel(dbName, userName, password);

        int locationId;

        do {
            locationId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENT_LOCATIONS);
        } while (rentModel.relationExists(i, locationId));

        String sql = "INSERT INTO RENT(Event_Id, Location_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = rentModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, i);
            pstmt.setInt(2, locationId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rentModel.close();
        }

    }

    public void generateRandomRents() throws SQLException {
        RentModel rentModel = new RentModel(dbName, userName, password);

        int eventId;
        int locationId;

        do {
            eventId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENTS);
            locationId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENT_LOCATIONS);
        } while (rentModel.relationExists(eventId, locationId));

        String sql = "INSERT INTO RENT(Event_Id, Location_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = rentModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, eventId);
            pstmt.setInt(2, locationId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rentModel.close();
        }
    }

}

