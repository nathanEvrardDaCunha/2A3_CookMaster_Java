package fr.esgi.poo.cookmaster.model;

import java.sql.*;

public class PublicationsModel extends Model {

    private ResultSet publication;

    public PublicationsModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadPublicationById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM publications WHERE Id = " + id);
            if (resultSet.next()) {
                this.publication = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPublicationId() {
        try {
            return this.publication.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getTitle() {
        try {
            return this.publication.getString("Title");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Timestamp getPublicationDate() {
        try {
            return this.publication.getTimestamp("Publication_date");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDescription() {
        try {
            return this.publication.getString("Description");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUserId() {
        try {
            return this.publication.getInt("Id_1");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getTableName() {
        return "publications";
    }
}