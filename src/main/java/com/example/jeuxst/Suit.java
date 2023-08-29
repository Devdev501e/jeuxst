package com.example.jeuxst;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Suit implements Initializable {
    public String[] NP2 = {"Bleu", "Vert", "Violet", "Orange"};

    public String color = null;
    public String name;
    @FXML
    ChoiceBox myChoiceBox;


    @FXML
    Label label1;
    @FXML
    public TextField NOMCL;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(NP2);//chose box
        myChoiceBox.setOnAction(this::action1);

    }

    private void action1(Event event) {

        color = (String) myChoiceBox.getValue();
        label1.setText("Vous avez choisi la couleur "+color);
        System.out.println(color);
    }

    public void onHelloButtonClick(ActionEvent event) throws IOException {


        if (!NOMCL.getText().equals("")) {
            name = NOMCL.getText();
        }

        if(!NOMCL.getText().equals("")&&!color.equals(null)){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root= loader.load();

            Game_controleur game_controleur=loader.getController();
            game_controleur.initia(color,name);


            Stage stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
           Scene  scene= new Scene(root);

            scene.getStylesheets().add(getClass().getResource("Suit.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        }

        System.out.println(name);


    }
}