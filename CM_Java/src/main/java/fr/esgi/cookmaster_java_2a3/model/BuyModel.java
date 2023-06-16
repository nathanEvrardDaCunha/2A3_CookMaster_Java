package fr.esgi.cookmaster_java_2a3.model;

import java.sql.*;

public class BuyModel extends Model {

    public BuyModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "buy";
    }

    public boolean relationExists(int userId, int productId) {
        String sql = "SELECT COUNT(*) FROM buy WHERE User_Id = ? AND Product_Id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
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
