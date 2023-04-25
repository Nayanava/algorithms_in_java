package com.leetcode.hard.arrays;

import java.util.Arrays;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int totalCount = t.length();
        int map[] = new int[128];
        Arrays.fill(map, -1);
        int n = s.length();
        int start = 0, res = n, finalStart = 0;
        for (char c : t.toCharArray()) {
            map[c] += (map[c] == -1) ? 2 : 1;
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map[c]--;
            if (map[c] >= 0) {
                totalCount--;
            }
            while (totalCount == 0) {
                if(res > i-start+1) {
                    res = i - start + 1;
                    finalStart = start;
                }
                map[s.charAt(start)]++;
                if(map[s.charAt(start)] == 1) {
                    totalCount++;
                }
                start++;
            }
        }
        return s.substring(finalStart, finalStart + res);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.print(mws.minWindow(s, t));
    }
}
