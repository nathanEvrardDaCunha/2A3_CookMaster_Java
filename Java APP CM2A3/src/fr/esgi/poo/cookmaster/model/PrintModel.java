package fr.esgi.poo.cookmaster.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintModel extends Model{

    public PrintModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "print";
    }

    public boolean relationExists(int productId, int billId) {
        String sql = "SELECT COUNT(*) FROM print WHERE Product_Id = ? AND Bill_Id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, billId);
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
