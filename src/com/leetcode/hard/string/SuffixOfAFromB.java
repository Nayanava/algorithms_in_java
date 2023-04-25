package com.leetcode.hard.string;

import java.util.HashSet;
import java.util.Set;

public class SuffixOfAFromB {
    public int findMinSuffix(String A, String B) {
        Set<String> dict = new HashSet<>();
        for (int i = 0; i < B.length(); i++) {
            dict.add(B.substring(i));
        }
        int[] dp = new int[A.length()];
        return findMinSuffix(A, 0, dict, dp);
    }

    private int findMinSuffix(String A, int start, Set<String> dict, int[] dp) {
        if (start == A.length()) {
            return 0;
        }
        if (dp[start] != 0) {
            return dp[start];
        }
        int min = Integer.MAX_VALUE;
        for (int end = start + 1; end <= A.length(); end++) {
            if (dict.contains(A.substring(start, end))) {
                int temp = findMinSuffix(A, end, dict, dp);
                if (temp != Integer.MAX_VALUE) {
                    min = Math.min(temp + 1, min);
                }
            }
        }
        return dp[start] = min;
    }

    public static void main(String[] args) {
    	String A = "aaaaaaaaa";
    	String B = "aa";
    	SuffixOfAFromB suffixOfAFromB = new SuffixOfAFromB();
    	System.out.println(suffixOfAFromB.findMinSuffix(A, B));
	}
}
