package com.example.elsoleclipsado.model.game;

public class RevealedLetter {

    private char letter;
    private int index;

    public RevealedLetter(char letter, int index) {
        this.letter = letter;
        this.index = index;
    }

    public char getLetter() {
        return letter;
    }

    public int getIndex() {
        return index;
    }
}
