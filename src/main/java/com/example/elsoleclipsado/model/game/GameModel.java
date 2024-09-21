package com.example.elsoleclipsado.model.game;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameModel class represents the logic of the game "El Sol Eclipsado".
 * @author Mateo Noguera
 * @version 1.0
 */
public class GameModel {
    private final String secretWord;
    private final int MAXATTEMPTS;
    private int currentAttempts;

    private final HelpModel helpService;

    /**
     * Constructor for the GameModel class.
     * Initializes the game with a given secret word and sets up the number of attempts and the help service.
     * @param secretWord the word that the player must guess.
     */
    public GameModel(String secretWord) {
        this.secretWord = secretWord;
        currentAttempts = 0;
        MAXATTEMPTS = 5;
        helpService = new HelpModel();
    }

    /**
     * Gets the length of the secret word.
     * @return the length of the secret word.
     */
    public int getSecretWordLength() {
        return secretWord.length();
    }

    /**
     * Gets the maximum number of attempts allowed.
     * @return the maximum number of attempts.
     */
    public int getMaxAttempts() {
        return MAXATTEMPTS;
    }

    /**
     * Gets the current number of attempts used.
     * @return the current number of attempts.
     */
    public int getCurrentAttempts() {
        return currentAttempts;
    }

    /**
     * Gets the remaining number of attempts.
     * @return the number of remaining attempts.
     */
    public int getRemainingAttempts() {
        return MAXATTEMPTS - currentAttempts;
    }

    /**
     * Determines if the player has lost the game by running out of attempts.
     * @return true if no attempts remain, false otherwise.
     */
    public boolean isLooser() {
        return getRemainingAttempts() == 0;
    }

    /**
     * Checks if the secret word contains the input letter, ignoring accents and case.
     * Increases the attempt count if the secret word doesn't contain the letter.
     * @param letter: the letter entered by the player.
     * @return true or false
     */
    public boolean isCorrectInputLetter(String letter) {
        String noAccentSecretWord = removeAccents(secretWord);
        String noAccentInputLetter = removeAccents(letter);

        if (noAccentSecretWord.contains(noAccentInputLetter)) {
            return true;
        }
        currentAttempts++;
        return false;
    }

    /**
     * Returns a list of integers containing the indexes where the input letter
     * appears in the secret word.
     * @param letter
     * @return List of integers
     */
    public List<Integer> getLetterIndexes(String letter) {
        List<Integer> indexes = new ArrayList<>();
        String noAccentSecretWord = removeAccents(secretWord);
        String noAccentInputLetter = removeAccents(letter);

        for (int i = 0; i < noAccentSecretWord.length(); i++) {
            if (String.valueOf(noAccentSecretWord.charAt(i)).equalsIgnoreCase(noAccentInputLetter)) {
                indexes.add(i);
            }
        }

        return indexes;
    }

    /**
     * Removes accents from a given word.
     * @param word the word from which to remove accents.
     * @return the word without accents.
     */
    public String removeAccents(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }


    /**
     * Reveals a random letter from the secret word using the help service.
     * Throws an exception if no more help is available.
     *
     * @return a RevealedLetter object containing the letter and its position.
     * @throws Exception if the help service has reached its limit.
     */
    public RevealedLetter revealLetter() throws Exception{
        if (!helpService.isAvailable()) {
            throw new Exception("Haz alcanzado el límite de ayudas");
        }
        int randomIndex = helpService.getHelp(secretWord.length());
        char randomLetter = secretWord.charAt(randomIndex);

        return new RevealedLetter(randomLetter, randomIndex);
    }


    /**
     * Marks the help service as used, so the amount of uses of the
     * help service decreases by one
     */
    public void markUsedHelp() {
        this.helpService.markUsed();
    }

    /**
     * Gets the amount of remaining amount of helps
     * @return int
     */
    public int getRemainingHelps() {
        return this.helpService.getUses();
    }
}
