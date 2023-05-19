package fr.esgi.poo.cookmaster.model;

import java.sql.*;

public class EventsModel extends Model {

    private ResultSet event;

    public EventsModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadEventById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM events WHERE Id = " + id);
            if (resultSet.next()) {
                this.event = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getEventId() {
        try {
            return this.event.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getEventType() {
        try {
            return this.event.getInt("Type");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getEventTitle() {
        try {
            return this.event.getString("Title");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getEventDescription() {
        try {
            return this.event.getString("Description");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getEventState() {
        try {
            return this.event.getInt("State");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Date getEventStartingDate() {
        try {
            return this.event.getDate("Starting_date");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date getEventEndingDate() {
        try {
            return this.event.getDate("Ending_date");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
