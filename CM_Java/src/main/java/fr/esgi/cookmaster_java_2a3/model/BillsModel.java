package fr.esgi.cookmaster_java_2a3.model;

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

    private int getNumberOfSales(String startDate, String endDate) {
        String querySales = "SELECT COUNT(DISTINCT b.Id) AS count FROM BILLS b LEFT JOIN Print p ON b.Id = p.Bill_Id WHERE b.Purchase_date BETWEEN ? AND ?";
        String querySubscriptions = "SELECT COUNT(DISTINCT b.Id) AS count FROM BILLS b WHERE b.Subscription_Id IS NOT NULL AND b.Purchase_date BETWEEN ? AND ?";
        String queryEvents = "SELECT COUNT(DISTINCT b.Id) AS count FROM BILLS b WHERE b.Event_Id IS NOT NULL AND b.Purchase_date BETWEEN ? AND ?";

        try {
            PreparedStatement stmtSales = getConnection().prepareStatement(querySales);
            stmtSales.setString(1, startDate);
            stmtSales.setString(2, endDate);

            PreparedStatement stmtSubscriptions = getConnection().prepareStatement(querySubscriptions);
            stmtSubscriptions.setString(1, startDate);
            stmtSubscriptions.setString(2, endDate);

            PreparedStatement stmtEvents = getConnection().prepareStatement(queryEvents);
            stmtEvents.setString(1, startDate);
            stmtEvents.setString(2, endDate);

            ResultSet resultSetSales = stmtSales.executeQuery();
            ResultSet resultSetSubscriptions = stmtSubscriptions.executeQuery();
            ResultSet resultSetEvents = stmtEvents.executeQuery();

            int sales = 0;
            int subscriptions = 0;
            int events = 0;

            if (resultSetSales.next()) {
                sales = resultSetSales.getInt("count");
            }
            if (resultSetSubscriptions.next()) {
                subscriptions = resultSetSubscriptions.getInt("count");
            }
            if (resultSetEvents.next()) {
                events = resultSetEvents.getInt("count");
            }

            return sales + subscriptions + events;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public String getTableName() {
        return "bills";
    }

    public int getNumberOfSalesFirstTrimester2022() {
        return getNumberOfSales("2022-01-01", "2022-03-31");
    }

    public int getNumberOfSalesSecondTrimester2022() {
        return getNumberOfSales("2022-04-01", "2022-06-30");
    }

    public int getNumberOfSalesThirdTrimester2022() {
        return getNumberOfSales("2022-07-01", "2022-09-30");
    }

    public int getNumberOfSalesFourthTrimester2022() {
        return getNumberOfSales("2022-10-01", "2022-12-31");
    }

    public int getNumberOfSalesFirstTrimester2023() {
        return getNumberOfSales("2023-01-01", "2023-03-31");
    }

    public int getNumberOfSalesSecondTrimester2023() {
        return getNumberOfSales("2023-04-01", "2023-06-30");
    }
}
