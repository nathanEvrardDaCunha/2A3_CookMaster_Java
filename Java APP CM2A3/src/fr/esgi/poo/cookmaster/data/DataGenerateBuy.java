package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.BuyModel;
import fr.esgi.poo.cookmaster.model.OrganiseModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateBuy {

    private String dbName;
    private String userName;
    private String password;

    public DataGenerateBuy(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    void generateBuy() throws SQLException {
        BuyModel buyModel = new BuyModel(dbName, userName, password);

        int userId;
        int productId;

        do {
            userId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_USERS);
            productId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_PRODUCTS);
        } while (buyModel.relationExists(userId, productId));

        String sql = "INSERT INTO BUY(User_Id, Product_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = buyModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
