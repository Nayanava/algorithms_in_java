package com.leetcode.hard.string;

import java.util.Arrays;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int start = 0, res = Integer.MAX_VALUE;
        int finalStart = 0;
        Arrays.fill(map, Integer.MIN_VALUE);
        char[] src = s.toCharArray();
        for(char c : t.toCharArray()) {
            map[c] = (map[c] == Integer.MIN_VALUE) ? 1 : map[c]+1;
        }
        for(int i = 0; i < src.length; i++) {
            if(map[src[i]] == Integer.MIN_VALUE)
                continue;
            map[src[i]]--;
            while (start < src.length && allCharFound(map)) {
                if(map[src[start]] == Integer.MIN_VALUE) {
                    start++;
                    continue;
                }
                map[src[start]]++;
                if(res > i-(start)+1) {
                    finalStart = start;
                    res = i-(start)+1;
                }
                start++;
            }

        }
        return s.substring(finalStart, finalStart+res);
    }

    private boolean allCharFound(int[] map) {
        for(int i : map)
            if(i > 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.print(mws.minWindow(s, t));
    }
}
