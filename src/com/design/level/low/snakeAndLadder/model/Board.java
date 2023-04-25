package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;

public class Board {
    private int boardId;
    private int size;
    private Cell[][] cells;
    private Cell winningCell;
    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        this.initializeCells();
        this.winningCell = this.cells[size-1][size-1];
    }
    private void initializeCells() {
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                this.cells[row][col] = new Cell(row, col, size);
            }
        }
    }
    public int getSize() {
        return size;
    }
    public Cell[][] getCells() {
        return cells;
    }

    public int getBoardId() {
        return this.boardId;
    }

    public Cell getWinningCell() {
        return winningCell;
    }
}
