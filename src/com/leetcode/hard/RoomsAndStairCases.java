package com.leetcode.hard;

import java.util.Scanner;

public class RoomsAndStairCases {


    private static int[] move_x = {-1, 1, 0, 0};
    private static int[] move_y = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int numberOfRows = myScanner.nextInt();
        String moves[];
        boolean visited[][];
        int blockSize;
        for(int r = 1; r <= numberOfRows; r++) {
            blockSize = myScanner.nextInt();
            moves = (myScanner.next().split(""));
            visited = new boolean[2][blockSize];
            Count count = new Count();
            count.count = 0;
            for(int i = 0; i < 2; i ++) {
                for (int j = blockSize - 1; j >= 0; j--) {
                    dfs(visited, moves, i, j, 0, count);
                }
                if(count.count == 2*blockSize){
                    break;
                }
            }
            System.out.println(count.count);
        }
    }

    private static void dfs(boolean visited[][], String[] moves, int i, int j, int count, Count maxCount) {

        if(visited[i][j]) {
            return;
        }

        count += 1;
        visited[i][j] = true;

        if(count > maxCount.count)
            maxCount.count = count;

        for( int k = 0; k < move_x.length; k++) {
            if(isValidMove(visited, moves, i + move_y[k], j + move_x[k], move_y[k] != 0)) {
                dfs(visited, moves, i + move_y[k], j + move_x[k], count, maxCount);
            }
        }
        visited[i][j] = false;
    }

    private static boolean isValidMove(boolean visited[][], String[] moves, int i, int j, boolean checkMoves) {
        boolean validMove = (i >= 0 && i < 2 && j >= 0 && j < moves.length && !visited[i][j]);

        return  validMove && (checkMoves ? moves[j].equals("1") : true);
    }


    private static class Count {
        private int count;
    }

}
