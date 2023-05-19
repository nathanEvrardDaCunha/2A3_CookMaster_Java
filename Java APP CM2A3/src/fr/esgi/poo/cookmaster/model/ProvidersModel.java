package fr.esgi.poo.cookmaster.model;

import java.sql.*;

public class ProvidersModel extends Model {

    private ResultSet provider;

    public ProvidersModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadProviderById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM providers WHERE Id = " + id);
            if (resultSet.next()) {
                this.provider = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProviderId() {
        try {
            return this.provider.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getProviderFirstname() {
        try {
            return this.provider.getString("Firstname");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getProviderLastname() {
        try {
            return this.provider.getString("Lastname");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getProviderType() {
        try {
            return this.provider.getInt("Type");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getProviderCompanyName() {
        try {
            return this.provider.getString("Provider_compagny_name");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getProviderCost() {
        try {
            return this.provider.getInt("Cost");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getProviderDescription() {
        try {
            return this.provider.getString("Description");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getProviderNumberOfEventsOrganised() {
        try {
            return this.provider.getInt("Number_of_events_organised");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
