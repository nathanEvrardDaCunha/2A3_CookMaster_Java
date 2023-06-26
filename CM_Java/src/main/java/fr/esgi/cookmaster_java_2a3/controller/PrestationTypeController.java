package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PrestationTypeController extends Controller{

    @FXML
    private PieChart prestationTypePieChart;

    @FXML
    public void initialize() {

        EquipmentsModel equipmentsModel = new EquipmentsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProvidersModel providersModel = new ProvidersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProductsModel productsModel = new ProductsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        SubscriptionsModel subscriptionsModel = new SubscriptionsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        EventsLocationModel eventsLocationModel = new EventsLocationModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        int numberOfEquipmentsUsed = equipmentsModel.getNumberOfEquipmentsUsed();
        int numberOfProvidersUsed = providersModel.getNumberOfProvidersUsed();
        int numberOfProductsBought = productsModel.getNumberOfProductsUsed();
        int numberOfSubscriptionsUsed = subscriptionsModel.getNumberOfSubscriptionsUsed();
        int numberOfEventsLocationUsed = eventsLocationModel.getNumberOfEventsLocationUsed();

        PieChart.Data slice1 = new PieChart.Data("Equipments", numberOfEquipmentsUsed);
        PieChart.Data slice2 = new PieChart.Data("Providers", numberOfProvidersUsed);
        PieChart.Data slice3 = new PieChart.Data("Products", numberOfProductsBought);
        PieChart.Data slice4 = new PieChart.Data("Subscriptions", numberOfSubscriptionsUsed);
        PieChart.Data slice5 = new PieChart.Data("Events Location", numberOfEventsLocationUsed);

        prestationTypePieChart.getData().add(slice1);
        prestationTypePieChart.getData().add(slice2);
        prestationTypePieChart.getData().add(slice3);
        prestationTypePieChart.getData().add(slice4);
        prestationTypePieChart.getData().add(slice5);

        equipmentsModel.close();
        providersModel.close();
        productsModel.close();
        subscriptionsModel.close();
        eventsLocationModel.close();
    }

}