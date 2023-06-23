package fr.esgi.cookmaster_java_2a3.model;

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

    public boolean providerExists(String firstname, String lastname, String companyName, int type) throws SQLException {
        String query = "SELECT COUNT(*) FROM PROVIDERS WHERE Firstname = ? AND Lastname = ? AND Provider_compagny_name = ? AND Type = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, firstname);
        stmt.setString(2, lastname);
        stmt.setString(3, companyName);
        stmt.setInt(4, type);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }

        return false;
    }

    public int getCAOfProvider() {
        int totalCA = 0;

        try {
            String query = "SELECT p.Cost, COUNT(o.Event_Id) as EventCount "
                    + "FROM providers p "
                    + "LEFT JOIN organise o ON o.Provider_Id = p.Id "
                    + "GROUP BY p.Id";

            PreparedStatement stmt = getConnection().prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int cost = rs.getInt("Cost");
                int eventCount = rs.getInt("EventCount");

                totalCA += cost * eventCount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCA;
    }


    @Override
    public String getTableName() {
        return "providers";
    }
}

