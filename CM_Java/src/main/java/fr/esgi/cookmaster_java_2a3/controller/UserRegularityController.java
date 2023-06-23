package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.BillsModel;
import fr.esgi.cookmaster_java_2a3.model.UsersModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class UserRegularityController extends Controller {

    @FXML
    private BarChart userRegularityBarChart;

    @FXML
    public void initialize() {

        BillsModel billsModel = new BillsModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        int numberOfSalesFirstTrimester2022 = billsModel.getNumberOfSalesFirstTrimester2022();
        int numberOfSalesSecondTrimester2022 = billsModel.getNumberOfSalesSecondTrimester2022();
        int numberOfSalesThirdTrimester2022 = billsModel.getNumberOfSalesThirdTrimester2022();
        int numberOfSalesFourthTrimester2022 = billsModel.getNumberOfSalesFourthTrimester2022();
        int numberOfSalesFirstTrimester2023 = billsModel.getNumberOfSalesFirstTrimester2023();
        int numberOfSalesSecondTrimester2023 = billsModel.getNumberOfSalesSecondTrimester2023();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("First Trimester 2022", numberOfSalesFirstTrimester2022));
        series.getData().add(new XYChart.Data<>("Second Trimester 2022", numberOfSalesSecondTrimester2022));
        series.getData().add(new XYChart.Data<>("Third Trimester 2022", numberOfSalesThirdTrimester2022));
        series.getData().add(new XYChart.Data<>("Fourth Trimester 2022", numberOfSalesFourthTrimester2022));
        series.getData().add(new XYChart.Data<>("First Trimester 2023", numberOfSalesFirstTrimester2023));
        series.getData().add(new XYChart.Data<>("Second Trimester 2023", numberOfSalesSecondTrimester2023));

        userRegularityBarChart.getData().add(series);

        billsModel.close();
    }


}