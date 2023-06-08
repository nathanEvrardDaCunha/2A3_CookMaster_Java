package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.*;

public class DataPurger {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataPurger(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }


    public void purgeData() {
        UsersModel usersModel = new UsersModel(dbName, userName, password);
        EventsModel eventsModel = new EventsModel(dbName, userName, password);
        ProvidersModel providersModel = new ProvidersModel(dbName, userName, password);
        EventsLocationModel eventsLocationModel = new EventsLocationModel(dbName, userName, password);
        SubscriptionsModel subscriptionsModel = new SubscriptionsModel(dbName, userName, password);
        ProductsModel productsModel = new ProductsModel(dbName, userName, password);
        EquipmentsModel equipmentsModel = new EquipmentsModel(dbName, userName, password);
        BillsModel billsModel = new BillsModel(dbName, userName, password);
        PublicationsModel publicationsModel = new PublicationsModel(dbName, userName, password);
        RentModel rentModel = new RentModel(dbName, userName, password);
        OrganiseModel organiseModel = new OrganiseModel(dbName, userName, password);
        BuyModel buyModel = new BuyModel(dbName, userName, password);
        GenerateModel generateModel = new GenerateModel(dbName, userName, password);
        PrintModel printModel = new PrintModel(dbName, userName, password);  // assuming you have this model
        RegisterModel registerModel = new RegisterModel(dbName, userName, password);  // assuming you have this model

        generateModel.purge();
        buyModel.purge();
        rentModel.purge();
        organiseModel.purge();
        printModel.purge();  // purge print before bills and products
        registerModel.purge();  // purge register before users
        publicationsModel.purge();  // purge publications before users
        billsModel.purge(); // purge bills before events
        equipmentsModel.purge(); // purge equipments before events
        usersModel.purge();  // purge users before subscriptions
        subscriptionsModel.purge();
        productsModel.purge();
        eventsLocationModel.purge();
        providersModel.purge();
        eventsModel.purge();
    }
}
