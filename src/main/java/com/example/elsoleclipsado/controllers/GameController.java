package com.example.elsoleclipsado.controllers;

import com.example.elsoleclipsado.model.adapters.TypingControlAdapter;
import com.example.elsoleclipsado.model.GameModel;
import com.example.elsoleclipsado.model.RevealedLetter;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class GameController extends TypingControlAdapter {


    private GameModel game;

    @FXML
    private HBox textFieldsContainer;

    @FXML
    private ImageView sunImageView;

    @FXML
    private Label warningLabel;


    @FXML
    public void onActionValidateButton() {

        String inputWord = getUserWord();

        if (game.isCorrectSecretWord(inputWord)) {
            System.out.println("Correct secret word, you won");
        }
        else {
            changeSunStatus();
            if (game.isLooser()) {
                System.out.println("Incorrect Secret Word, you loose");
                return;
            }
            System.out.println("Incorrect Secret Word, try again");
        }
    }


    @FXML
    public void onActionHelpButton() {

        try {

            RevealedLetter letter = game.revealLetter();
            Node revealedNode = textFieldsContainer.getChildren().get(letter.getIndex());

            ((TextField) revealedNode).setText(String.valueOf(letter.getLetter()));
            revealedNode.setDisable(true);

        } catch (Exception e) {

            warningLabel.setText(e.getMessage());
            warningLabel.setVisible(true);

            setFadeTransition(warningLabel);
        }

    }


    public void initGame(String secretWord) {
        game = new GameModel(secretWord);
        System.out.println("Game initialized with secret word: " + secretWord);

        for ( int i = 0; i < secretWord.length(); ++i ) {
            // Create a new TextField
            TextField textField = new TextField();

            // Setting an id
            textField.setId("textField" + i);
            textField.getStyleClass().add("text-field");

            textField.addEventFilter(KeyEvent.KEY_TYPED, this::handleKeyTyped);
            textFieldsContainer.getChildren().add(textField);
        }
    }


    public void handleKeyTyped(KeyEvent event) {

        TextField currentField = (TextField) event.getSource();
        String incomingChar = event.getCharacter();


        if (incomingChar.equals("\b")) {

            int currentIndex = textFieldsContainer.getChildren().indexOf(currentField);

            if (currentIndex > 0) {
                Node previousNode = textFieldsContainer.getChildren().get(currentIndex - 1);

                if (previousNode instanceof TextField) {
                    ((TextField) previousNode).requestFocus();
                    currentField.clear();
                }
            }
            event.consume();
        }
        else if (isValidCharacter(incomingChar)) {

            if (!currentField.getText().isEmpty()) {
                event.consume();
            }

            else {
                currentField.setText(incomingChar);
                int currentIndex = textFieldsContainer.getChildren().indexOf(currentField);
                if (currentIndex < textFieldsContainer.getChildren().size() - 1) {
                    Node nextNode = textFieldsContainer.getChildren().get(currentIndex + 1);

                    if (nextNode instanceof TextField) {
                        ((TextField) nextNode).requestFocus();
                    }
                }
            }
            event.consume();
        }

        else {
            event.consume();
        }
    }


    public void changeSunStatus() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), sunImageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.5);

        fadeOut.setOnFinished( actionEvent -> {
            String newStatusImagePath = String
                    .format(
                            "/com/example/elsoleclipsado/images/phase%s.png", game.getCurrentAttempts()
                    );
            Image newSunStatus = new Image(
                    getClass().getResourceAsStream(newStatusImagePath)
            );
            sunImageView.setImage(newSunStatus);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), sunImageView);
            fadeIn.setFromValue(0.5);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }


    public String getUserWord() {

        StringBuilder userWord = new StringBuilder();
        for (int i = 0; i < game.getSecretWordLength(); ++i) {
            Node currentNode = textFieldsContainer.getChildren().get(i);
            if (currentNode instanceof TextField) {
                userWord.append(((TextField) currentNode).getText());
            }
        }
        return userWord.toString();

    }

    public void setFadeTransition(Label object) {
        FadeTransition transition = new FadeTransition(
                Duration.seconds(3), object
        );
        transition.setFromValue(1.0);
        transition.setToValue(0.0);
        transition.setOnFinished(event -> object.setVisible(false));
        transition.play();
    }

}

