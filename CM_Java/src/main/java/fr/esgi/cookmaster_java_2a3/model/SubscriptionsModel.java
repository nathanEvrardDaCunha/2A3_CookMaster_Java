package fr.esgi.cookmaster_java_2a3.model;

import java.sql.*;

public class SubscriptionsModel extends Model {

    private ResultSet subscription;

    public SubscriptionsModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadSubscriptionById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM subscriptions WHERE Id = " + id);
            if (resultSet.next()) {
                this.subscription = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSubscriptionId() {
        try {
            return this.subscription.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getSubscriptionTitle() {
        try {
            return this.subscription.getString("Title");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSubscriptionCost() {
        try {
            return this.subscription.getInt("Cost");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getSubscriptionType() {
        try {
            return this.subscription.getInt("Type");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getSubscriptionDescription() {
        try {
            return this.subscription.getString("Description");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSubscriptionFrequencyOfCost() {
        try {
            return this.subscription.getInt("Frequency_of_cost");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getTableName() {
        return "subscriptions";
    }
}

