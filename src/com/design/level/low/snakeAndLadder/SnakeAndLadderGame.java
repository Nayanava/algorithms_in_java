package com.design.level.low.snakeAndLadder;
/**
 * @author nayanava
 */

import com.design.level.low.snakeAndLadder.controller.BoardController;
import com.design.level.low.snakeAndLadder.controller.GameController;
import com.design.level.low.snakeAndLadder.controller.PlayerController;
import com.design.level.low.snakeAndLadder.model.*;
import com.design.level.low.snakeAndLadder.service.IBoardManager;
import com.design.level.low.snakeAndLadder.service.IGameManager;
import com.design.level.low.snakeAndLadder.service.impl.BoardManager;
import com.design.level.low.snakeAndLadder.service.impl.GameManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SnakeAndLadderGame {

    //1. Access to game controller.
    //2. Access to board controller
    //3. Configure a board.
    //4. Configure a game
    //5. Register players to game.
    //6. Start the game and continue, till we have a winner
    public static void main(String[] args) throws InvalidObjectException {
        IBoardManager boardManager = BoardManager.getInstance();
        IGameManager gameManager = GameManager.getInstance(boardManager);

        GameController gameController = new GameController(gameManager);
        BoardController boardController = new BoardController(boardManager);
        PlayerController playerController = new PlayerController(gameManager);
        List<JumpsPair> snakes = new ArrayList<>();
        snakes.add(new JumpsPair(new Cell(5, 4), new Cell(2, 3)));
        snakes.add(new JumpsPair(new Cell(6, 3), new Cell(1, 2)));

        List<JumpsPair> ladders = new ArrayList<>();
        ladders.add(new JumpsPair(new Cell(2, 1), new Cell(5, 2)));
        ladders.add(new JumpsPair(new Cell(3, 4), new Cell(9, 6)));

        Board board = boardController.configureBoard(10, snakes, ladders);

        Dice dice = new Dice(6);
        Game game = gameController.initiateGame(board.getBoardId(), dice);

        Deque<Player> players = new LinkedList<>();
        players.offerLast(playerController.registerToGame(game.getGameId(), new Player(1, "A")));
        players.offerLast(playerController.registerToGame(game.getGameId(), new Player(2, "B")));
        players.offerLast(playerController.registerToGame(game.getGameId(), new Player(3, "C")));

        boolean hasAWinner = false;
        while(!hasAWinner) {
            Player player = players.pollFirst();
            int diceRollValue = dice.rollDice();
            Cell cell = gameController.updatePlayerPosition(game.getGameId(), player.getPlayerId(), diceRollValue);
            hasAWinner = gameController.hasAWinner(game.getGameId(), cell);
            if(hasAWinner) {
                gameManager.updateGameState(game.getGameId(), GameState.ENDED);
            }
            players.offerLast(player);
        }
    }
}
