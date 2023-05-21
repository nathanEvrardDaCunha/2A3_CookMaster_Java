package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class DataGenerator {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerator(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateDataForDB() {
        for (int i = 1; i <= 5; i++) {
            generateSubscriptions(i);
            generateEvents(i);
            generateProducts(i);
            generateProviders(i);
            generateEventLocations(i);
            generateUsers(i);
            generateEquipments(i);
            generateBills(i);
            generatePublications(i);
            generateRegister(i);
            generateOrganise(i);
            generateRent(i);
            generateBuy(i);
            generateGenerate(i);
            generatePrint(i);
        }
    }



    private void generateEvents(int i) {
        EventsModel eventsModel = new EventsModel(dbName, userName, password);

        Calendar calendar = Calendar.getInstance();

        String startingDate = new Date(calendar.getTimeInMillis()).toString();

        calendar.add(Calendar.DATE, 5);

        String endingDate = new Date(calendar.getTimeInMillis()).toString();

        String sql = "INSERT INTO EVENTS(Type, Title, Description, State, Starting_date, Ending_date) VALUES " +
                "(" + (i % 2 + 1) + ", 'Event" + i + "', 'This is event " + i + "', " + (i % 2) + ", '" +
                startingDate + "', '" + endingDate + "')";

        eventsModel.executeUpdate(sql);
    }

    private void generateProviders(int i) {
        ProvidersModel providersModel = new ProvidersModel(dbName, userName, password);
        String sql = "INSERT INTO PROVIDERS(Firstname, Lastname, Type, Provider_compagny_name, Cost, Description, Number_of_events_organised) VALUES " +
                "('First" + i + "', 'Last" + i + "', " + (i % 3 + 1) + ", 'Company" + i + "', " + (i * 100) + ", 'This is provider " + i + "', " + i + ")";
        providersModel.executeUpdate(sql);
    }

    private void generateEventLocations(int i) {
        EventsLocationModel eventsLocationModel = new EventsLocationModel(dbName, userName, password);
        String sql = "INSERT INTO EVENT_LOCATIONS(Address, Postal_code, Cost) VALUES " +
                "('Address" + i + "', '1111" + i + "', " + (i * 50) + ")";
        eventsLocationModel.executeUpdate(sql);
    }

    private void generateEquipments(int i) {
        EquipmentsModel equipmentsModel = new EquipmentsModel(dbName, userName, password);
        String sql = "INSERT INTO EQUIPMENTS(Title, Type, Cost, Brand, Id_1) VALUES " +
                "('Equipment" + i + "', " + (i % 3 + 1) + ", " + (i * 10) + ", 'Brand" + i + "', " + i + ")";
        equipmentsModel.executeUpdate(sql);
    }

    private void generateSubscriptions(int i) {
        SubscriptionsModel subscriptionsModel = new SubscriptionsModel(dbName, userName, password);
        String sql = "INSERT INTO SUBSCRIPTIONS(Title, Cost, Type, Description, Frequency_of_cost) VALUES " +
                "('Subscription" + i + "', " + (i * 50) + ", " + (i % 3 + 1) + ", 'This is subscription " + i + "', " + (i % 2 + 1) + ")";
        subscriptionsModel.executeUpdate(sql);
    }

    private void generateProducts(int i) {
        ProductsModel productsModel = new ProductsModel(dbName, userName, password);
        String sql = "INSERT INTO PRODUCTS(Title, Cost, Description, Category, Stock_state) VALUES " +
                "('Product" + i + "', " + (i * 10) + ", 'This is product " + i + "', " + (i % 3 + 1) + ", " + (i % 2) + ")";
        productsModel.executeUpdate(sql);
    }

    private void generateBills(int i) {
        BillsModel billsModel = new BillsModel(dbName, userName, password);
        String sql = "INSERT INTO BILLS(Purchase_content, Purchase_date, Firstname_of_buyer, Lastname_of_buyer, Postal_code_of_buyer, Address_of_buyer, Cost_of_purchase, Id_1) VALUES " +
                "('Content" + i + "', '" + new Timestamp(System.currentTimeMillis()) + "', 'Firstname" + i + "', 'Lastname" + i + "', '1111" + i + "', 'Address" + i + "', " + (i * 100) + ", " + i + ")";
        billsModel.executeUpdate(sql);
    }

    private void generateUsers(int i) {
        UsersModel usersModel = new UsersModel(dbName, userName, password);

        Calendar calendar = Calendar.getInstance();

        String registrationDate = new Date(calendar.getTimeInMillis()).toString();
        String lastPurchaseDate = new Date(calendar.getTimeInMillis()).toString();
        String startingSubscriptionDate = new Date(calendar.getTimeInMillis()).toString();
        String birthday = new Date(calendar.getTimeInMillis()).toString();

        calendar.add(Calendar.MONTH, 1);

        String endingSubscriptionDate = new Date(calendar.getTimeInMillis()).toString();

        String sql = "INSERT INTO USERS(Address, Firstname, Lastname, Postal_code, Role, Registration_date, Fidelity_point, Last_purchase_date, Ending_subscription_date, Starting_subscription_date, Sex, Birthday, Id_1) VALUES " +
                "('Address" + i + "', 'Firstname" + i + "', 'Lastname" + i + "', '1111" + i + "', " + (i % 3 + 1) + ", '" +
                registrationDate + "', " + (i * 10) + ", '" + lastPurchaseDate + "', '" +
                endingSubscriptionDate + "', '" + startingSubscriptionDate + "', " + (i % 2) + ", '" +
                birthday + "', " + i + ")";

        usersModel.executeUpdate(sql);
    }

    private void generatePublications(int i) {
        PublicationsModel publicationsModel = new PublicationsModel(dbName, userName, password);
        String sql = "INSERT INTO PUBLICATIONS(Title, Publication_date, Description, Id_1) VALUES " +
                "('Publication" + i + "', '" + new Timestamp(System.currentTimeMillis()) + "', 'This is publication " + i + "', " + i + ")";
        publicationsModel.executeUpdate(sql);
    }

    /**
     *
     * TEST MANUEL DE L'AJOUT DE CES FONCTION AVEC GITCOPILOT
     * @param i
     */


    private void generateRegister(int i) {
        RegisterModel registerModel = new RegisterModel(dbName, userName, password);
        String sql = "INSERT INTO Register(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";
        registerModel.executeUpdate(sql);
    }

    private void generateOrganise(int i) {
        OrganiseModel organiseModel = new OrganiseModel(dbName, userName, password);
        String sql = "INSERT INTO Organise(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";
        organiseModel.executeUpdate(sql);
    }

    private void generateRent(int i) {
        RentModel rentModel = new RentModel(dbName, userName, password);
        String sql = "INSERT INTO Rent(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";
        rentModel.executeUpdate(sql);
    }

    private void generateBuy(int i) {
        BuyModel buyModel = new BuyModel(dbName, userName, password);
        String sql = "INSERT INTO Buy(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";
        buyModel.executeUpdate(sql);
    }

    private void generateGenerate(int i) {
        GenerateModel generateModel = new GenerateModel(dbName, userName, password);
        String sql = "INSERT INTO Generate(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";
        generateModel.executeUpdate(sql);
    }

    private void generatePrint(int i) {
        PrintModel printModel = new PrintModel(dbName, userName, password);
        String sql = "INSERT INTO Print(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";
        printModel.executeUpdate(sql);
    }
}
