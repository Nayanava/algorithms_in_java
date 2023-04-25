package com.leetcode.hard.matrices;

public class NumberOfSquaresSumK {
    public int squaresThreshold(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i+1][j+1] = dp[i+1][j] + dp[i][j+1] -dp[i][j] + mat[i][j];
            }
        }
        int length = Math.min(m,n), res = 0;
        for(int i = 1; i <= length; i++) {
            res += countSquares(dp, i, k);
        }
        return res;
    }

    private int countSquares(int[][] dp, int len, int k) {
        int res = 0;
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(i < len || j < len) {
                    continue;
                }
                int sum = dp[i][j] - dp[i-len][j] - dp[i][j-len] + dp[i-len][j-len];
                if(sum < k) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int mat[][] = {{2, 1, 3, 4},
                        {-1, 2, 4, 2},
                        {6, 3, -10, 2}};

        int k = 6;
        NumberOfSquaresSumK obj = new NumberOfSquaresSumK();
        System.out.print(obj.squaresThreshold(mat, k));
    }
}

