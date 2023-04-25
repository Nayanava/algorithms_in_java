package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.Objects;

public class Cell {
    private int row;
    private int col;
    private int number;

    private boolean hasJump;
    private Jump jump;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Cell(int row, int col, int boardSize) {
        this(row, col);
        this.number = (row+1) * boardSize + (col + 1);
    }
    public void setJump(Jump jump) {
        this.hasJump = true;
        this.jump = jump;
    }

    public Jump getJump() {
        return jump;
    }
    public boolean hasJump() {
        return hasJump;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell setRow(int row) {
        this.row = row;
        return this;
    }

    public Cell setCol(int col) {
        this.col = col;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
