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

    private int uses = 3;
    /**
     * HelpModel constructor
     */
    public HelpModel() {
    }

    public int getUses() {
        return uses;
    }

    /**
     * Checks if the maximum number of helps has been reached by
     * the player, if so, returns false, true otherwise.
     * @return false or true.
     */
    public boolean isAvailable() {

        return uses > 0;
    }

    /**
     * Essential method that randomly returns an index to reveal.
     * If the index has been already revealed it tries again until a non-revealed index
     * is obtained.
     * @param wordLength : the length of the secret word
     * @return : a random integer
     */
    public int getHelp(int wordLength) {

        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(wordLength);

    }

    /**
     * Decreases the remaining uses by one
     */
    public void markUsed() {
        uses -= 1;
    }
}
