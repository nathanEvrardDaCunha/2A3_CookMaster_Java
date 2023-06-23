package fr.esgi.cookmaster_java_2a3.model;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Pair<String, Integer>> getTopEventsByParticipants() {
        List<Pair<String, Integer>> results = new ArrayList<>();
        try {
            String query = "SELECT Events.Title, COUNT(Register.User_Id) as Count " +
                    "FROM Events LEFT JOIN Register ON Events.Id = Register.Event_Id " +
                    "GROUP BY Events.Id " +
                    "ORDER BY Count DESC " +
                    "LIMIT 5";

            ResultSet resultSet = this.executeQuery(query);

            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                int count = resultSet.getInt("Count");
                results.add(new Pair<>(title, count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public int getNumberOfEventsInMonthYear(int month, int year) {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as Count " +
                    "FROM events " +
                    "WHERE YEAR(Starting_date) = ? AND MONTH(Starting_date) = ?";

            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setInt(1, year);
            statement.setInt(2, month);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("Count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public int getNumberOfEventsOfType(int eventType) {
        String query = "SELECT COUNT(*) FROM events WHERE Type = ?";
        int count = 0;

        try {
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setInt(1, eventType);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                count = resultSet.getInt(1);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public String getTableName() {
        return "events";
    }
}

