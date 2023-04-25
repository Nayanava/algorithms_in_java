package com.leetcode.medium;

import java.util.*;

public class SnakeGame {
    private List<int[]> snakeOccupiedPositions;
    private Set<Integer> snakePositionsAsSet;
    private int cols;
    private int rows;
    private Map<String, int[]> movesMap;
    private int[][] food;
    private int lengthOfSnake;
    public SnakeGame(int width, int height, int[][] food) {
        this.snakeOccupiedPositions = new LinkedList<>();
        this.snakeOccupiedPositions.add(new int[]{0, 0});
        this.snakePositionsAsSet = new HashSet<>();
        this.snakePositionsAsSet.add(flattenedPosition(0, 0));
        this.populateMovesMap();
        this.rows = height;
        this.cols = width;
        this.lengthOfSnake = 1;
        this.food = food;
    }
    
    public int move(String direction) {
        int currentPosition[] = this.snakeOccupiedPositions.get(this.lengthOfSnake - 1);
        
        int[] move = this.movesMap.get(direction);
        int newRow = currentPosition[0] + move[0];
        int newCol = currentPosition[1] + move[1];

        if(lengthOfSnake <= this.food.length && newRow == this.food[lengthOfSnake-1][0] && newCol == this.food[lengthOfSnake-1][1]) {
            lengthOfSnake++;
        } else {
            int[] position = this.snakeOccupiedPositions.remove(0);
            this.snakePositionsAsSet.remove(this.flattenedPosition(position[0], position[1]));
        }
        if(!isPossibleMove(newRow, newCol)) {
            return -1;
        }

        this.snakeOccupiedPositions.add(new int[] {newRow, newCol});
        this.snakePositionsAsSet.add(this.flattenedPosition(newRow, newCol));

        return lengthOfSnake-1;
    }

    private int flattenedPosition(int row, int col) {
        return row * this.cols + col;
    }
    private void populateMovesMap() {
        if(this.movesMap == null) {
            this.movesMap = new HashMap<>();
        }
        this.movesMap.put("R", new int[] {0, 1});
        this.movesMap.put("L", new int[] {0, -1});
        this.movesMap.put("U", new int[] {-1, 0});
        this.movesMap.put("D", new int[] {1, 0});
    }

    private boolean isPossibleMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && !this.snakePositionsAsSet.contains(this.flattenedPosition(row, col));
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3,3,new int[][] {{2,0},{0,0},{0,2},{2,2}});
        String[] moves = {"D","D","R","U","U","L","D","R","R","U","L","D"};

        for(String move : moves) {
            System.out.println(snakeGame.move(move));
        }
    }
}
/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */