package com.design.level.low.snakeAndLadder.service;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.*;

import java.io.InvalidObjectException;
import java.util.List;

public interface IGameManager {
    Game initializeGame(int boardId, Dice dice);
    Player registerPlayer(int gameId, Player player);
    void updateGameState(int gameId, GameState gameState);
    Cell updatePlayerPosition(int gameId, int playerId, int diceRoll) throws InvalidObjectException;
    boolean hasAWinner(int gameId, Cell currentCell);
}
