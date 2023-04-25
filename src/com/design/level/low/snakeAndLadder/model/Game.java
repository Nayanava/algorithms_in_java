package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    private int gameId;
    private int boardId;
    private GameState gameState;
    private Player winner;
    private Dice dice;
    private Map<Integer, Player> playersMap = new HashMap<>();

    public Game(int gameId, int boardId, Dice dice, GameState gameState, Player winner) {
        this.gameId = gameId;
        this.boardId = boardId;
        this.dice = dice;
        this.gameState = gameState;
        this.winner = winner;
        this.playersMap = new HashMap<>();
    }

    public int getGameId() {
        return gameId;
    }

    public int getBoardId() {
        return boardId;
    }

    public List<Player> getPlayers() {
        return (List<Player>) playersMap.values();
    }

    public Player getPlayer(int playerId) {
        return this.playersMap.getOrDefault(playerId, null);
    }
    public void addPlayer(Player player) {
        this.playersMap.put(player.getPlayerId(), player);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
