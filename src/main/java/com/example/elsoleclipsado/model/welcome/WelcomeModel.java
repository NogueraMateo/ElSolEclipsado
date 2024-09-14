package com.example.elsoleclipsado.model.welcome;

import com.example.elsoleclipsado.model.adapters.TypingControlAdapter;

public class WelcomeModel extends TypingControlAdapter {


    public boolean minimumLengthReached(String word) {

        return word.length() >= MINLENGTH;

    }


    public boolean maximumLengthReached(String word) {

        return word.length() == MAXLENGTH;

    }
}
