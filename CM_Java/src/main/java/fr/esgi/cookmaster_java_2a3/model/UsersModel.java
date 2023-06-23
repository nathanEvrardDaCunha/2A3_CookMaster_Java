package fr.esgi.cookmaster_java_2a3.model;

import javafx.util.Pair;

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

    public int getNumberOfUserMaleAndType0() {
        int numberOfUserMaleAndType1 = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Sex = 0 AND s.Type = 0";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                numberOfUserMaleAndType1 = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfUserMaleAndType1;
    }

    public int getNumberOfUserMaleAndType1() {
        int numberOfUserMaleAndType1 = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Sex = 0 AND s.Type = 1";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                numberOfUserMaleAndType1 = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfUserMaleAndType1;
    }

    public int getNumberOfUserMaleAndType2() {
        int numberOfUserMaleAndType1 = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Sex = 0 AND s.Type = 2";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                numberOfUserMaleAndType1 = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfUserMaleAndType1;
    }

    public int getNumberOfUserFemaleAndType0() {
        int numberOfUserMaleAndType1 = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Sex = 1 AND s.Type = 0";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                numberOfUserMaleAndType1 = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfUserMaleAndType1;
    }

    public int getNumberOfUserFemaleAndType1() {
        int numberOfUserMaleAndType1 = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Sex = 1 AND s.Type = 1";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                numberOfUserMaleAndType1 = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfUserMaleAndType1;
    }

    public int getNumberOfUserFemaleAndType2() {
        int numberOfUserMaleAndType1 = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM USERS u " +
                    "JOIN SUBSCRIPTIONS s ON u.Subscription_Id = s.Id " +
                    "WHERE u.Sex = 1 AND s.Type = 2";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                numberOfUserMaleAndType1 = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfUserMaleAndType1;
    }

    public Pair<String, Integer> getTopUserByFidelityScore(int rank) {
        Pair<String, Integer> result = null;
        try {
            String query = "SELECT CONCAT(Firstname, ' ', Lastname) as Fullname, Total_fidelity_point " +
                    "FROM USERS " +
                    "ORDER BY Total_fidelity_point DESC, RAND() " +
                    "LIMIT " + (rank - 1) + ", 1";

            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                String fullname = resultSet.getString("Fullname");
                int score = resultSet.getInt("Total_fidelity_point");
                result = new Pair<>(fullname, score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getTableName() {
        return "users";
    }
}
