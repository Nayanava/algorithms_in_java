package com.leetcode.hard.slidingwindow;

import java.util.Arrays;

public class AllSubstringsWithConstraint {
    public int allSubstrings(String s, char[] alphabets) {
        int map[] = new int[128];
        int total = alphabets.length;
        int res = 0, start = 0;
        for(char c : alphabets) {
            map[c]++;
        }
        for(int i = 0; i < s.length(); i++) {
            if(map[s.charAt(i)]-- > 0) {
                total--;
            }
            while(total == 0) {
                if(++map[s.charAt(start++)] > 0) {
                    total++;
                }
            }
            res += i-start+1;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "cab";
        char[] alphabets = {'a', 'b', 'c'};
        AllSubstringsWithConstraint aswc = new AllSubstringsWithConstraint();
        System.out.println(aswc.allSubstrings(s, alphabets));
    }
}