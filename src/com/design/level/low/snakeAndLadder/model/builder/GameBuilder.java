package com.design.level.low.snakeAndLadder.model.builder;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.*;

import java.io.*;
import java.util.List;

public class GameBuilder {
    private int gameId;
    private int boardId;
    private Dice dice;
    private GameState gameState;
    private Player winner;

    public GameBuilder setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }
    public GameBuilder setBoard(int boardId) {
        this.boardId = boardId;
        return this;
    }
    public GameBuilder setDice(Dice dice) {
        this.dice = dice;
        return this;
    }
    public GameBuilder setGameState(GameState gameState) {
        this.gameState = gameState;
        return this;
    }

    public GameBuilder setWinner(Player winner) {
        this.winner = winner;
        return this;
    }

    public Game build() {
        return new Game(gameId, boardId, dice, gameState, winner);
    }
}
