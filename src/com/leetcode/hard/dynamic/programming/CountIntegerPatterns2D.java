package com.leetcode.hard.dynamic.programming;

public class CountIntegerPatterns2D {
    public int findTotalCountPatterns(int N) {
        int[][] dp = new int[N+1][N];
        dp[0][0] = 1;
        for(int i = 0; i <= N; i++) {
            for (int j = 1; j <= N-1; j++) {
                dp[i][j] = dp[i][j-1];
                if(i >= j)
                    dp[i][j] = dp[i][j-1] + dp[i-j][j];
            }
        }
        return dp[N][N-1];
    }



    public static void main(String[] args) {
        int N = 10;
        System.out.print(new CountIntegerPatterns().findTotalCountPatterns(N));
    }
}
