package com.example.elsoleclipsado.model.interfaces;

public interface HelpInterface {
    final int MAXUSES = 3;

    boolean isAvailable();
    int getHelp(int wordLength);

}
