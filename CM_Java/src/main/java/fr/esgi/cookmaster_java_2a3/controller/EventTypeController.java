package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.EventsModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class EventTypeController extends Controller{

    @FXML
    private PieChart eventTypePieChart;

    @FXML
    public void initialize() {

        EventsModel eventsModel = new EventsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        int numberOfEventOfType1 = eventsModel.getNumberOfEventsOfType(1);
        int numberOfEventOfType2 = eventsModel.getNumberOfEventsOfType(2);
        int numberOfEventOfType0 = eventsModel.getNumberOfEventsOfType(0);

        PieChart.Data slice1 = new PieChart.Data("Atelier", numberOfEventOfType0);
        PieChart.Data slice2 = new PieChart.Data("Degustation", numberOfEventOfType1);
        PieChart.Data slice3 = new PieChart.Data("Formation", numberOfEventOfType2);

        eventTypePieChart.getData().add(slice1);
        eventTypePieChart.getData().add(slice2);
        eventTypePieChart.getData().add(slice3);

        eventsModel.close();
    }

}