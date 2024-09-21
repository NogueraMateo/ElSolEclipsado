package com.example.elsoleclipsado.model.game;

/**
 * Class used as a data structure to return a letter and its index
 * @author Mateo Noguera Pinto
 * @version 1.0
 */
public class RevealedLetter {

    private char letter;
    private int index;

    /**
     * Simple constructor
     * @param letter : string
     * @param index : int
     */
    public RevealedLetter(char letter, int index) {
        this.letter = letter;
        this.index = index;
    }

    /**
     * Simple getter of the letter
     * @return String, the letter
     */
    public String getLetter() {
        return String.valueOf(letter);
    }

    /**
     * Simple getter of the index
     * @return integer, the index
     */
    public int getIndex() {
        return index;
    }
}
