package com.leetcode.hard.dynamic.programming.mergeintervals;

class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int dp[][][] = new int[n][n][2];
        return dfs(arr, 0, n-1, dp)[1];
    }
    
    
    private int[] dfs(int[] arr, int left, int right, int[][][] dp) {
        if(left > right)
            return new int[]{1, 0};
        if(left == right) {
            return new int[]{arr[left], 0};
        }
        if(dp[left][right][1] != 0)
            return dp[left][right];
        dp[left][right][1] = Integer.MAX_VALUE;
        for(int i = left; i < right; i++) {
            int[] leftSub = dfs(arr, left, i, dp);
            int[] rightSub = dfs(arr, i+1, right, dp);
            dp[left][right][1] = Math.min(dp[left][right][1], leftSub[1] + rightSub[1] + leftSub[0]*rightSub[0]);
            
            dp[left][right][0] = Math.max(leftSub[0], rightSub[0]);
        }
        return dp[left][right];
    }

    public static void main(String[] args) {
        int[] arr = {6, 2, 4};
        Solution sol = new Solution();
        System.out.println(sol.mctFromLeafValues(arr));
    }
}