package com.example.elsoleclipsado.model;

import java.text.Normalizer;

public class GameModel {
    private final String secretWord;
    private final int MAXATTEMPTS;
    private int currentAttempts;

    private HelpModel helpService;

    public GameModel(String secretWord) {
        this.secretWord = secretWord;
        currentAttempts = 0;
        MAXATTEMPTS = 5;
        helpService = new HelpModel();
    }


    public int getSecretWordLength() {
        return secretWord.length();
    }


    public int getMaxAttempts() {
        return MAXATTEMPTS;
    }


    public int getCurrentAttempts() {
        return currentAttempts;
    }


    public int getRemainingAttempts() {
        return MAXATTEMPTS - currentAttempts;
    }


    public boolean isLooser() {
        return getRemainingAttempts() == 0;
    }


    public boolean isCorrectSecretWord(String word) {
        String noAccentSecretWord = removeAccents(secretWord);
        String noAccentInputWord = removeAccents(word);

        if (noAccentInputWord.equalsIgnoreCase(noAccentSecretWord)) {
            return true;
        }
        currentAttempts++;
        return false;
    }


    public String removeAccents(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }


    public RevealedLetter revealLetter() throws Exception{
        if (!helpService.isAvailable()) {
            throw new Exception("Haz alcanzado el límite de ayudas");
        }
        int randomIndex = helpService.getHelp(secretWord.length());
        char randomLetter = secretWord.charAt(randomIndex);

        return new RevealedLetter(randomLetter, randomIndex);
    }
}
