package fr.esgi.cookmaster_java_2a3.controller;

import fr.esgi.cookmaster_java_2a3.model.UsersModel;
import fr.esgi.cookmaster_java_2a3.tools.CommonDataGenerator;
import fr.esgi.cookmaster_java_2a3.tools.CommonSettings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class UserTypeController extends Controller {

    @FXML
    private PieChart userTypePieChart;

    @FXML
    public void initialize() {
        UsersModel usersModel = new UsersModel(CommonSettings.DB_NAME, CommonSettings.USER_NAME, CommonSettings.PASSWORD);
        int numberOfMaleWithSubType0 = usersModel.getNumberOfUserMaleAndType0();
        int numberOfMaleWithSubType1 = usersModel.getNumberOfUserMaleAndType1();
        int numberOfMaleWithSubType2 = usersModel.getNumberOfUserMaleAndType2();

        int numberOfFemaleWithSubType0 = usersModel.getNumberOfUserFemaleAndType0();
        int numberOfFemaleWithSubType1 = usersModel.getNumberOfUserFemaleAndType1();
        int numberOfFemaleWithSubType2 = usersModel.getNumberOfUserFemaleAndType2();

        PieChart.Data slice1 = new PieChart.Data("Male / Free", numberOfMaleWithSubType0);
        PieChart.Data slice2 = new PieChart.Data("Male / Starter", numberOfMaleWithSubType1);
        PieChart.Data slice3 = new PieChart.Data("Male / Cook", numberOfMaleWithSubType2);
        PieChart.Data slice4 = new PieChart.Data("Female / Free", numberOfFemaleWithSubType0);
        PieChart.Data slice5 = new PieChart.Data("Female / Starter", numberOfFemaleWithSubType1);
        PieChart.Data slice6 = new PieChart.Data("Female / Cook", numberOfFemaleWithSubType2);

        userTypePieChart.getData().add(slice1);
        userTypePieChart.getData().add(slice2);
        userTypePieChart.getData().add(slice3);
        userTypePieChart.getData().add(slice4);
        userTypePieChart.getData().add(slice5);
        userTypePieChart.getData().add(slice6);

        usersModel.close();
    }

}