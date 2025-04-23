package com.example.tabletopbuddy;

import java.util.Random;

public class Roller extends Random {
    public Roller(){};

    // Private helper function
    private int rollDie(Dice die) {
        return this.nextInt(die.getSides()) + 1;
    }


}
