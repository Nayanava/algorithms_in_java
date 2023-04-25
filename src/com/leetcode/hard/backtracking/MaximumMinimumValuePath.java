package com.leetcode.hard.backtracking;

public class MaximumMinimumValuePath {
    int minMax = 0;
    int m, n;
    int row[] = {0, 0, -1, 1};
    int col[] = {-1, 1, 0, 0};
    public int maximumMinimumPath(int[][] A) {
        m = A.length;
        if(m == 0) {
            return 0;
        }
        n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        dfs(A, 0, 0, A[0][0], visited);
        return minMax;
    }

    private void dfs(int[][] grid, int i, int j, int min, boolean[][] visited) {
        if(i == m-1 && j == n-1) {
            minMax = min;
        }
        visited[i][j] = true;
        for(int x = 0; x < 4; x++) {
            int nextRow = i + row[x];
            int nextCol = j + col[x];
            if(isSafe(grid, nextRow, nextCol, visited)) {
                dfs(grid, nextRow, nextCol, Math.min(min, grid[nextRow][nextCol]), visited);
            }
        }
        visited[i][j] = false;
    }
    private boolean isSafe(int[][] grid, int i, int j, boolean[][] visited) {
        return i >= 0 && j >= 0 && i < m && j < n && !visited[i][j] && grid[i][j] >= minMax;
    }

    public static void main(String[] args) {
        int[][] arr = {{0,0,0,0,0,1,0},{0,1,0,1,1,0,1},{0,1,1,1,1,0,0},{0,1,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,1}};
        MaximumMinimumValuePath mmvp = new MaximumMinimumValuePath();
        System.out.println(mmvp.maximumMinimumPath(arr));
    }
}
