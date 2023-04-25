package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.util.Random;

public class Dice {
    int maximumValue;
    public Dice(int value) {
        this.maximumValue = value;
    }
    public int rollDice() {
        Random random = new Random();
        return random.nextInt(maximumValue) + 1;
    }
}
