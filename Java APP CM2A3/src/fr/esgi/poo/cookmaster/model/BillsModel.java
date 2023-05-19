package fr.esgi.poo.cookmaster.model;

import java.sql.*;

public class BillsModel extends Model {

    private ResultSet bill;

    public BillsModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadBillById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM bills WHERE Id = " + id);
            if (resultSet.next()) {
                this.bill = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBillId() {
        try {
            return this.bill.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getPurchaseContent() {
        try {
            return this.bill.getString("Purchase_content");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Timestamp getPurchaseDate() {
        try {
            return this.bill.getTimestamp("Purchase_date");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFirstnameOfBuyer() {
        try {
            return this.bill.getString("Firstname_of_buyer");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLastnameOfBuyer() {
        try {
            return this.bill.getString("Lastname_of_buyer");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPostalCodeOfBuyer() {
        try {
            return this.bill.getString("Postal_code_of_buyer");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAddressOfBuyer() {
        try {
            return this.bill.getString("Address_of_buyer");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCostOfPurchase() {
        try {
            return this.bill.getInt("Cost_of_purchase");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getEventId() {
        try {
            return this.bill.getInt("Id_1");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
