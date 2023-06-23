package fr.esgi.cookmaster_java_2a3.data;

import fr.esgi.cookmaster_java_2a3.model.RegisterModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateRegister {

    private String dbName;
    private String userName;
    private String password;

    public DataGenerateRegister(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateRegister() throws SQLException {

        RegisterModel registerModel = new RegisterModel(dbName, userName, password);

        int userId;
        int eventId;

        do {
            userId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_USERS);
            eventId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENTS);
        } while (registerModel.relationExists(userId, eventId));

        String sql = "INSERT INTO REGISTER(User_Id, Event_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = registerModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, eventId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            registerModel.close();
        }
    }

}

