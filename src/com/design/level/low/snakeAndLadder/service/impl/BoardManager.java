package com.design.level.low.snakeAndLadder.service.impl;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.*;
import com.design.level.low.snakeAndLadder.service.IBoardManager;

import java.io.InvalidObjectException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardManager implements IBoardManager {

    private Map<Integer, Board> boards;

    private static BoardManager INSTANCE = new BoardManager();

    public static BoardManager getInstance() {
        return INSTANCE;
    }
    private BoardManager() {
        this.boards = new HashMap<>();
    }
    @Override
    public Board configureBoard(int boardSize,
                                List<JumpsPair> ladders,
                                List<JumpsPair> snakes) {
        Board board = new Board(boardSize);
        Cell[][] cells = board.getCells();

        for(JumpsPair ladder : ladders) {
            Cell fromCell = ladder.fromCell();
            Cell toCell = ladder.toCell();
            Jump jump = new Ladder(cells[toCell.getRow()][toCell.getCol()]);
            cells[fromCell.getRow()][fromCell.getCol()].setJump(jump);
        }
        for(JumpsPair snake : snakes) {
            Cell fromCell = snake.fromCell();
            Cell toCell = snake.toCell();
            Jump jump = new Snake(cells[toCell.getRow()][toCell.getCol()]);
            cells[fromCell.getRow()][fromCell.getCol()].setJump(jump);
        }
        this.boards.put(board.getBoardId(), board);
        return board;
    }

    @Override
    public Board getBoardById(int boardId) {
        return boards.getOrDefault(boardId, null);
    }

    @Override
    public Cell getCellByRowAndColumn(int boardId, int row, int col) throws InvalidObjectException {
        Board board = getBoardById(boardId);
        if(board == null) {
            throw new InvalidObjectException("Invalid boardId " + boardId);
        }
        if(row * board.getSize() + col > board.getSize()*board.getSize()) {
            return null;
        }
        Cell cell = board.getCells()[row][col];
        return cell.hasJump() ? cell.getJump().getToCell() : cell;
    }

    public Cell getWinningCell(int boardId) {
        return this.boards.get(boardId).getWinningCell();
    }
}
