package com.design.level.low.snakeAndLadder.service;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.Board;
import com.design.level.low.snakeAndLadder.model.Cell;
import com.design.level.low.snakeAndLadder.model.JumpsPair;

import java.io.*;
import java.util.List;

public interface IBoardManager {
    Board configureBoard(int boardSize,
                         List<JumpsPair> ladders,
                         List<JumpsPair> snakes);
    Board getBoardById(int boardId);
    Cell getCellByRowAndColumn(int boardId, int row, int col) throws InvalidObjectException;
    Cell getWinningCell(int boardId);
}
