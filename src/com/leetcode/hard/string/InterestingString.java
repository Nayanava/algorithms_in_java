package com.leetcode.hard.string;

public class InterestingString {
    public boolean isInterestingString(String str) {
    	if(str.isEmpty()) {
    		return false;
		}
        Boolean[] dp = new Boolean[str.length()];
        return dfs(str.toCharArray(), 0, dp);
    }

    private boolean dfs(char[] chs, int index, Boolean[] dp) {
        if (index > chs.length) {
            return false;
        }
        if (index == chs.length) {
            return true;
        }
        if (dp[index] != null) {
            return dp[index];
        }
        int num = 0;
        for (int i = index; i < chs.length; i++) {
            if (!isNum(chs[i])) {
                return false;
            }
            num = num * 10 + chs[i] - '0';
            if (dfs(chs, i + num + 1, dp)) {
                return dp[index] = true;
            }
        }
        return dp[index] = false;
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
    	String s = "";
    	InterestingString is = new InterestingString();
    	System.out.println(is.isInterestingString(s));
	}
}
