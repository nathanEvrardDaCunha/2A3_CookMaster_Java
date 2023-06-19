package fr.esgi.cookmaster_java_2a3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PrestationController {

    // FAIRE UN EXTENDS DE LA CLASSE CONTROLLER

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void openUserView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/user-ca-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //icon : Image icon = new Image("/fr/esgi/cookmaster_java_2a3/assets/icon.png");
        //stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openEventView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/event-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //icon : Image icon = new Image("/fr/esgi/cookmaster_java_2a3/assets/icon.png");
        //stage.getIcons().add(icon);


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void openPrestationView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fr/esgi/cookmaster_java_2a3/prestation-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        //icon : Image icon = new Image("/fr/esgi/cookmaster_java_2a3/assets/icon.png");
        //stage.getIcons().add(icon);


        stage.setScene(scene);
        stage.show();
    }
}