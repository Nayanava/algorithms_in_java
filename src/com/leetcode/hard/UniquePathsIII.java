package com.leetcode.hard;

class UniquePathsIII {

    private static int[] x_axis = {1, -1, 0, 0};
    private static int[] y_axis = {0, 0, 1, -1};


    public int uniquePathsIII(int[][] grid) {
        CalculationDetails calculationDetails = new CalculationDetails();
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        int i, j = 0;
        boolean flag = false;
        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        countObstacles(grid, calculationDetails);
        dfs_visit(grid, visited, i, j, 0, calculationDetails);

        return calculationDetails.pathCount;
    }

    private void reset(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                visited[i][j] = false;
            }
        }
    }

    private void dfs_visit(int[][] arr, boolean visited[][], int i, int j, int visitedCount, CalculationDetails calculationDetails) {

        if (arr[i][j] == 2) {
            if (visitedCount + calculationDetails.obstaclesCount + 1 == arr.length * arr[0].length) {
                calculationDetails.pathCount++;
            }
            return;
        }

        visited[i][j] = true;
        visitedCount++;
        for (int it = 0; it < x_axis.length; it++) {
            if (isValidWalk(arr, visited, i + x_axis[it], j + y_axis[it])) {
                dfs_visit(arr, visited, i + x_axis[it], j + y_axis[it], visitedCount, calculationDetails);
                if (arr[i][j] == 1) {
                    reset(visited);
                    visited[i][j] = true;
                }
            }
        }
        visited[i][j] = false;
    }

    private void countObstacles(int grid[][], CalculationDetails calculationDetails) {
        for( int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == -1) {
                    calculationDetails.obstaclesCount++;
                }
            }
        }
    }

    private boolean isValidWalk(int[][] arr, boolean[][] visited, int x, int y) {
        return x >= 0 && y >= 0 && x < arr.length && y < arr[0].length && arr[x][y] != -1 && !visited[x][y];
    }

    public static void main(String[] args) {
        int arr[][] =
        arr = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        UniquePathsIII uniquePathsIII = new UniquePathsIII();
        System.out.println(uniquePathsIII.uniquePathsIII(arr));
    }

    private static class CalculationDetails {
        private int obstaclesCount;
        private int pathCount;
    }
}