package com.example.elsoleclipsado.model;

import com.example.elsoleclipsado.model.interfaces.HelpInterface;

import java.lang.reflect.Array;
import java.util.*;

public class HelpModel implements HelpInterface {

    private Set<Integer> indexesRevealed;


    public HelpModel() {
        indexesRevealed = new HashSet<>();
    }


    public boolean isAvailable() {

        return MAXUSES != indexesRevealed.size();
    }


    public int getHelp(int wordLength) {

        Random rand = new Random(System.currentTimeMillis());
        int nextInt;

        do {
            nextInt = rand.nextInt(wordLength);
        } while (indexesRevealed.contains(nextInt));

        indexesRevealed.add(nextInt);
        return nextInt;

    }
}
