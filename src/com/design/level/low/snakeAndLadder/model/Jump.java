package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;

public abstract class Jump {
    private JumpType jumpType;
    private Cell toCell;

    public Jump(JumpType jumpType, Cell toCell) {
        this.jumpType = jumpType;
        this.toCell = toCell;
    }

    public Cell getToCell() {
        return toCell;
    }
}
