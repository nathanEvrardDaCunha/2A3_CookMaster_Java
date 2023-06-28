package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class PrestationTopController extends Controller{

    @FXML
    private BarChart prestationTopBarChart;

    @FXML
    public void initialize() {
        UsersModel usersModel = new UsersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProvidersModel providersModel = new ProvidersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        EventsLocationModel eventsLocationModel = new EventsLocationModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        EquipmentsModel equipmentsModel = new EquipmentsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProductsModel productsModel = new ProductsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        int totalNumberOfInscriptionForProvider = providersModel.getNumberOfProvidersUsed();
        int totalNumberOfInscriptionForSubscription = usersModel.getTotalNumberOfInscriptionForSubscription();
        int totalNumberOfInscriptionForEvent = usersModel.getTotalNumberOfInscriptionForEvent();
        int totalNumberOfCommandForUser = productsModel.getNumberOfProductsUsed();
        int totalNumberOfInscriptionForEquipment = equipmentsModel.getNumberOfEquipmentsUsed();
        int totalNumberOfInscriptionForLocation = eventsLocationModel.getNumberOfEventsLocationUsed();

        series.getData().add(new XYChart.Data<>("Providers", totalNumberOfInscriptionForProvider));
        series.getData().add(new XYChart.Data<>("Subscription", totalNumberOfInscriptionForSubscription));
        series.getData().add(new XYChart.Data<>("Event", totalNumberOfInscriptionForEvent));
        series.getData().add(new XYChart.Data<>("Command", totalNumberOfCommandForUser));
        series.getData().add(new XYChart.Data<>("Equipment", totalNumberOfInscriptionForEquipment));
        series.getData().add(new XYChart.Data<>("Location", totalNumberOfInscriptionForLocation));

        prestationTopBarChart.getData().add(series);

        usersModel.close();
        providersModel.close();
        eventsLocationModel.close();
        equipmentsModel.close();
        productsModel.close();
    }


}