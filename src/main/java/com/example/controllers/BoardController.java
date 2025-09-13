package com.example.controllers;


import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.example.model.GameLogic;
import com.example.model.Country;
import com.example.model.Player;

import static java.util.Objects.*;


public class BoardController  {

    @FXML
    private Button closeButton, attackButton, skipButton, tradeButton;
    @FXML
    private Label playerLabel, unsedTroopsLabel, upkeepLabel, fortifyLabel;
    private Country country,country2;
    private boolean twoSelected = false,oneSelected = false;
    private GameLogic game;
    private String buttonId,
            disableColor = "-fx-background-color: #bd1111",
            highLightStyle = "CountryButtonHighlight",
            enableColor = "-fx-text-fill: #777d78";

    //Opens the StartMenu window
    @FXML
    private void switchToStartMenu() throws IOException {
        //close current stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        //create a new stage
        Parent root;
        root = FXMLLoader.load(requireNonNull(StartMenuController.class.getResource("StartMenu.fxml")));
        stage.setScene(new Scene(root, 800, 600));
        stage.centerOnScreen();
        stage.show();

    }

    //Calculates the attack result,reset flags,updates map and opens fortify and winner window
    @FXML
    private void attackButton() throws IOException {
        //Sets the attackButton disable again
        attackButton.setStyle(disableColor);
        attackButton.setDisable(true);
        //Reset selected flags
        oneSelected = false;
        twoSelected = false;
        //Initialize attack
        if(game.getWorld().attack(country,country2)) {
            //Check if the current player is the winner,opens winner Message GUI
            if (game.getWorld().checkWin(game.getWorld().getPlayers())) {
                Parent root;
                FXMLLoader fxmlLoader = new FXMLLoader(MessageController.class.getResource("WinnerMessage.fxml"));
                root = fxmlLoader.load();
                WinnerController winnerController = fxmlLoader.getController();
                winnerController.init(game);
                Stage stage = new Stage();
                stage.setTitle("RISK");
                stage.setScene(new Scene(root, 200, 100));
                stage.setResizable(false);
                stage.alwaysOnTopProperty();
                stage.centerOnScreen();
                stage.show();
            //Else show the victory Message GUI
            } else {
                Parent root;
                FXMLLoader fxmlLoader = new FXMLLoader(MessageController.class.getResource("Victory.fxml"));
                root = fxmlLoader.load();
                MessageController messageController = fxmlLoader.getController();
                messageController.init(game, country, country2);
                updateMap(game.getWorld().getPlayers(), game.getScene());
                Stage stage = new Stage();
                stage.setTitle("RISK");
                stage.setScene(new Scene(root, 300, 200));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            }
        }
        updateMap(game.getWorld().getPlayers(), game.getScene());
    }

    //Skip to the next phase/next player,updates map and reset flag-values
    @FXML
    private void skipButton() {
        game.setState(game.getState() + 1);
        twoSelected = false;
        oneSelected = false;
        updateMap(game.getWorld().getPlayers(), game.getScene());
    }

