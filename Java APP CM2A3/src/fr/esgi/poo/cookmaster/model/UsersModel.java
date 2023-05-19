package fr.esgi.poo.cookmaster.model;

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

}
