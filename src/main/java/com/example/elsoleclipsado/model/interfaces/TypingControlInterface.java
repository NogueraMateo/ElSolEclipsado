package com.example.elsoleclipsado.model.interfaces;

public interface TypingControlInterface {

    int MINLENGTH = 6;
    int MAXLENGTH = 12;

    boolean isValidCharacter(String character);
    boolean minimumLengthReached(String word);
    boolean maximumLengthReached(String word);
}
