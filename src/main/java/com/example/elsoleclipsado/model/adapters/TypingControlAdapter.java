package com.example.elsoleclipsado.model.adapters;

import com.example.elsoleclipsado.model.interfaces.TypingControlInterface;

public abstract class TypingControlAdapter implements TypingControlInterface {

    @Override
    public boolean isValidCharacter(String character) {
        return character.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]");
    }

    @Override
    public boolean minimumLengthReached(String word) {return false;}

    @Override
    public boolean maximumLengthReached(String word) {return false;}
}

