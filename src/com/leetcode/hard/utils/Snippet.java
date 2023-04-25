package com.leetcode.hard.utils;

public class Snippet {
    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int dp[] = new int[n];
            return minCostRecursive(cost, n, dp);
        }

        private int minCostRecursive(int[] cost, int n, int[] dp) {
            if(n==1)
                return cost[n-1];
            if( n <= 0)
                return Integer.MAX_VALUE;
            if(dp[n-1] != -1) {
                return dp[n-1];
            }
            dp[n-1] = Math.min(minCostRecursive(cost, n-1, dp), minCostRecursive(cost, n-2, dp)) + cost[n-1];
            return dp[n-1];
        }

        private int minCostTab(int[] cost, int n) {
            int dp[] = new int[n+1];
            dp[0] = Integer.MAX_VALUE;
            dp[1] = cost[0];
            for(int i = 2; i <= n; i++) {
                dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i-1];
            }
            return dp[n];
        }
    }
}
