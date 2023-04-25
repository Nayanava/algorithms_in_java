package com.leetcode.hard.dynamic.programming;

public class CountIntegerPatterns {
    public int findTotalCountPatterns(int N) {
        int[] dp = new int[N+1];
        dp[0] = 1;
        for (int j = 1; j <= N-1; j++) {
            for(int i = 0; i <= N; i++) {
                if(i >= j)
                    dp[i] += dp[i-j];
            }
        }
        return dp[N];
    }



    public static void main(String[] args) {
        int N = 10;
        System.out.print(new CountIntegerPatterns().findTotalCountPatterns(N));
    }
}
