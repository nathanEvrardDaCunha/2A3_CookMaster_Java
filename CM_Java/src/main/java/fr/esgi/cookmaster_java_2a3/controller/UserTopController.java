package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.BillsModel;
import fr.esgi.cookmaster_java_2a3.model.UsersModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

public class UserTopController extends Controller {

    @FXML
    private BarChart userTopBarChart;

    @FXML
    public void initialize() {

        UsersModel usersModel = new UsersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 1; i <= 5; i++) {
            Pair<String, Integer> topUser = usersModel.getTopUserByFidelityScore(i);
            if (topUser != null) {
                series.getData().add(new XYChart.Data<>(topUser.getKey(), topUser.getValue()));
            }
        }

        userTopBarChart.getData().add(series);

        usersModel.close();

    }

}