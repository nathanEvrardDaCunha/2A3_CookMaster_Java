package fr.esgi.poo.cookmaster.model;

import java.sql.*;

abstract class Model {

    private Connection connection;

    public Model(String dbName, String userName, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, userName, password);
            System.out.println("Connected to database " + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int executeUpdate(String query) {
        try {
            Statement statement = this.connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}