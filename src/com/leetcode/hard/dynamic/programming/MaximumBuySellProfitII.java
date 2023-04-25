package com.leetcode.hard.dynamic.programming;

public class MaximumBuySellProfitII {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if( n == 0) {
            return 0;
        }

        int[][] dp = new int[3][n];
        int j = 1;
        for(int i = 1; i < 3; i++) {
            int maxDiff = -prices[0];
            for(j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], maxDiff + prices[j]);
                maxDiff = Math.max(maxDiff, dp[i-1][j-1] - prices[j]);
            }
        }

        return dp[2][n-1];
    }

    public static void main(String[] args) {
        int arr[] = {7,6,4,3,1};
        MaximumBuySellProfitII mbs = new MaximumBuySellProfitII();
        System.out.println(mbs.maxProfit(arr));
    }
}
