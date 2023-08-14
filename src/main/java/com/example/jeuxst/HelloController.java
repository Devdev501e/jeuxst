package com.example.jeuxst;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class HelloController implements Initializable {
    Scene scene;






    @FXML
    public void onHelloButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("suite.fxml"));
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Suit.css").toExternalForm());
        stage1.setScene(scene);
        stage1.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}