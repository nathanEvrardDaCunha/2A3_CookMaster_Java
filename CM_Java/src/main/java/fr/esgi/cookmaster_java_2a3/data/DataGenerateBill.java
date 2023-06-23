package fr.esgi.cookmaster_java_2a3.data;

import fr.esgi.cookmaster_java_2a3.model.BillsModel;
import fr.esgi.cookmaster_java_2a3.model.EventsModel;
import fr.esgi.cookmaster_java_2a3.model.ProductsModel;
import fr.esgi.cookmaster_java_2a3.model.SubscriptionsModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateBill {

    private static final int BILL_MINIMUM_COST = 2;
    private static final int BILL_MAXIMUM_COST = 2000;
    private static final String BILL_STARTING_DATE = "2022-01-01";
    private static final String BILL_ENDING_DATE = "2023-06-30";
    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateBill(String dbName, String userName, String password){
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateBills() throws SQLException{

        //Reprendre le cout d'un événement, d'un abonnement ou d'un produit
        int billCostOfPurchase = CommonDataGenerator.selectRandomInt(BILL_MINIMUM_COST, BILL_MAXIMUM_COST);

        //Faire date le jour de fin d'un événements et après date inscription utilisateur
        //Faire date début d'abonnement et après inscription utilisateur
        String billPurchaseDate = CommonDataGenerator.selectRandomDate(BILL_STARTING_DATE, BILL_ENDING_DATE);

        //Reprendre l'évènement, l'abonnement ou le produit auquel il est associé
        String billPurchaseContent = selectPurchaseContent();

        //Reprendre l'utilisateur qui a acheté l'évènement, l'abonnement ou le produit
        String billFirstnameOfBuyer = CommonDataGenerator.selectRandomFirstname();
        String billLastnameOfBuyer = CommonDataGenerator.selectRandomLastname();
        String billPostalCodeOfBuyer = CommonDataGenerator.selectRandomPostalCode();
        String billAddressOfBuyer = CommonDataGenerator.selectRandomAdress();


        String sql = "INSERT INTO BILLS(Purchase_content, Purchase_date, Firstname_of_buyer, Lastname_of_buyer, Postal_code_of_buyer, Address_of_buyer, Cost_of_purchase, Event_Id, Subscription_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        BillsModel billsModel = new BillsModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = billsModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, billPurchaseContent);
            pstmt.setString(2, billPurchaseDate);
            pstmt.setString(3, billFirstnameOfBuyer);
            pstmt.setString(4, billLastnameOfBuyer);
            pstmt.setString(5, billPostalCodeOfBuyer);
            pstmt.setString(6, billAddressOfBuyer);
            pstmt.setInt(7, billCostOfPurchase);

            int randomEventId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENTS);
            int randomSubscriptionId = CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_SUBSCRIPTIONS);

            pstmt.setInt(8, randomEventId);
            pstmt.setInt(9, randomSubscriptionId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            billsModel.close();
        }
    }

    private String selectPurchaseContent() {
        int randomPurchaseContent = CommonDataGenerator.selectRandomInt(1, 3);
        switch (randomPurchaseContent) {
            case 1:
                EventsModel eventsModel = new EventsModel(dbName, userName, password);
                eventsModel.loadEventById(CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_EVENTS));
                String eventName = eventsModel.getEventTitle();
                eventsModel.close();
                return eventName;
            case 2:
                SubscriptionsModel subscriptionsModel = new SubscriptionsModel(dbName, userName, password);
                subscriptionsModel.loadSubscriptionById(CommonDataGenerator.selectRandomInt(1, DataGenerator.NUMBER_OF_SUBSCRIPTIONS));
                String subscriptionName = subscriptionsModel.getSubscriptionTitle();
                subscriptionsModel.close();
                return subscriptionName;
            case 3:
                DataGenerateProduct dataGenerateProduct = new DataGenerateProduct(dbName, userName, password);
                String productName = dataGenerateProduct.selectRandomTitle(CommonDataGenerator.selectRandomInt(DataGenerateProduct.PRODUCT_MIN_CATEGORY, DataGenerateProduct.PRODUCT_MAX_CATEGORY));
                return productName;
        }
        return null;
    }
}

