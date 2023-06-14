package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.OrganiseModel;
import fr.esgi.poo.cookmaster.model.PrintModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateOrganise {

    private String dbName;
    private String userName;
    private String password;

    public DataGenerateOrganise(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    void generateNormalOrganise(int i) throws SQLException {
        OrganiseModel organiseModel = new OrganiseModel(dbName, userName, password);

        int providerId;

        do {
            providerId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_PROVIDERS);
        } while (organiseModel.relationExists(i, providerId));

        String sql = "INSERT INTO ORGANISE(Event_Id, Provider_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = organiseModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, i);
            pstmt.setInt(2, providerId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateRandomOrganises() throws SQLException {
        OrganiseModel organiseModel = new OrganiseModel(dbName, userName, password);

        int eventId;
        int providerId;

        do {
            eventId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENTS);
            providerId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_PROVIDERS);
        } while (organiseModel.relationExists(eventId, providerId));

        String sql = "INSERT INTO ORGANISE(Event_Id, Provider_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = organiseModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, eventId);
            pstmt.setInt(2, providerId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
