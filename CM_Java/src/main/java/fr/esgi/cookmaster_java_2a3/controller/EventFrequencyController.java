package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.BillsModel;
import fr.esgi.cookmaster_java_2a3.model.EventsModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class EventFrequencyController extends Controller{

    @FXML
    private BarChart eventFrequencyBarChart;

    @FXML
    public void initialize() {

        EventsModel eventsModel = new EventsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        int numberOfEventInJanuary2023 = eventsModel.getNumberOfEventsInMonthYear(1, 2023);
        int numberOfEventInFebruary2023 = eventsModel.getNumberOfEventsInMonthYear(2, 2023);
        int numberOfEventInMarch2023 = eventsModel.getNumberOfEventsInMonthYear(3, 2023);
        int numberOfEventInApril2023 = eventsModel.getNumberOfEventsInMonthYear(4, 2023);
        int numberOfEventInMay2023 = eventsModel.getNumberOfEventsInMonthYear(5, 2023);
        int numberOfEventInJune2023 = eventsModel.getNumberOfEventsInMonthYear(6, 2023);
        int numberOfEventInJuly2023 = eventsModel.getNumberOfEventsInMonthYear(7, 2023);
        int numberOfEventInAugust2023 = eventsModel.getNumberOfEventsInMonthYear(8, 2023);
        int numberOfEventInSeptember2023 = eventsModel.getNumberOfEventsInMonthYear(9, 2023);
        int numberOfEventInOctober2023 = eventsModel.getNumberOfEventsInMonthYear(10, 2023);
        int numberOfEventInNovember2023 = eventsModel.getNumberOfEventsInMonthYear(11, 2023);
        int numberOfEventInDecember2023 = eventsModel.getNumberOfEventsInMonthYear(12, 2023);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("January 2023", numberOfEventInJanuary2023));
        series.getData().add(new XYChart.Data<>("February 2023", numberOfEventInFebruary2023));
        series.getData().add(new XYChart.Data<>("March 2023", numberOfEventInMarch2023));
        series.getData().add(new XYChart.Data<>("April 2023", numberOfEventInApril2023));
        series.getData().add(new XYChart.Data<>("May 2023", numberOfEventInMay2023));
        series.getData().add(new XYChart.Data<>("June 2023", numberOfEventInJune2023));
        series.getData().add(new XYChart.Data<>("July 2023", numberOfEventInJuly2023));
        series.getData().add(new XYChart.Data<>("August 2023", numberOfEventInAugust2023));
        series.getData().add(new XYChart.Data<>("September 2023", numberOfEventInSeptember2023));
        series.getData().add(new XYChart.Data<>("October 2023", numberOfEventInOctober2023));
        series.getData().add(new XYChart.Data<>("November 2023", numberOfEventInNovember2023));
        series.getData().add(new XYChart.Data<>("December 2023", numberOfEventInDecember2023));

        System.out.println("Number of events in January 2023: " + numberOfEventInJanuary2023);
        System.out.println("Number of events in July 2023: " + numberOfEventInJuly2023);

        eventFrequencyBarChart.getData().add(series);

        eventsModel.close();
    }

}