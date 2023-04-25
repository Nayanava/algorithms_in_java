package com.leetcode.hard.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CountAllPaths {
    int row[] = {0, 0, -1, 1};
    int col[] = {1, -1, 0, 0};
    int dfs(int[][] grid, int startI, int startJ, boolean visited[][], List<Integer> path, List<List<Integer>> paths) {
        if(startI == grid.length-1 && startJ == grid[0].length-1) {
            for(int i : path) {
                System.out.print(i + " ");
            }
            System.out.println();
            return 1;
        }
        visited[startI][startJ] = true;
        int count = 0;
        for(int i = 0; i < row.length; i++) {
            int currRow = row[i] + startI;
            int currCol = col[i] + startJ;
            if(isSafe(grid, currRow, currCol, visited)) {
                path.add(grid[currRow][currCol]);
                count += dfs(grid, currRow, currCol, visited, path, paths);
                path.remove(path.size()-1);
            }
        }
        visited[startI][startJ] = false;
        return count;
    }
    boolean isSafe(int[][] grid, int i, int j, boolean[][] visited) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !visited[i][j];
    }

    public static void main(String[] args) {
        int[][] grid = new int[3][4];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = (i)*4+j;
      //          System.out.print(grid[i][j] + " ");
            }
        //    System.out.println();
        }
        CountAllPaths cap = new CountAllPaths();
        boolean[][] visited = new boolean[3][4];
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();
        path.add(grid[0][0]);
        System.out.println(cap.dfs(grid, 0, 0, visited, path, paths));
        System.out.println(paths.size());
//        for(List<Integer> list : paths) {
//            for(Integer i : list) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
    }
}
