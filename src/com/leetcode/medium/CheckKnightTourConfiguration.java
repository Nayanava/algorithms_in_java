package com.leetcode.medium;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class CheckKnightTourConfiguration {
    private int[] row = {-2, -2, 2, 2, 1, 1, -1, -1};
    private int[] col = {-1, 1, -1, 1, -2, 2, -2, 2};
    public boolean checkValidGrid(int[][] grid) {
        if(grid[0][0] != 0) {
            return false;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        int next = 0;
        while(!q.isEmpty()) {
            int[] position = q.poll();
            next += 1;
            boolean hasValidMove = false;
            for(int i = 0; i < 8; i++) {
                int nextRow = position[0] + row[i];
                int nextCol = position[1] + col[i];

                if(isValidPosition(nextRow, nextCol, grid, next)) {
                    hasValidMove = true;
                    q.offer(new int[] {nextRow, nextCol});
                    break;
                }
            }
            if(!hasValidMove) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPosition(int row, int col, int[][] grid, int next) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == next;
    }

    public static void main(String[] args) {
        int[][] mat = {{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}};
        System.out.println(new CheckKnightTourConfiguration().checkValidGrid(mat));
    }
}
