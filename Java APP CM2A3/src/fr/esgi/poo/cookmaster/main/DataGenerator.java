package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

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
        DataRandomizer dataRandomizer = new DataRandomizer();
        DataTemplateValues dataTemplateValues = new DataTemplateValues();

        for (int i = 1; i <= 5; i++) {
            generateSubscriptions(i, dataRandomizer);
            generateEvents(i, dataRandomizer);
            generateProducts(i, dataRandomizer);
            generateProviders(i, dataRandomizer);
            generateEventLocations(i, dataRandomizer);
            generateUsers(i, dataRandomizer);
            generateEquipments(i, dataRandomizer);
            generateBills(i, dataRandomizer);
            generatePublications(i, dataRandomizer);
            generateRegister(i);
            generateOrganise(i);
            generateRent(i);
            generateBuy(i);
            generateGenerate(i);
            generatePrint(i);
        }
    }



    private void generateEvents(int i, DataRandomizer randomizer, DataTemplateValues dataTemplateValues) {

        //Si le state = 0 alors l'event pas encore commencé (voir date)
        //Si le state = 1 alors l'event en cours (voir date)
        //Si le state = 2 alors l'event terminé (voir date)

        //Un evenement possède un titre est une description qui est rattaché a ce titre
        //Un evenement possède un type qui lui est rattaché

        int eventState = randomizer.generateRandomInt();
        int eventType = randomizer.generateRandomInt();
        //String eventStartingDate = randomizer.generateRandomStartingDates(2020, 2023);
        //String eventEndingDate = randomizer.generateRandomEndingDates(eventStartingDate, 1, 7);
        //String eventTitle = randomizer.generateRandomPickInArray(dataTemplateValues.eventTitlesArray);
        //String eventDescription = randomizer.generateRandomPickInArray(dataTemplateValues.eventDescriptionsArray);

        String sql = "INSERT INTO EVENTS(Type, Title, Description, State, Starting_date, Ending_date) VALUES " +
                "(" + eventType + ", "
                + eventTitle + ", "
                + eventDescription + ", "
                + eventState + ", "
                + eventStartingDate + ", "
                + eventEndingDate + "')";

        EventsModel eventsModel = new EventsModel(dbName, userName, password);
        eventsModel.executeUpdate(sql);
    }

    private void generateProviders(int i, DataRandomizer randomizer, DataTemplateValues dataTemplateValues) {

        //Prévoir le coup si un produit a le même type, le même companie, le même nom, le même prénom


        //Chaque company possède un nom différent et une description qu iest rattaché a ce nom
        //et un cout qui lui est rattaché


        int providerType = randomizer.generateRandomInt();
        //int providerCost = randomizer.generateRandomInt();
        String providerLastname = randomizer.generateRandomPickInArray(dataTemplateValues.lastNamesArray);
        String providerFirstname = randomizer.generateRandomPickInArray(dataTemplateValues.firstNamesArray);
        String providerCompanyName = randomizer.generateRandomPickInArray(dataTemplateValues.providerCompanyNamesArray);
        //String providerDescription = randomizer.generateRandomPickInArray(dataTemplateValues.providerDescriptionsArray);

        //Reprendre le nombre d'event organisé par le provider
        //int providerNumberOfEventsOrganised = random.nextInt(27);

        String sql = "INSERT INTO PROVIDERS(Firstname, Lastname, Type, Provider_compagny_name, Cost, Description, Number_of_events_organised) VALUES " +
                "(" + providerFirstname + ", "
                + providerLastname + ", "
                + providerType + ", "
                + providerCompanyName + ", "
                + providerCost + ", "
                + providerDescription + ", "
                + providerNumberOfEventsOrganised + ")";

        ProvidersModel providersModel = new ProvidersModel(dbName, userName, password);
        providersModel.executeUpdate(sql);
    }

    private void generateEventLocations(int i, DataRandomizer randomizer, DataTemplateValues dataTemplateValues) {

        //Prévoir le coup si un produit a le même adresse, le même code postal

        int eventLocationCost = randomizer.generateRandomInt();
        String eventLocationAddress = randomizer.generateRandomAddresses(dataTemplateValues.streetNamesArray);
        String eventLocationPostalCode = randomizer.generateRandomPostalCodes();

        String sql = "INSERT INTO EVENT_LOCATIONS(Address, Postal_code, Cost) VALUES " +
                "(" + eventLocationAddress + ", "
                + eventLocationPostalCode + ", "
                + eventLocationCost + ")";

        EventsLocationModel eventsLocationModel = new EventsLocationModel(dbName, userName, password);
        eventsLocationModel.executeUpdate(sql);
    }

    private void generateEquipments(int i, DataRandomizer randomizer, DataTemplateValues dataTemplateValues) {

        //Prévoir le coup si un produit a le même type, le même titre, la même marque

        int equipmentCost = randomizer.generateRandomInt();
        int equipmentType = randomizer.generateRandomInt();
        String equipmentTitle = randomizer.generateRandomPickInArray(dataTemplateValues.equipmentTitlesArray);
        String equipmentBrand = randomizer.generateRandomPickInArray(dataTemplateValues.equipmentBrandsArray);

        String sql = "INSERT INTO EQUIPMENTS(Title, Type, Cost, Brand, Id_1) VALUES " +
                "(" + equipmentTitle + ", "
                + equipmentType + ", "
                + equipmentCost + ", "
                + equipmentBrand + ", "
                + i + ")";

        EquipmentsModel equipmentsModel = new EquipmentsModel(dbName, userName, password);
        equipmentsModel.executeUpdate(sql);
    }

    private void generateSubscriptions(int i, DataRandomizer randomizer, DataTemplateValues dataTemplateValues) {

        //Faire condition si Type abonnement X alors pas de cout / cout approprié et pas random
        //Pareil pour subscriptionTitle et subscriptionDescription

        int subscriptionFrequencyOfCost = randomizer.generateRandomPickInArray(dataTemplateValues.subscriptionFrequencyOfCostsArray);
        int subscriptionType = randomizer.generateRandomPickInArray(dataTemplateValues.subscriptionTypesArray);
        //int subscriptionCost = randomizer.generateRandomPickInArray(dataTemplateValues.subscriptionCostsArray);
        //String subscriptionTitle = randomizer.generateRandomPickInArray(dataTemplateValues.subscriptionTitlesArray);
        //String subscriptionDescription = randomizer.generateRandomPickInArray(dataTemplateValues.subscriptionDescriptionsArray);

        String sql = "INSERT INTO SUBSCRIPTIONS(Title, Cost, Type, Description, Frequency_of_cost) VALUES " +
                "(" + subscriptionTitle + ", "
                + subscriptionCost + ", "
                + subscriptionType + ", "
                + subscriptionDescription + ", "
                + subscriptionFrequencyOfCost + ")";

        SubscriptionsModel subscriptionsModel = new SubscriptionsModel(dbName, userName, password);
        subscriptionsModel.executeUpdate(sql);
    }

    private void generateProducts(int i, DataRandomizer randomizer, DataTemplateValues dataTemplateValues) {

        //Prévoir le coup si un produit a la même catégorie, le même titre, la même description
        //Description générique pour tout les produits

        int productCategory = randomizer.generateRandomInt();
        int productCost = randomizer.generateRandomInt();
        int productStockState = randomizer.generateRandomInt();
        String productTitle = randomizer.generateRandomPickInArray(dataTemplateValues.productTitles);
        String productDescription = randomizer.generateRandomPickInArray(dataTemplateValues.productDescriptions);

        String sql = "INSERT INTO PRODUCTS(Title, Cost, Description, Category, Stock_state) VALUES " +
                "(" + productTitle + ", "
                + productCost + ", "
                + productDescription + ", "
                + productCategory + ", "
                + productStockState + ")";

        ProductsModel productsModel = new ProductsModel(dbName, userName, password);
        productsModel.executeUpdate(sql);
    }

    private void generateBills(int i, DataRandomizer randomizer) {

        //Reprendre le cout d'un événement, d'un abonnement ou d'un produit
        //int billCostOfPurchase = randomizer.generateRandomInt();

        //Faire date le jour de fin d'un événements et après date inscription utilisateur
        //Faire date début d'abonnement et après inscription utilisateur
        //String billPurchaseDate = randomizer.generateRandomStartingDates(2020, 2023);

        //Reprendre l'évenement, l'abonnement ou le produit auquel il est associé
        //String billPurchaseContent = randomizer.generateRandomPickInArray(dataTemplateValues.billPurchaseContents);

        //Reprendre l'utilisateur qui a acheté l'évenement, l'abonnement ou le produit
        //String billFirstnameOfBuyer = randomizer.generateRandomPickInArray(dataTemplateValues.billFirstnamesOfBuyer);
        //String billLastnameOfBuyer = randomizer.generateRandomPickInArray(dataTemplateValues.billLastnamesOfBuyer);
        //String billPostalCodeOfBuyer = randomizer.generateRandomPostalCodes();
        //String billAddressOfBuyer = randomizer.generateRandomAddresses();


        String sql = "INSERT INTO BILLS(Purchase_content, Purchase_date, Firstname_of_buyer, Lastname_of_buyer, Postal_code_of_buyer, Address_of_buyer, Cost_of_purchase, Id_1) VALUES " +
                "(" + billPurchaseContent + ", "
                + billPurchaseDate + ", "
                + billFirstnameOfBuyer + ", "
                + billLastnameOfBuyer + ", "
                + billPostalCodeOfBuyer + ", "
                + billAddressOfBuyer + ", "
                + billCostOfPurchase + ", "
                + i + ")";

        BillsModel billsModel = new BillsModel(dbName, userName, password);
        billsModel.executeUpdate(sql);
    }

    private void generateUsers(int i, DataRandomizer randomizer) {

        //PREVOIR que nom utilisateur, prénom utilisateur, date de naissance utilisateur
        //Ou nom utilisateur, nrénom utilisateur, code postal utlisateur, adresse utilisateur

        int userSex = randomizer.generateRandomInt();
        int userRole = randomizer.generateRandomInt();
        int userFidelityPoint = randomizer.generateRandomInt();

        //PARTIE A REVOIR POUR ETRE SUR QUE CHAQUE CHOSE EST COHERENTE
        String userLastPurchaseDate = randomizer.generateRandomEndingDates(userRegistrationDate, 2, 90);
        String userStartingSubscriptionDate = randomizer.generateRandomEndingDates(userRegistrationDate, 2, 90);
        String userEndingSubscriptionDate = randomizer.generateRandomEndingDates(userStartingSubscriptionDate, 30, 365);
        String userBirthday = randomizer.generateRandomStartingDates(1960, 2002);
        String userRegistrationDate = randomizer.generateRandomEndingDates(userBirthday, (365 * 18), (365 * 22));

        String userFirstname = randomizer.generateRandomPickInArray(dataTemplateValues.userFirstnames());
        String userLastname = randomizer.generateRandomPickInArray(dataTemplateValues.userLastnames());

        //Prévoir que le code postal et l'adresse de l'utilisateur ne soit pas similaire avec l'adresse et le code postal d'un événement
        //String userPostalCode = randomizer.generateRandomPostalCodes();
        //String userAddress = randomizer.generateRandomAddresses();






        String sql = "INSERT INTO USERS(Address, Firstname, Lastname, Postal_code, Role, Registration_date, Fidelity_point, Last_purchase_date, Ending_subscription_date, Starting_subscription_date, Sex, Birthday, Id_1) VALUES " +
                "('" + userAddress + ", "
                + userFirstname + ", "
                + userLastname + ", "
                + userPostalCode + ", "
                + userRole + ", "
                + userRegistrationDate + ", "
                + userFidelityPoint + ", "
                + userLastPurchaseDate + ", "
                + userEndingSubscriptionDate + ", "
                + userStartingSubscriptionDate + ", "
                + userSex + ", "
                + userBirthday + ", "
                + i + ")";

        UsersModel usersModel = new UsersModel(dbName, userName, password);
        usersModel.executeUpdate(sql);

    }

    private void generatePublications(int i, DataRandomizer randomizer) {


        //PARTIE A REVOIR POUR ETRE SUR QUE CHAQUE CHOSE EST COHERENTE
        //Etre sûr que l'utilisateur qui publie est un utilisateur qui existe (donc vérifier date d'inscription)
        //String publicationDate = randomizer.generateRandomStartingDates(2022, 2023);

        String publicationTitle = randomizer.generateRandomPickInArray(dataTemplateValues.publicationTitles);
        String publicationDescription = randomizer.generateRandomPickInArray(dataTemplateValues.publicationDescriptions);

        String sql = "INSERT INTO PUBLICATIONS(Title, Publication_date, Description, Id_1) VALUES " +
                "(" + publicationTitle + ", "
                + publicationDate + ", "
                + publicationDescription + ", "
                + i + ")";

        PublicationsModel publicationsModel = new PublicationsModel(dbName, userName, password);
        publicationsModel.executeUpdate(sql);
    }


    /**
     *
     * TEST MANUEL DE L'AJOUT DE CES FONCTION AVEC GITCOPILOT
     * @param i
     *
     */


    private void generateRegister(int i) {
        String sql = "INSERT INTO Register(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        RegisterModel registerModel = new RegisterModel(dbName, userName, password);
        registerModel.executeUpdate(sql);
    }

    private void generateOrganise(int i) {
        String sql = "INSERT INTO Organise(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        OrganiseModel organiseModel = new OrganiseModel(dbName, userName, password);
        organiseModel.executeUpdate(sql);
    }

    private void generateRent(int i) {
        String sql = "INSERT INTO Rent(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        RentModel rentModel = new RentModel(dbName, userName, password);
        rentModel.executeUpdate(sql);
    }

    private void generateBuy(int i) {
        String sql = "INSERT INTO Buy(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        BuyModel buyModel = new BuyModel(dbName, userName, password);
        buyModel.executeUpdate(sql);
    }

    private void generateGenerate(int i) {
        String sql = "INSERT INTO Generate(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        GenerateModel generateModel = new GenerateModel(dbName, userName, password);
        generateModel.executeUpdate(sql);
    }

    private void generatePrint(int i) {
        String sql = "INSERT INTO Print(Id, Id_1) VALUES " +
                "(" + i + ", " + i + ")";

        PrintModel printModel = new PrintModel(dbName, userName, password);
        printModel.executeUpdate(sql);
    }
}
