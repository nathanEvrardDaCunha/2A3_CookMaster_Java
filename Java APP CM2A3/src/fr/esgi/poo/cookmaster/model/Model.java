package fr.esgi.poo.cookmaster.model;

import java.sql.*;

abstract class Model {

    private Connection connection;

    public Model(String dbName, String userName, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, userName, password);
            System.out.println("Connected to database " + getTableName());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        return "";
    }

    //CHANGER POUR QUIL NE RESTE QUE LA PREPARET STATEMENT
    public ResultSet executeQuery(String query) {
        try {
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //CHANGER POUR QUIL NE RESTE QUE LA PREPARET STATEMENT
    public int executeUpdate(String query) {
        try {
            Statement statement = this.connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int executeUpdate(PreparedStatement pstmt) {
        try {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public Connection getConnection() {
        return this.connection;
    }


    public void purge() {
        // Delete all rows from the table
        String sqlDelete = "DELETE FROM " + this.getTableName();
        this.executeUpdate(sqlDelete);
        System.out.println("All data from table " + this.getTableName() + " has been deleted.");

        // Reset AUTO_INCREMENT
        String sqlAlter = "ALTER TABLE " + this.getTableName() + " AUTO_INCREMENT = 1";
        this.executeUpdate(sqlAlter);
        System.out.println("AUTO_INCREMENT for table " + this.getTableName() + " has been reset to 1.");
    }


    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}