package com.leetcode.hard.dynamic.programming;

import java.util.*;

public class WordBreakIII {
    public List<String> findMinWords(String s, Set<String> dict) {
        List<String>[] dp = new List[s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new ArrayList<>();
        }
        List<String> res = dfs(s, 0, dict, dp);
        return res == null ? new ArrayList<>() : res;
    }

    private List<String> dfs(String s, int start, Set<String> dict, List<String>[] dp) {
        if (start == s.length()) {
            return new ArrayList<>();
        }
        if (!dp[start].isEmpty()) {
            return new ArrayList<>(dp[start]);
        }
        List<String> list = null;
        for (int end = start + 1; end <= s.length(); end++) {
            String temp = s.substring(start, end);
            if (dict.contains(temp)) {
                list = dfs(s, end, dict, dp);
                if(null == list) {
                	continue;
				}
                list.add(temp);
                if (dp[start].isEmpty() || (list.size() < dp[start].size())) {
                    dp[start] = list;
                }
            }
        }
        return dp[start].isEmpty() ? null : new ArrayList<>(dp[start]);
    }

    public static void main(String[] args) {
        String s = "catsandog";
        Set<String> dict = new HashSet<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        WordBreakIII wb = new WordBreakIII();
        List<String> res = wb.findMinWords(s, dict);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }
}
