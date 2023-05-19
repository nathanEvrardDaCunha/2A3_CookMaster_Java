package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.*;

import java.sql.*;

public class Launcher {

    public static void main(String[] args) {
        UsersModel model = new UsersModel("cookmaster", "root", "OTr;fgwskSYf,o/$");

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
