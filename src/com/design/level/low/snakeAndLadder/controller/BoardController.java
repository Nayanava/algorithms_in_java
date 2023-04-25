package com.design.level.low.snakeAndLadder.controller;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.Board;
import com.design.level.low.snakeAndLadder.model.JumpsPair;
import com.design.level.low.snakeAndLadder.service.IBoardManager;

import java.util.List;

public class BoardController {
    IBoardManager boardManager;
    public BoardController(IBoardManager boardManager) {
        this.boardManager = boardManager;
    }
    public Board configureBoard(int boardSize,
                                List<JumpsPair> snakes,
                                List<JumpsPair> ladders) {
        return boardManager.configureBoard(boardSize, snakes, ladders);
    }
}
