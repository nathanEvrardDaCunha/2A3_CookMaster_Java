package fr.esgi.poo.cookmaster.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganiseModel extends Model{

    public OrganiseModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "organise";
    }

    public boolean relationExists(int eventId, int providerId) {
        String sql = "SELECT COUNT(*) FROM organise WHERE Event_Id = ? AND Provider_Id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, providerId);
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
