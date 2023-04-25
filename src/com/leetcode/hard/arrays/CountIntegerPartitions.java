package com.leetcode.hard.arrays;

public class CountIntegerPartitions {
    public int countIntegerPartitions(int n) {
        int dp[] = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            for(int j = i; j <= n; j++) {
                dp[j] += dp[j-i];
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        int n = 5;
        CountIntegerPartitions cip = new CountIntegerPartitions();
        System.out.println(cip.countIntegerPartitions(n));
    }
}
