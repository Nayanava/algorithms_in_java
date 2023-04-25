package com.leetcode;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.*;

public class Solution {
    public int countPalindromicSubsequence(String s) {
        int distinctLetters = 0;
        CharMeta[] arr = new CharMeta[26];
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < 26; i++) {
            arr[i] = new CharMeta();
        }
        int index = 0;
        for(char c : s.toCharArray()) {
            int idx = c-'a';
            if(arr[idx].count == 0) {
                distinctLetters++;
            }
            if(arr[idx].start == -1) {
                arr[idx].start = index;
            } else {
                arr[idx].end = index;
            }
            map.computeIfAbsent(idx, x -> new TreeSet<>()).add(index);
            arr[idx].count++;

            index++;
        }
        int totalPal = 0;
        for(int i = 0; i < 26; i++) {
            if(arr[i].count > 1) {
                if(arr[i].end - arr[i].start > 1) {
                    for(int j = 0; j < 26; j++) {
                        if (j == i || arr[j].count == 0) {
                            continue;
                        }
                        Integer ceiling = map.get(j).ceiling(arr[i].start);
                        Integer floor = map.get(j).floor(arr[i].end);
                        if(ceiling != null && floor != null && ceiling <= floor) {
                            totalPal++;
                        }
                    }
                    totalPal += arr[i].count > 2 ? 1 : 0;
                }
            }
        }
        return totalPal;
    }

    class CharMeta {
        int start, end, count;
        CharMeta() {
            start = -1;
            end = -1;
            count = 0;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.countPalindromicSubsequence("bbcbaba");
        System.out.println(res);
    }
}
