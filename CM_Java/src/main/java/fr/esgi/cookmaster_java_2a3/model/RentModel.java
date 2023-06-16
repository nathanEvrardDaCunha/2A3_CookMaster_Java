package fr.esgi.cookmaster_java_2a3.model;

import java.sql.*;

public class RentModel extends Model{

    public RentModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "rent";
    }

    public boolean relationExists(int eventId, int eventLocationId) {
        String sql = "SELECT COUNT(*) FROM rent WHERE Event_Id = ? AND Location_Id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, eventLocationId);
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
