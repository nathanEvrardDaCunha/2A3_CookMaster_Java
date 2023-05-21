package fr.esgi.poo.cookmaster.model;

import java.sql.*;

public class EventsLocationModel extends Model {

    private ResultSet eventLocation;

    public EventsLocationModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadEventLocationById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM event_locations WHERE Id = " + id);
            if (resultSet.next()) {
                this.eventLocation = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getEventLocationId() {
        try {
            return this.eventLocation.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getEventLocationAddress() {
        try {
            return this.eventLocation.getString("Address");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getEventLocationPostalCode() {
        try {
            return this.eventLocation.getString("Postal_code");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getEventLocationCost() {
        try {
            return this.eventLocation.getInt("Cost");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getTableName() {
        return "event_locations";
    }
}

