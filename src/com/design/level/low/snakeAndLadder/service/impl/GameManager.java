package com.design.level.low.snakeAndLadder.service.impl;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.*;
import com.design.level.low.snakeAndLadder.model.builder.GameBuilder;
import com.design.level.low.snakeAndLadder.service.IBoardManager;
import com.design.level.low.snakeAndLadder.service.IGameManager;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager implements IGameManager {

    private IBoardManager boardManager;
    private static GameManager INSTANCE;
    private Map<Integer, Game> games;
    private GameManager(IBoardManager boardManager) {
        this.boardManager = boardManager;
        this.games = new HashMap<>();
    }

    public static GameManager getInstance(IBoardManager boardManager) {
        if(INSTANCE == null) {
            synchronized (GameManager.class) {
                if(INSTANCE == null) {
                    INSTANCE = new GameManager(boardManager);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Game initializeGame(int boardId, Dice dice) {
        Game game = new GameBuilder().setBoard(boardId)
                .setGameState(GameState.INITIALIZING)
                .setDice(dice)
                .build();
        this.games.put(game.getGameId(), game);
        return game;
    }

    @Override
    public Player registerPlayer(int gameId, Player player) {
        this.games.get(gameId).addPlayer(player);
        return player;
    }

    @Override
    public void updateGameState(int gameId, GameState gameState) {
        this.games.get(gameId).setGameState(gameState);
    }

    @Override
    public Cell updatePlayerPosition(int gameId, int playerId, int diceRoll) throws InvalidObjectException {
        Game game = this.games.get(gameId);
        Player player = game.getPlayer(playerId);
        if(player == null) {
            throw new InvalidObjectException("Invalid player Id");
        }
        Cell playerPosition = player.getPlayerPosition();
        Board board = boardManager.getBoardById(game.getBoardId());
        int boardSize = board.getSize();
        int row = diceRoll / boardSize;
        int col = diceRoll % boardSize;

        Cell newPosition = boardManager.getCellByRowAndColumn(
                board.getBoardId(),
                playerPosition.getRow() + row,
                playerPosition.getCol() + col);
        if(newPosition == null) {
            return playerPosition;
        }
        player.setPlayerPosition(newPosition);
        return newPosition;
    }

    @Override
    public boolean hasAWinner(int gameId, Cell currentCell) {
        int boardId = games.get(gameId).getBoardId();
        Cell winningCell = boardManager.getWinningCell(boardId);
        return winningCell.equals(currentCell);
    }
}
