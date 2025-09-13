package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import com.example.model.GameLogic;
import com.example.model.Country;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {

    @FXML private Button troopLabel;
    @FXML private Slider slider;
    @FXML private Label countryLabelC,countryLabelF,conquerLabel,fortifyLabel;
    private GameLogic game;
    private Country country,country2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.valueProperty().addListener((observableValue, number, t1) -> troopLabel.setText(String.valueOf((int)slider.getValue())));
    }

    //Update the country(flags) values,updates world and map
    @FXML
    public void fortify() {
        game.getBoardController().setOneSelected(false);
        game.getBoardController().setTwoSelected(false);
        game.getWorld().moveArmy(country,country2,(int)slider.getValue(),game.getCurrentPlayer());
        game.getBoardController().updateMap(game.getWorld().getPlayers(), game.getScene());
        Stage stage = (Stage) troopLabel.getScene().getWindow();
        stage.close();
    }

    //Initialize the values of scene's components
    public void init(GameLogic gameLogic,Country country, Country country2) {
        this.game = gameLogic;
        this.country = country;
        this.country2 = country2;

        slider.setMax((double)country.getNumTroops() - 1);
        slider.setMin(0);
        slider.setValue(0);
        troopLabel.setText("0");

        if(conquerLabel != null) {
            conquerLabel.setStyle("-fx-text-fill:" + game.getCurrentPlayer().getColor());
        }
        if(fortifyLabel != null) {
            fortifyLabel.setStyle("-fx-text-fill:" + game.getCurrentPlayer().getColor());
        }
        if(countryLabelF != null) {
            countryLabelF.setText(country2.toString());
        }
        if(countryLabelC != null) {
            countryLabelC.setText(country2.toString());
        }
    }
}
