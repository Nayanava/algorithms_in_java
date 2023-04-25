package com.leetcode.hard.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LexicographicallySmallestAndLargest {
    public String[] lexicographicallySmallestAndLargest(String str) {
        int start = -1;
        String smallestString = "";
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        for(int i = 0; i < str.length(); i++) {
            while (start == -1 && i < str.length() && !vowels.contains(str.charAt(i))) {
                i += 1;
            }
            start = i;
            while (i < str.length() && vowels.contains(str.charAt(i))) {
                if (str.charAt(start) > str.charAt(i)) {
                    start = i;
                }
                i++;
            }
            if (i == str.length()) {
                break;
            }
            String newString = str.substring(start, i + 1);
            if (smallestString.isEmpty() || smallestString.compareTo(newString) > 0) {
                smallestString = newString;
            }
            start = -1;
        }
        int i = 0, n = str.length(), end = n;
        String largestString = "";
        for(i = n-1; i >= 0; i--) {
            while(i >= 0 && !vowels.contains(str.charAt(i))) {
                i--;
            }
            if(i == end-1) {
                end = i;
                continue;
            }
            if(i == -1) {
                break;
            }
            String newString = str.substring(i, end);
            if(largestString.isEmpty() || largestString.compareTo(newString) < 0) {
                largestString = newString;
            }
        }
        System.out.println(smallestString + " " + largestString);
        return new String[]{smallestString, largestString};
    }

    public static void main(String[] args) {
        String test = "aabaaab";
        LexicographicallySmallestAndLargest smallestAndLargest = new LexicographicallySmallestAndLargest();
        smallestAndLargest.lexicographicallySmallestAndLargest(test);
    }
}
