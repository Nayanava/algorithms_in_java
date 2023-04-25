package com.leetcode.hard.backtracking;

public class AllZeroMatrix {

    int minCount = Integer.MAX_VALUE;
    int row[] = {0, 0, -1, 1};
    int col[] = {1, -1, 0, 0};

    public int minFlips(int[][] mat) {
        if (mat.length == 0)
            return 0;
        dfs(mat, 0, 0, 0);

        return minCount;
    }

    private void dfs(int[][] mat, int startI, int startJ, int count) {
        if (allZeroMat(mat)) {
            minCount = Math.min(minCount, count);
            return;
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    flipMat(mat, i, j);
                    dfs(mat, i, j + 1, count + 1);
                    flipMat(mat, i, j);

                }
            }
        }
    }

    private boolean isSafe(int[][] mat, int i, int j) {
        return i >= 0 && i < mat.length && j >= 0 && j < mat[0].length && mat[i][j] == 1;
    }

    private boolean allZeroMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    private void flipMat(int[][] mat, int i, int j) {
        int flipValue = mat[i][j] == 1 ? 0 : 1;
        mat[i][j] = flipValue;

        for (int k = 0; k < 4; k++) {
            int currRow = i + row[k];
            int currCol = j + col[k];

            if (isSafe(mat, currRow, currCol)) {
                mat[currRow][currCol] = flipValue;
            }
        }
    }

    public static void main(String[] args) {
        int mat[][] = {{0, 0}, {0, 1}};
        AllZeroMatrix azm = new AllZeroMatrix();
        System.out.print(azm.minFlips(mat));
    }
}
