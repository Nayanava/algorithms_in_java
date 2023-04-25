package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;

public class JumpsPair {
    Cell fromCell;
    Cell toCell;

    public JumpsPair(Cell fromCell, Cell toCell) {
        this.fromCell = fromCell;
        this.toCell = toCell;
    }

    public Cell fromCell() {
        return fromCell;
    }

    public JumpsPair setFromCell(Cell fromCell) {
        this.fromCell = fromCell;
        return this;
    }

    public Cell toCell() {
        return toCell;
    }

    public JumpsPair setToCell(Cell toCell) {
        this.toCell = toCell;
        return this;
    }
}
