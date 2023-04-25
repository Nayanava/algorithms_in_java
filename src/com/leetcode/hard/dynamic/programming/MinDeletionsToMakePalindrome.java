package com.leetcode.hard.dynamic.programming;

public class MinDeletionsToMakePalindrome {
    public int minDeletions(String s) {
        return s.length() - LPS(s);
    }

    public int LPS(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i < n - L + 1; i++) {
                int j = i + L - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (L == 2) ? 2 : dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n-1];
    }
    public static void main(String[] args) {
    	String s = "aebcbda";
    	MinDeletionsToMakePalindrome mdmp = new MinDeletionsToMakePalindrome();
    	System.out.println(mdmp.minDeletions(s));
	}
}
