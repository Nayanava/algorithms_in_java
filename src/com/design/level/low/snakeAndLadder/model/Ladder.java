package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;

public class Ladder extends Jump{
    public Ladder(Cell toCell) {
        super(JumpType.LADDER, toCell);
    }
}
