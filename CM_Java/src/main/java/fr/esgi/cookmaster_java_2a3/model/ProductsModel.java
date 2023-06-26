package fr.esgi.cookmaster_java_2a3.model;

import java.sql.*;

public class ProductsModel extends Model {

    private ResultSet product;

    public ProductsModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadProductById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM products WHERE Id = " + id);
            if (resultSet.next()) {
                this.product = resultSet;
                System.out.println("Product loaded: " + this.product.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProductId() {
        try {
            return this.product.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getProductTitle() {
        try {
            return this.product.getString("Title");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getProductCost() {
        try {
            return this.product.getInt("Cost");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getProductDescription() {
        try {
            return this.product.getString("Description");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getProductCategory() {
        try {
            return this.product.getInt("Category");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getProductStockState() {
        try {
            return this.product.getInt("Stock_state");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int getCAOfProduct() {
        int totalCA = 0;
        try {
            String query = "SELECT Cost, Total_sell FROM products";
            ResultSet resultSet = this.executeQuery(query);

            while (resultSet.next()) {
                int cost = resultSet.getInt("Cost");
                int totalSell = resultSet.getInt("Total_sell");
                totalCA += cost * totalSell;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCA;
    }

    public int getNumberOfProductsUsed() {
        int totalNumberOfSales = 0;
        try {
            String query = "SELECT SUM(Total_sell) AS TotalSales FROM products";
            ResultSet resultSet = this.executeQuery(query);

            if (resultSet.next()) {
                totalNumberOfSales = resultSet.getInt("TotalSales");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalNumberOfSales;
    }



    @Override
    public String getTableName() {
        return "products";
    }
}

