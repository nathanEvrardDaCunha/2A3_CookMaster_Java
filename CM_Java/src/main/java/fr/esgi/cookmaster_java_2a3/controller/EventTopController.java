package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.EventsModel;
import fr.esgi.cookmaster_java_2a3.model.UsersModel;
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
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

public class EventTopController extends Controller{

    @FXML
    private BarChart eventTopBarChart;

    @FXML
    public void initialize() {
        EventsModel eventsModel = new EventsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        List<Pair<String, Integer>> topEvents = eventsModel.getTopEventsByParticipants();
        int maxEvents = Math.min(topEvents.size(), 5);
        for (int i = 0; i < maxEvents; i++) {
            Pair<String, Integer> topEvent = topEvents.get(i);
            series.getData().add(new XYChart.Data<>(topEvent.getKey(), topEvent.getValue()));
        }

        eventTopBarChart.getData().add(series);

        eventsModel.close();
    }


}