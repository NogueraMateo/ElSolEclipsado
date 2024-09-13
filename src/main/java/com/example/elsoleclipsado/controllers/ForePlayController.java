package com.example.elsoleclipsado.controllers;

import com.example.elsoleclipsado.model.ForePlayModel;
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

public class ForePlayController {

    private ForePlayModel forePlayModel;

    @FXML
    private TextField secretWordTextField;

    @FXML
    private Button playButton;

    @FXML
    private Label maxCharsReachedWarning;


    @FXML
    public void initialize() {

        forePlayModel = new ForePlayModel();
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
    public void onActionPlayButton() {

        String inputWord = secretWordTextField.getText();
        loadGameView(inputWord);

    }


    @FXML
    public void loadGameView(String secretWord) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass()
                    .getResource("/com/example/elsoleclipsado/game-view.fxml")
            );

            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.initGame(secretWord);

            Stage stage =  (Stage) playButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void handleKeyTyped(KeyEvent keyEvent) {

        String currentWord = secretWordTextField.getText();
        String incomingChar = keyEvent.getCharacter();

        if (!forePlayModel.isValidCharacter(incomingChar) || forePlayModel.maximumLengthReached(currentWord)) {

            keyEvent.consume();
            if (forePlayModel.maximumLengthReached(currentWord))
                maxCharsReachedWarning.setVisible(true);

        }
        else playButton.setDisable(!forePlayModel.minimumLengthReached(currentWord + incomingChar));

    }


    public void handleKeyPressedTextField(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.BACK_SPACE && secretWordTextField.getText().length() == 6) {
            playButton.setDisable(true);
        } else {
            maxCharsReachedWarning.setVisible(false);
        }

    }
}
