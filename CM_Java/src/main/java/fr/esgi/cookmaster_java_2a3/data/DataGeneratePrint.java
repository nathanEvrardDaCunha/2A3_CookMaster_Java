package fr.esgi.cookmaster_java_2a3.data;

import fr.esgi.cookmaster_java_2a3.model.PrintModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGeneratePrint {

    private String dbName;
    private String userName;
    private String password;

    public DataGeneratePrint(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    void generatePrint() throws SQLException {
        PrintModel printModel = new PrintModel(dbName, userName, password);

        int productId;
        int billId;

        do {
            productId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_PRODUCTS);
            billId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_BILLS);
        } while (printModel.relationExists(productId, billId));

        String sql = "INSERT INTO PRINT(Product_Id, Bill_Id) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = printModel.getConnection().prepareStatement(sql);

            pstmt.setInt(1, productId);
            pstmt.setInt(2, billId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            printModel.close();
        }
    }
}
