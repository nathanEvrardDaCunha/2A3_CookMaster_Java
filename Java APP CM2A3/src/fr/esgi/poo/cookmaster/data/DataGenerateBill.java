package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.BillsModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateBill {

    private static final int BILL_TEMP_MINIMUM_COST = 2;
    private static final int BILL_TEMP_MAXIMUM_COST = 2000;
    private static final String BILL_TEMP_STARTING_DATE = "1980-01-01";
    private static final String BILL_TEMP_CONTENT = "Event";

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateBill(String dbName, String userName, String password){
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateBills(int i) throws SQLException{

        //Reprendre le cout d'un événement, d'un abonnement ou d'un produit
        int billCostOfPurchase = CommonDataGenerator.selectRandomInt(BILL_TEMP_MINIMUM_COST, BILL_TEMP_MAXIMUM_COST);

        //Faire date le jour de fin d'un événements et après date inscription utilisateur
        //Faire date début d'abonnement et après inscription utilisateur
        String billPurchaseDate = selectTempPurchaseDate();

        //Reprendre l'évènement, l'abonnement ou le produit auquel il est associé
        String billPurchaseContent = selectTempPurchaseContent();

        //Reprendre l'utilisateur qui a acheté l'évènement, l'abonnement ou le produit
        String billFirstnameOfBuyer = CommonDataGenerator.selectRandomFirstname();
        String billLastnameOfBuyer = CommonDataGenerator.selectRandomLastname();
        String billPostalCodeOfBuyer = CommonDataGenerator.selectRandomPostalCode();
        String billAddressOfBuyer = CommonDataGenerator.selectRandomAdress();


        String sql = "INSERT INTO BILLS(Purchase_content, Purchase_date, Firstname_of_buyer, Lastname_of_buyer, Postal_code_of_buyer, Address_of_buyer, Cost_of_purchase, Id_1) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            pstmt.setInt(8, i);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String selectTempPurchaseDate() {
        return BILL_TEMP_STARTING_DATE;
    }

    private String selectTempPurchaseContent() {
        return BILL_TEMP_CONTENT;
    }
}
