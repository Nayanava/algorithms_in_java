package com.leetcode.hard.dynamic.programming;

public class PaintHousesII {
    public int minCostII(int[][] costs) {
        int m = costs.length;
        int n = costs[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < m; i++) {
            int minArray[] = minArray(dp[i - 1]);
            for (int j = 0; j < n; j++) {
                dp[i][j] = minArray[j] + costs[i][j];
            }
        }
        int min = dp[m - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(dp[m - 1][i], min);
        }
        return min;
    }

    private int[] minArray(int arr[]) {
        int n = arr.length;
        int minArray[] = new int[n];
        minArray[0] = minArray[n - 1] = Integer.MAX_VALUE;
        int min = arr[0];
        for (int i = 1; i < n; i++) {
            minArray[i] = min;
            min = Math.min(min, arr[i]);
        }
        min = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minArray[i] = Math.min(min, minArray[i]);
            min = Math.min(min, arr[i]);
        }
        return minArray;
    }

}
