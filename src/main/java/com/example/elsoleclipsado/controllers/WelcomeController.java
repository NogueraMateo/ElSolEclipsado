package com.example.elsoleclipsado.controllers;

import com.example.elsoleclipsado.model.welcome.WelcomeModel;
import com.example.elsoleclipsado.view.GameView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    private WelcomeModel welcomeModel;

    @FXML
    private TextField secretWordTextField;

    @FXML
    private Button playButton;

    @FXML
    private Label maxCharsReachedWarning;


    @FXML
    public void initialize() {

        welcomeModel = new WelcomeModel();
        secretWordTextField.addEventFilter(
                KeyEvent.KEY_TYPED,
                this::handleKeyTyped
        );

        secretWordTextField.addEventFilter(
                KeyEvent.KEY_PRESSED,
                this::handleKeyPressedTextField
        );
    }


    @FXML
    public void onActionPlayButton() throws IOException {
        String inputWord = secretWordTextField.getText();
        GameView gameView = GameView.getInstance();
        gameView.getGameController().initGame(inputWord);
    }


    public void handleKeyTyped(KeyEvent keyEvent) {

        String currentWord = secretWordTextField.getText();
        String incomingChar = keyEvent.getCharacter();

        if (!welcomeModel.isValidCharacter(incomingChar) || welcomeModel.maximumLengthReached(currentWord)) {

            keyEvent.consume();
            if (welcomeModel.maximumLengthReached(currentWord))
                maxCharsReachedWarning.setVisible(true);

        }
        else playButton.setDisable(!welcomeModel.minimumLengthReached(currentWord + incomingChar));

    }


    public void handleKeyPressedTextField(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.BACK_SPACE && secretWordTextField.getText().length() == 6) {
            playButton.setDisable(true);
        } else {
            maxCharsReachedWarning.setVisible(false);
        }

    }
}
