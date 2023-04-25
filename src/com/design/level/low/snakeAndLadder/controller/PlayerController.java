package com.design.level.low.snakeAndLadder.controller;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.model.Player;
import com.design.level.low.snakeAndLadder.service.IGameManager;

public class PlayerController {
    IGameManager gameManager;
    public PlayerController(IGameManager gameManager) {
        this.gameManager = gameManager;
    }
    public Player registerToGame(int gameId, Player player) {
        return gameManager.registerPlayer(gameId, player);
    }

}
