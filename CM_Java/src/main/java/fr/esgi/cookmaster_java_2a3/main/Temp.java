package fr.esgi.cookmaster_java_2a3.main;

public class Temp {





        /*SubscriptionsModel model = new SubscriptionsModel(DB_NAME, USER_NAME, PASSWORD);

        try {
            ResultSet resultSet = model.executeQuery("SELECT * FROM subscriptions");
            while (resultSet.next()) {
                model.loadSubscriptionById(resultSet.getInt("Id"));
                System.out.println("ID: " + model.getSubscriptionId() + ", Title: " + model.getSubscriptionTitle() +
                        ", Cost: " + model.getSubscriptionCost() + ", Type: " + model.getSubscriptionType());
            }
            model.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*EventsModel eventsModel = new EventsModel(DB_NAME, USER_NAME, PASSWORD);

        try {
            ResultSet resultSet = eventsModel.executeQuery("SELECT * FROM events");
            while (resultSet.next()) {
                eventsModel.loadEventById(resultSet.getInt("Id"));
                System.out.println("ID: " + eventsModel.getEventId() + ", Title: " + eventsModel.getEventTitle() +
                        ", Type: " + eventsModel.getEventType() +
                        ", State: " + eventsModel.getEventState() + ", Starting date: " + eventsModel.getEventStartingDate() +
                        ", Ending date: " + eventsModel.getEventEndingDate());
            }
            eventsModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*
        ProvidersModel providersModel = new ProvidersModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = providersModel.executeQuery("SELECT * FROM providers");
            while (resultSet.next()) {
                providersModel.loadProviderById(resultSet.getInt("Id"));
                System.out.println("ID: " + providersModel.getProviderId() + ", Firstname: " + providersModel.getProviderFirstname() + ", Lastname: " + providersModel.getProviderLastname() +
                        ", CompagnyName: " + providersModel.getProviderCompanyName());
            }
            providersModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        /*EventsLocationModel eventsLocationModel = new EventsLocationModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = eventsLocationModel.executeQuery("SELECT * FROM event_locations");
            while (resultSet.next()) {
                eventsLocationModel.loadEventLocationById(resultSet.getInt("Id"));
                System.out.println("ID: " + eventsLocationModel.getEventLocationId() +
                        ", Address: " + eventsLocationModel.getEventLocationAddress() + ", City: " + eventsLocationModel.getEventLocationCity() +
                        ", PostalCode: " + eventsLocationModel.getEventLocationPostalCode());
            }
            eventsLocationModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*UsersModel usersModel = new UsersModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = usersModel.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                usersModel.loadUserById(resultSet.getInt("Id"));
                System.out.println("ID: " + usersModel.getUserId() +
                        ", Firstname: " + usersModel.getUserFirstname() + ", Lastname: " + usersModel.getUserLastname() +
                        ", Email: " + usersModel.getUserEmail() + ", Password: " + usersModel.getUserPassword() +
                        ", Role: " + usersModel.getUserRole());
            }
            usersModel.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*EquipmentsModel equipmentsModel = new EquipmentsModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = equipmentsModel.executeQuery("SELECT * FROM equipments");
            while (resultSet.next()) {
                equipmentsModel.loadEquipmentById(resultSet.getInt("Id"));
                System.out.println("ID: " + equipmentsModel.getEquipmentId() +
                        ", Title: " + equipmentsModel.getEquipmentTitle() + ", Type: " + equipmentsModel.getEquipmentType() +
                        ", Brand: " + equipmentsModel.getEquipmentBrand());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*ProductsModel productsModel = new ProductsModel(DB_NAME, USER_NAME, PASSWORD);
        try {
            ResultSet resultSet = productsModel.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                productsModel.loadProductById(resultSet.getInt("Id"));
                System.out.println("ID: " + productsModel.getProductId() +
                        ", Title: " + productsModel.getProductTitle() + ", Type: " + productsModel.getProductCategory() +
                        ", Price: " + productsModel.getProductCost() +
                        ", Quantity: " + productsModel.getProductStockState());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


}
