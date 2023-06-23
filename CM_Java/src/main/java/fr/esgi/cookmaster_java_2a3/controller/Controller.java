package fr.esgi.cookmaster_java_2a3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void openUserView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-ca-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openEventView(ActionEvent event) throws IOException {

        // ERREUR CHANGER DE PAGE

        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/event-type-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openUserCAView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-ca-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openUserRegularityView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-regularity-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openUserTypeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-type-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openUserTopView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-top-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openEventTopView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/event-top-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openEventFrequencyView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/event-frequency-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openEventTypeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/event-type-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openPrestationCAView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/prestation-ca-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openPrestationTypeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/prestation-type-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openPrestationTopView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/prestation-top-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openPrestationAverageView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/prestation-average-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

}
