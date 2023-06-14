package fr.esgi.poo.cookmaster.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel extends Model{

    public RegisterModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "register";
    }

    public boolean relationExists(int userId, int eventId) {
        String sql = "SELECT COUNT(*) FROM register WHERE User_Id = ? AND Event_Id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, eventId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
