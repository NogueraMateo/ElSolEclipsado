package com.example.elsoleclipsado.model.game;

import com.example.elsoleclipsado.model.interfaces.HelpInterface;

import java.util.*;

/**
 * Class in charge of providing helps for the user
 * Inside the GameModel acts as a service
 * @author Mateo Noguera
 * @version 1.0
 */
public class HelpModel implements HelpInterface {

    private Set<Integer> indexesRevealed;

    /**
     * HelpModel constructor
     */
    public HelpModel() {
        indexesRevealed = new HashSet<>();
    }

    /**
     * Checks if the maximum number of helps has been reached by
     * the player, if so, returns false, true otherwise.
     * @return false or true.
     */
    public boolean isAvailable() {

        return MAXUSES != indexesRevealed.size();
    }

    /**
     * Essential method that randomly returns an index to reveal.
     * If the index has been already revealed it tries again until a non-revealed index
     * is obtained.
     * @param wordLength
     * @return
     */
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
