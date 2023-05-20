package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.*;

import java.sql.*;

public class Launcher {

    private static final String DB_NAME = "cookmaster";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "OTr;fgwskSYf,o/$";

    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator(DB_NAME, USER_NAME, PASSWORD);
        dataGenerator.generateDataForDB();

        UsersModel model = new UsersModel(DB_NAME, USER_NAME, PASSWORD);

        try {
            ResultSet resultSet = model.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                model.loadUserById(resultSet.getInt("Id"));
                System.out.println("ID: " + model.getUserId() + ", Firstname: " + model.getUserFirstname() +
                        ", Lastname: " + model.getUserLastname() + ", Address: " + model.getUserAddress());
            }
            model.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


