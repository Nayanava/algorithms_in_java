package com.design.level.low.snakeAndLadder.model;
/**
 * @author nayanava
 */

import java.io.*;

public class Player {
    private int playerId;
    private String playerName;
    private Cell playerPosition;
    public Player(int playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerPosition = new Cell(0, 0);
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }
    public void setPlayerPosition(Cell position) {
        this.playerPosition = playerPosition;
    }
    public Cell getPlayerPosition() {
        return this.playerPosition;
    }
}
