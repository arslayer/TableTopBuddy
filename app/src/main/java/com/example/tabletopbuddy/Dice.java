package com.example.tabletopbuddy;

// Class for creating dice objects.
public class Dice {
    private final int sides;

    // Default constructor
    public Dice() {
        this.sides = 6;
    }

    // Parameterized constructor
    public Dice(int sides) {
        this.sides = sides;
    }

    // Accessor method
    public int getSides() {
        return this.sides;
    }
}
