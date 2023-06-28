package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.*;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class UserCAController extends Controller {

    @FXML
    private PieChart userCAPieChart;

    @FXML
    public void initialize() {

        UsersModel usersModel = new UsersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);

        for(String city : CommonDataGenerator.eventCityArray) {
            int subscriptionCostForUsersFromCity = usersModel.getSubscriptionCostForUsersFromCity(city);
            int sumOfCommandForUsersFromCity = usersModel.getSumOfCommandForUsersFromCity(city);
            int sumCAFromCity = subscriptionCostForUsersFromCity + sumOfCommandForUsersFromCity;
            PieChart.Data slice = new PieChart.Data(city, sumCAFromCity);

            userCAPieChart.getData().add(slice);
        }

        usersModel.close();
    }
}
