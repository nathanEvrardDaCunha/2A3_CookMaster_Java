package fr.esgi.cookmaster_java_2a3.model;

import java.sql.*;

public class UsersModel extends Model {

    private ResultSet user;

    public UsersModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadUserById(int id) {
        try {
            this.user = this.executeQuery("SELECT * FROM users WHERE Id = " + id);
            if (!this.user.next()) {
                System.out.println("User not found.");
                this.user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserId() throws SQLException {
        return this.user.getInt("Id");
    }

    public String getUserFirstname() throws SQLException {
        return this.user.getString("Firstname");
    }

    public String getUserLastname() throws SQLException {
        return this.user.getString("Lastname");
    }

    public String getUserAddress() throws SQLException {
        return this.user.getString("Address");
    }

    public String getUserPostalCode() throws SQLException {
        return this.user.getString("Postal_code");
    }

    public int getUserRole() throws SQLException {
        return this.user.getInt("Role");
    }

    public Date getUserRegistrationDate() throws SQLException {
        return this.user.getDate("Registration_date");
    }

    public int getUserFidelityPoint() throws SQLException {
        return this.user.getInt("Fidelity_point");
    }

    public Date getUserLastPurchaseDate() throws SQLException {
        return this.user.getDate("Last_purchase_date");
    }

    public Date getUserEndingSubscriptionDate() throws SQLException {
        return this.user.getDate("Ending_subscription_date");
    }

    public Date getUserStartingSubscriptionDate() throws SQLException {
        return this.user.getDate("Starting_subscription_date");
    }

    public int getUserSex() throws SQLException {
        return this.user.getInt("Sex");
    }

    public Date getUserBirthday() throws SQLException {
        return this.user.getDate("Birthday");
    }

    public int getUserId_1() throws SQLException {
        return this.user.getInt("Id_1");
    }

    public String getUserCity() throws SQLException {
        return this.user.getString("City");
    }

    public String getUserEmail() throws SQLException {
        return this.user.getString("Email");
    }

    public String getUserPassword() throws SQLException {
        return this.user.getString("Password");
    }

    public void close() {
        try {
            if (this.user != null) {
                this.user.close();
            }
            super.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userExistsWithNameBirthdate(String lastname, String firstname, String birthdate) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT * FROM Users WHERE Lastname = ? AND Firstname = ? AND BirthDay = ?"
            );
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, birthdate);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userExistsWithNameAddress(String lastname, String firstname, String city, String address, String postalCode) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT * FROM Users WHERE Lastname = ? AND Firstname = ? AND City = ? AND Address = ? AND Postal_code = ?"
            );
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, city);
            ps.setString(4, address);
            ps.setString(5, postalCode);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userExistsWithEmail(String email) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT * FROM Users WHERE Email = ?"
            );
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userExistsWithUsername(String username) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT * FROM Users WHERE Username = ?"
            );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSubscriptionFrequencyOfCostByUserId(int userId) {
        int frequencyOfCost = 0;
        try {
            String query = "SELECT s.Frequency_of_cost FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Id = " + userId;

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                frequencyOfCost = resultSet.getInt("Frequency_of_cost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return frequencyOfCost;
    }

    @Override
    public String getTableName() {
        return "users";
    }
}