    //Opens the trade-cards window and initialise controller
    @FXML
    private void tradeCardsButton() throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(TradeCardsController.class.getResource("TradeCards.fxml"));
        root = fxmlLoader.load();
        TradeCardsController tradeCardsController = fxmlLoader.getController();
        tradeCardsController.init(game);
        Stage stage = new Stage();
        stage.setTitle("RISK");
        stage.setScene(new Scene(root, 300, 370));
        stage.setResizable(false);
        stage.centerOnScreen();
        tradeButton.setDisable(true);
        stage.show();
    }

    /* This is the event handler for country-buttons.Each phase has different handler
       for the buttons.
     */
    @FXML
    private void loadWhenClicked(ActionEvent event) throws IOException {

        Button button = (Button) event.getSource();
        //Upkeep handler
        if (game.getState() <= 2) {
            upkeepHandler(button);
        }
        else {
            buttonId = button.getId();
            //If the first country is selected lock it and wait for the second country to get selected
            if ( !oneSelected && !twoSelected ){
                country = game.getWorld().findCountry(buttonId);
                if(game.getCurrentPlayer().getCountriesOwned().contains(country) && country.getNumTroops() >= 2) {
                    Button b1 = (Button) button.lookup("#" + country.toString());
                    b1.getStyleClass().add(highLightStyle);
                    oneSelected = true;
                }
            }
            else {
                //If the second country is selected lock the selection and depending of the game state do actions
                if (oneSelected && !twoSelected) {
                    country2 = game.getWorld().findCountry(buttonId);
                    if (game.getState() == 3) {
                        attackHandler(button);
                    } else if (game.getState() == 4) {
                        endturnHandler(button);
                    }
                }else{
                    //Reset player's country selections
                    updateMap(game.getWorld().getPlayers(), game.getScene());
                    attackButton.setStyle(disableColor);
                    attackButton.setDisable(true);
                    twoSelected =false;
                }
                oneSelected = false;
            }
        }
    }

    //This method handles the button event trigger in the upkeep phase
    private void upkeepHandler(Button button){
        buttonId = button.getId();
        country = game.getWorld().fortify(buttonId,game.getCurrentPlayer());
        button.setText(String.valueOf(country.getNumTroops()));
        unsedTroopsLabel.setText("x" + game.getCurrentPlayer().getUnsedTroops());
    }

    //This method handles the button event trigger in the attack phase
    private void attackHandler(Button button) {
        if(!game.getCurrentPlayer().getCountriesOwned().contains(country2) && country.getAdjacentCountries().contains(country2)) {
            Button b2 = (Button) button.lookup("#" + country2.toString());
            b2.getStyleClass().add(highLightStyle);
            if (game.getWorld().attackCheck(country, country2, game.getCurrentPlayer())) {
                attackButton.setStyle("-fx-background-color: #0fea88");
                attackButton.setDisable(false);
            }
            twoSelected = true;
        }
        else {
            updateMap(game.getWorld().getPlayers(), game.getScene());
            twoSelected =false;
        }
    }

    //This method handles the button event trigger in the endturn phase
    private void endturnHandler(Button button) throws IOException {
        if (game.getCurrentPlayer().getCountriesOwned().contains(country2) && game.getWorld().findPath(country, country2) && country != country2) {
            Button b2 = (Button) button.lookup("#" + country2.toString());
            b2.getStyleClass().add(highLightStyle);
            if (game.getWorld().moveArmyCheck(country, country2, game.getCurrentPlayer())) {
                Parent root;
                FXMLLoader fxmlLoader = new FXMLLoader(MessageController.class.getResource("Fortify.fxml"));
                root = fxmlLoader.load();
                MessageController messageController = fxmlLoader.getController();
                messageController.init(game, country, country2);
                Stage stage = new Stage();
                stage.setTitle("RISK");
                stage.setScene(new Scene(root, 250, 170));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            }
            twoSelected = true;
        }
        else {
            updateMap(game.getWorld().getPlayers(), game.getScene());
            twoSelected = false;
        }
    }

    //Initialise board with the current player's information
    public void init(GameLogic gameLogic){
        game = gameLogic;
        playerLabel.setStyle("-fx-text-fill:" + game.getCurrentPlayer().getColor());
        playerLabel.setText("PLAYER " + game.getCurrentPlayer().getId());
        unsedTroopsLabel.setText("x" + game.getCurrentPlayer().getUnsedTroops());
    }

    /*GUI Setters*/
    public  void updateMap(Player[] players, Scene scene){
        for (Player p : players) {
            for (Country c : p.getCountriesOwned()) {
                Button b = (Button) scene.lookup("#" + c.toString());
                b.getStyleClass().clear();
                b.getStyleClass().add("CountryButton");
                b.setText(String.valueOf((c.getNumTroops())));
                b.setStyle("-fx-background-color:" + p.getColor());
            }
        }
    }

    public void placeTroops() {
        upkeepLabel.setStyle(enableColor);
        attackButton.setDisable(true);
        skipButton.setDisable(true);
        tradeButton.setDisable(true);
    }

    public void upkeep() {
        //Turn on the UPKEEP indicator
        upkeepLabel.setStyle("-fx-text-fill: #0fea88");
        //Turn off the FORTIFY indicator
        fortifyLabel.setStyle(enableColor);
        attackButton.setDisable(true);
        skipButton.setDisable(true);
        tradeButton.setDisable(true);
    }

    public void attackPhase() {
        //Turn off the UPKEEP indicator
        upkeepLabel.setStyle(enableColor);
        //Turn off the FORTIFY indicator
        fortifyLabel.setStyle(enableColor);
        skipButton.setDisable(false);
        tradeButton.setDisable(true);
    }

    public void endTurn() {
        //Turn off the UPKEEP indicator
        upkeepLabel.setStyle(enableColor);
        //Turn on the FORTIFY indicator
        fortifyLabel.setStyle("-fx-text-fill: #0fea88");
        skipButton.setDisable(false);
        tradeButton.setDisable(false);
        attackButton.setDisable(true);
        //Check if the player won a battle in attack phase,if true add a random card
    }

    public void fullHand(GameLogic game) throws IOException {
        //If fullHandCheck = true display TradeCards window
        if (game.isFlag()) {
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(TradeCardsController.class.getResource("TradeCards.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("RISK");
            stage.setScene(new Scene(root, 300, 370));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.alwaysOnTopProperty();
            stage.show();
            TradeCardsController tradeCardsController = fxmlLoader.getController();
            tradeCardsController.init(game);
            tradeCardsController.getCloseButton().setDisable(true);
        }
        game.setFlag(false);
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        System.out.println("exit");
        Platform.exit();

    }

    /*Setters & Getters*/

    public void setTwoSelected(boolean twoSelected) { this.twoSelected = twoSelected; }

    public void setOneSelected(boolean oneSelected) { this.oneSelected = oneSelected; }

    public Button getTradeButton() { return tradeButton; }

}


