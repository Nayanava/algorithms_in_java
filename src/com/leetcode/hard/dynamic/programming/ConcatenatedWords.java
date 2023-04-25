package com.leetcode.hard.dynamic.programming;

import java.util.*;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = new HashSet<>();
        for(String word : words) {
            dict.add(word);
        }
        List<String> res = new ArrayList<>();
        for(String word : words) {
            if(word.isEmpty()) {
                Boolean dp[] = new Boolean[word.length()];
                dict.remove(word);
                if(wordBreak(word, dict, dp, 0)) {
                    res.add(word);
                }
                dict.add(word);
            }
        }
        return res;
    }

    private boolean wordBreak(String word, Set<String> dict, Boolean[] dp, int start) {

        if(start == word.length()) {
            return true;
        }
        if(dp[start] != null) {
            return dp[start];
        }
        boolean res = false;
        for(int end = start+1; end <= word.length(); end++) {
            if(dict.contains(word.substring(start, end))) {
                if(wordBreak(word, dict, dp, end)) {
                    res = true;
                    break;
                }
            }
        }
        return dp[start] = res;
    }

    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        ConcatenatedWords cw = new ConcatenatedWords();
        cw.findAllConcatenatedWordsInADict(words);
    }
}
