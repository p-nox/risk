package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import com.example.model.GameLogic;


public class TradeCardsController {

    @FXML
    private Button closeButton;
    @FXML
    private Label troopLabel, cavalryLabel, artilleryLabel, totalLabel;
    @FXML
    private Spinner<Integer> troopSpinner, cavalrySpinner, artillerySpinner;
    private GameLogic game;

    //Close the window
    @FXML
    public void backButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        game.getBoardController().getTradeButton().setDisable(false);
    }

    //updates player's cards and the window
    @FXML
    public void tradeButton() {
        int numOfTrades = game.getWorld().redeemCards(troopSpinner.getValue(), cavalrySpinner.getValue(), artillerySpinner.getValue(), game.getCurrentPlayer());
        if(numOfTrades != 0) {
            game.getCurrentPlayer().setUnsedTroops(numOfTrades);
        }
        init(game);
    }

    //Initialize the values of scene's components
    public void init(GameLogic gameLogic) {
        this.game = gameLogic;
        //Spinner Initialize
        if(!game.getCurrentPlayer().getCards().fullHandCheck()){
            closeButton.setDisable(false);
        }

        //Initialize spinners
        SpinnerValueFactory<Integer> troopValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, game.getCurrentPlayer().getCards().getCards()[0], 0);
        SpinnerValueFactory<Integer> cavalryValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, game.getCurrentPlayer().getCards().getCards()[1], 0);
        SpinnerValueFactory<Integer> artilleryValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, game.getCurrentPlayer().getCards().getCards()[2], 0);
        troopSpinner.setValueFactory(troopValue);
        cavalrySpinner.setValueFactory(cavalryValue);
        artillerySpinner.setValueFactory(artilleryValue);
        totalLabel.setText("");

        //Listeners for spinners to update totalLabel
        troopValue.valueProperty().addListener(e -> updateTotal(troopValue.getValue(), cavalryValue.getValue(), artilleryValue.getValue()));
        cavalryValue.valueProperty().addListener(e -> updateTotal(troopValue.getValue(), cavalryValue.getValue(), artilleryValue.getValue()));
        artilleryValue.valueProperty().addListener(e -> updateTotal(troopValue.getValue(), cavalryValue.getValue(), artilleryValue.getValue()));

        //Labels Initialize
        troopLabel.setText("x " + game.getCurrentPlayer().getCards().getCards()[0]);
        cavalryLabel.setText("x " + game.getCurrentPlayer().getCards().getCards()[1]);
        artilleryLabel.setText("x " + game.getCurrentPlayer().getCards().getCards()[2]);

    }

    //Calculations for total
    public void updateTotal(int t, int c, int a) {
        int total = 0, n = game.getWorld().getNumOfTrades(); // temp variables to calculate the total IRT
        int[] value = new int[3];
        boolean flag = false;
        value[0] = t;
        value[1] = c;
        value[2] = a;

        //Calculate the total if all values are equal
        if ( (value[0] == value[1]) && (value[1] == value[2]) && value[0] > 0 ) {
            for(int i=0; i < value[0]; i++) {
                total += n ;
                n += 2;
            }
            totalLabel.setText(String.valueOf(total));
            //Flag indicator if the 1rst condition success don't go any further
            flag = true;
        }
        //Second condition : check for any value that is multiply of 3
        if(!flag) {
            for (int i = 0; i < 3; i++) {
                if ( (value[i] != 0) && (value[i] % 3) == 0 && (value[i] >= 3) ) {
                    total += (n * (value[i] / 3) );
                    n += (2 * (value[i] / 3)) ;
                }
                if ( total >= game.getWorld().getNumOfTrades() ) {
                    totalLabel.setText(String.valueOf(total));
                } else { totalLabel.setText(""); }
            }
        }
    }

    public Button getCloseButton() { return closeButton; }

}
