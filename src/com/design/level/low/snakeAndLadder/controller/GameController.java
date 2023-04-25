package com.design.level.low.snakeAndLadder.controller;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.Cell;
import com.design.level.low.snakeAndLadder.model.Dice;
import com.design.level.low.snakeAndLadder.model.Game;
import com.design.level.low.snakeAndLadder.service.IGameManager;

import java.io.*;

public class GameController {
    IGameManager gameManager;
    public GameController(IGameManager gameManager) {
        this.gameManager = gameManager;
    }
    public Game initiateGame(int boardId, Dice dice) {
        return gameManager.initializeGame(boardId, dice);
    }

    public Cell updatePlayerPosition(int gameId, int playerId, int diceRoll) throws InvalidObjectException {
        return this.gameManager.updatePlayerPosition(gameId, playerId, diceRoll);
    }

    public boolean hasAWinner(int gameId, Cell currentCell) {
        return this.gameManager.hasAWinner(gameId, currentCell);
    }
}

