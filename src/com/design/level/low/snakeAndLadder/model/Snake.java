package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;

public class Snake extends Jump{
    public Snake(Cell toCell) {
        super(JumpType.SNAKE, toCell);
    }
}
