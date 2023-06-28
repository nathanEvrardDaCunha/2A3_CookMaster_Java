package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PrestationAverageController extends Controller{

    @FXML
    private PieChart prestationAveragePieChart;

    @FXML
    public void initialize() {

        EventsLocationModel eventsLocationModel = new EventsLocationModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        ProvidersModel providersModel = new ProvidersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        EquipmentsModel equipmentsModel = new EquipmentsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        int repartitionOfLocation = eventsLocationModel.getNumberOfEventsLocationUsed();
        int repartitionOfProviders = providersModel.getNumberOfProvidersUsed();
        int repartitionOfEquipments = equipmentsModel.getNumberOfEquipmentsUsed();

        PieChart.Data slice1 = new PieChart.Data("Location", repartitionOfLocation);
        PieChart.Data slice2 = new PieChart.Data("Providers", repartitionOfProviders);
        PieChart.Data slice3 = new PieChart.Data("Equipments", repartitionOfEquipments);

        prestationAveragePieChart.getData().add(slice1);
        prestationAveragePieChart.getData().add(slice2);
        prestationAveragePieChart.getData().add(slice3);

        eventsLocationModel.close();
        providersModel.close();
        equipmentsModel.close();
    }

}