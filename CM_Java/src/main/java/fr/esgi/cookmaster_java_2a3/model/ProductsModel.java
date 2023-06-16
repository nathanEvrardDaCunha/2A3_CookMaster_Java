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

    @Override
    public String getTableName() {
        return "products";
    }
}

