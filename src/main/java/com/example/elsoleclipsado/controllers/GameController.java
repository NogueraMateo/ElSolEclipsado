package com.example.elsoleclipsado.controllers;

import com.example.elsoleclipsado.controllers.alerts.LooserAlert;
import com.example.elsoleclipsado.controllers.alerts.WinnerAlert;
import com.example.elsoleclipsado.model.adapters.TypingControlAdapter;
import com.example.elsoleclipsado.model.game.GameModel;
import com.example.elsoleclipsado.model.game.RevealedLetter;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.net.SocketOption;
import java.sql.SQLOutput;
import java.util.List;

/**
 * Manages how the user interacts with the interface and communicates
 * these actions with the Game logic if necessary
 * @author Mateo Noguera Pinto
 * @version 1.0
 */
public class GameController extends TypingControlAdapter {


    private GameModel game;

    @FXML
    private HBox textFieldsContainer;

    @FXML
    private ImageView sunImageView;

    @FXML
    private Label warningLabel;

    @FXML
    private TextField inputLetterField;

    @FXML
    private Label remainingAttempts;

    @FXML
    private Label remainingHelps;

    /**
     * Method intended to be executed when the game is going to start
     * Dynamically creates an according amount of text fields
     * @param secretWord : secret word entered by the user
     */
    public void initGame(String secretWord) {
        game = new GameModel(secretWord);

        for ( int i = 0; i < secretWord.length(); ++i ) {
            // Create a new TextField
            TextField textField = new TextField();

            // Setting an id
            textField.setId("textField" + i);
            textField.setDisable(true);
            textField.getStyleClass().add("text-field");

            this.inputLetterField.addEventFilter(KeyEvent.KEY_TYPED, this::handleKeyTyped);

            textFieldsContainer.getChildren().add(textField);
        }
    }


    /**
     * Handles the action when the validate button is pressed.
     * Gets the letter inside the text field and communicates
     * with the GameModel in order to decide whether the letter
     * is valid.
     * Checks if the user has lost or has won.
     */
    @FXML
    public void onActionValidateButton() {

        String inputLetter = this.inputLetterField.getText();

        if (game.isCorrectInputLetter(inputLetter)) {

            List<Integer> indexes = game.getLetterIndexes(inputLetter);

            for (int i : indexes) {
                Node revealedNode = textFieldsContainer.getChildren().get(i);
                ((TextField) revealedNode).setText(String.valueOf(inputLetter));
            }

            if (this.isWinner()) {
                this.showWinnerAlert();
            }
        }
        else {

            changeSunStatus();
            this.remainingAttempts.setText("Intentos restantes: " + this.game.getRemainingAttempts());
            if (game.isLooser()) {
                this.showLooserAlert();
                return;
            }
        }
    }


    /**
     * Handles the event when the help button is pressed.
     * Communicates with the GameModel in order to get a random
     * letter to reveal.
     * After the letter is revealed, checks if the user has won.
     */
    @FXML
    public void onActionHelpButton() {

        try {
            RevealedLetter revealedLetter;
            Node revealedNode;
            do {
                revealedLetter = game.revealLetter();
                revealedNode = textFieldsContainer.getChildren().get(revealedLetter.getIndex());
            } while (!((TextField) revealedNode).getText().isEmpty());

            game.markUsedHelp();
            this.remainingHelps.setText("Ayudas restantes: " + this.game.getRemainingHelps());
            List<Integer> indexes = game.getLetterIndexes(revealedLetter.getLetter());

            for (int i : indexes) {
                revealedNode = textFieldsContainer.getChildren().get(i);
                ((TextField) revealedNode).setText(String.valueOf(revealedLetter.getLetter()));
            }

            if (this.isWinner()) {
                this.showWinnerAlert();
            }

        } catch (Exception e) {

            warningLabel.setText(e.getMessage());
            warningLabel.setVisible(true);

            setFadeTransition(warningLabel);
        }

    }


    /**
     * Handles the event when a key is typed inside the text field for
     * validating a letter.
     * Allows only valid characters through
     * @param event : the KeyEvent
     */
    public void handleKeyTyped(KeyEvent event) {

        TextField currentField = (TextField) event.getSource();
        String incomingChar = event.getCharacter();

        if (isValidCharacter(incomingChar)) {

            if (!currentField.getText().isEmpty()) {
                event.consume();
            }
            else {
                currentField.setText(incomingChar);
            }
        }

        else {
            event.consume();
        }
    }


    /**
     * Method intended to be called when the user validates a wrong letter.
     * Changes the sun phase (image) with a smooth transition.
     */
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


    /**
     * Sets a smooth transition to a label object.
     * Is used to show the label that warns the user that
     * he has no more helps to use.
     * @param object : label
     */
    public void setFadeTransition(Label object) {
        FadeTransition transition = new FadeTransition(
                Duration.seconds(3), object
        );
        transition.setFromValue(1.0);
        transition.setToValue(0.0);
        transition.setOnFinished(event -> object.setVisible(false));
        transition.play();
    }


    /**
     * Checks whether the user has won or not based on the
     * amount of non-empty text fields.
     * @return true if all the text fields are not empty, false otherwise
     */
    public boolean isWinner() {
        ObservableList<Node> nodes =  this.textFieldsContainer.getChildren();
        for ( Node node : nodes) {
            if (node instanceof TextField) {
                if (((TextField) node).getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Method intended to be called when a user looses.
     */
    public void showLooserAlert() {
        new LooserAlert().showAlert(
                "Perdedor",
                "Has perdido :(",
                "No has logrado advinar la palabra, intentalo de nuevo."
        );
    }


    /**
     * Method intended to be used when a user wins.
     */
    public void showWinnerAlert() {
        new WinnerAlert().showAlert(
                "Ganador",
                "¡Eres el ganador!",
                "¡Felicitaciones, has logrado adivinar la palabra!"
        );
    }
}

