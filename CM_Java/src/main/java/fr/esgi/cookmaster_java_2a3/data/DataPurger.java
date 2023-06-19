package fr.esgi.cookmaster_java_2a3.data;

import fr.esgi.cookmaster_java_2a3.model.*;

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
        PrintModel printModel = new PrintModel(dbName, userName, password);
        RegisterModel registerModel = new RegisterModel(dbName, userName, password);

        buyModel.purge();
        rentModel.purge();
        organiseModel.purge();
        printModel.purge();
        registerModel.purge();
        publicationsModel.purge();
        billsModel.purge();
        equipmentsModel.purge();
        usersModel.purge();
        subscriptionsModel.purge();
        productsModel.purge();
        eventsLocationModel.purge();
        providersModel.purge();
        eventsModel.purge();

        buyModel.close();
        rentModel.close();
        organiseModel.close();
        printModel.close();
        registerModel.close();
        publicationsModel.close();
        billsModel.close();
        equipmentsModel.close();
        usersModel.close();
        subscriptionsModel.close();
        productsModel.close();
        eventsLocationModel.close();
        providersModel.close();
        eventsModel.close();
    }
}

