package com.leetcode.hard.backtracking;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int shortestPath(int[][] grid, int k) {
        if(grid.length == 0)
            return 0;
        Map<String, Integer> map = new HashMap<>();
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        if(grid[0][0] == 1)
            k= k-1;
        int min = dfs(grid, 0, 0, k, map, visited);
        return  min == Integer.MAX_VALUE ? -1 : min;
    }

    int[] row = { 0, 1, 0, -1};
    int[] col = { 1, 0, -1, 0};
    private int dfs(int[][] grid, int startI, int startJ, int k, Map<String, Integer> map, boolean visited[][]) {
        if(k < 0)
            return Integer.MAX_VALUE;
        if(startI == grid.length-1 && startJ == grid[0].length-1) {
            return 0;
        }
        String key = startI + "_" + startJ + "_" + k;
        if(map.containsKey(key)) {
            return map.get(key);
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++) {
            int currRow = startI + row[i];
            int currCol = startJ + col[i];
            if(isSafe(grid, currRow, currCol, visited)) {
                int temp = Integer.MAX_VALUE;
                if(grid[currRow][currCol] == 1) {
                    if(k > 0){
                        visited[currRow][currCol] = true;
                        temp = dfs(grid, currRow, currCol, k-1, map, visited);
                    }
                    else
                        continue;
                }
                else {
                    visited[currRow][currCol] = true;
                    temp = dfs(grid, currRow, currCol, k, map, visited);
                }
                //System.out.println(temp);
                if(temp != Integer.MAX_VALUE) {
                    min = Math.min(min, temp+1);
                }
                visited[currRow][currCol] = false;
            }
        }
        map.put(key, min);
        return min;
    }

    boolean isSafe(int grid[][], int i, int j, boolean[][] visited) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && !visited[i][j];
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,0,0,0,1,1},{1,1,1,1,1,0,0,1,0,0,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,0,1,1,1,0,1,0,0,0},{1,0,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,1,0,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0},{0,0,1,0,0,1,0,1,0,0,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,1,0,0},{1,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},{0,1,0,1,0,0,0,1,0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1},{0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,1,1,0,1,1,1,0,0,1,1,1,1,1,0,1,0,0,1,0},{1,0,0,1,0,0,0,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,0,0,1,0,0,1,0,1},{1,1,1,0,0,1,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,0,1,0}};
        int k = 283;
        Solution s =  new Solution();
        System.out.println(s.shortestPath(grid, k));
    }
}

//[[0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,0,0,0,1,1],[1,1,1,1,1,0,0,1,0,0,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,0,1,1,1,0,1,0,0,0],[1,0,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,1,0,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0],[0,0,1,0,0,1,0,1,0,0,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,1,0,0],[1,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0],[0,1,0,1,0,0,0,1,0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1],[0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,1,1,0,1,1,1,0,0,1,1,1,1,1,0,1,0,0,1,0],[1,0,0,1,0,0,0,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,0,0,1,0,0,1,0,1],[1,1,1,0,0,1,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,0,1,0]]
//        283