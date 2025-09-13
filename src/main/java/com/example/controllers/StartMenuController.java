package com.example.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import com.example.App;

public class StartMenuController {

    @FXML
    private ToggleGroup numPlayers;

    @FXML
    private void startGameButton() throws IOException {
        ToggleButton selectedButton = (ToggleButton) numPlayers.getSelectedToggle();
        if(selectedButton != null) {
            App.startGame(Integer.parseInt(selectedButton.getText()));
        }
    }

    @FXML
    private void exitButton() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void howToPLayButton() throws IOException{
        String url ="https://docs.google.com/document/d/1acLcNAga5Q8TIbLqt7DTHUS2DIkE6y_vnBmF61aNWYc/edit?usp=sharing";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

}
