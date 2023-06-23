package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PrestationCAController extends Controller{

    @FXML
    private PieChart prestationCAPieChart;

    @FXML
    public void initialize() {

        EventsLocationModel eventsLocationModel = new EventsLocationModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        EquipmentsModel equipmentsModel = new EquipmentsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProvidersModel providersModel = new ProvidersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProductsModel productsModel = new ProductsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        SubscriptionsModel subscriptionsModel = new SubscriptionsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        int caOfEventLocation = eventsLocationModel.getCAOfEventLocation();
        int caOfEquipment = equipmentsModel.getCAOfEquipment();
        int caOfProvider = providersModel.getCAOfProvider();
        int caOfProduct = productsModel.getCAOfProduct();
        int caOfSubscription = subscriptionsModel.getCAOfSubscription();

        PieChart.Data slice1 = new PieChart.Data("Location", caOfEventLocation);
        PieChart.Data slice2 = new PieChart.Data("Equipement", caOfEquipment);
        PieChart.Data slice3 = new PieChart.Data("Fournisseur", caOfProvider);
        PieChart.Data slice4 = new PieChart.Data("Produit", caOfProduct);
        PieChart.Data slice5 = new PieChart.Data("Abonnement", caOfSubscription);

        prestationCAPieChart.getData().add(slice1);
        prestationCAPieChart.getData().add(slice2);
        prestationCAPieChart.getData().add(slice3);
        prestationCAPieChart.getData().add(slice4);
        prestationCAPieChart.getData().add(slice5);

        eventsLocationModel.close();
        equipmentsModel.close();
        providersModel.close();
        productsModel.close();
        subscriptionsModel.close();
    }

}