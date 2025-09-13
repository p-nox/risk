package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.example.model.GameLogic;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class WinnerController {

    @FXML private Button okButton;
    private GameLogic game;

    @FXML
    public void okButton() throws IOException {
        Stage stage = (Stage) okButton.getScene().getWindow();
        game.getPrimaryStage().close();
        stage.close();
        //create a new stage
        Parent root;
        root = FXMLLoader.load(requireNonNull(StartMenuController.class.getResource("StartMenu.fxml")));
        stage.setScene(new Scene(root, 800, 600));
        stage.centerOnScreen();
        stage.show();
    }

    public void init(GameLogic gameLogic) {
        this.game = gameLogic;
    }
}
