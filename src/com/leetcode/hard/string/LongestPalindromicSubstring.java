package com.leetcode.hard.string;
/**
 * @author nayanava
 */

import java.io.*;

public class LongestPalindromicSubstring {
    public static int longestPalindromicSubstringLinear(char input[]) {
        int index = 0;
        char newInput[] = new char[2*input.length + 1];
        for(int i=0; i < newInput.length; i++) {
            if(i % 2 != 0) {
                newInput[i] = input[index++];
            } else {
                newInput[i] = '$';
            }
        }

        int T[] = new int[newInput.length];
        int start = 0;
        int end = 0;
        int count = 0;
        for(int i=0; i < newInput.length; ) {

            while(start >0 && end < newInput.length-1 && newInput[start-1] == newInput[end+1]) {
                if(newInput[start-1] != '$') {
                    count++;
                }
                start--;
                end++;
            }
            T[i] = end - start + 1;
            if(end == T.length -1) {
                break;
            }
            int newCenter = end + (i%2 ==0 ? 1 : 0);

            for(int j = i + 1; j <= end; j++) {

                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                if(j + T[i - (j - i)]/2 == end) {
                    newCenter = j;
                    break;
                }
            }
            i = newCenter;
            end = i + T[i]/2;
            start = i - T[i]/2;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "abcde";
        int n = s.length();
        int totalPossible = n*(n-1) / 2;
        int totalPalindromes = longestPalindromicSubstringLinear(s.toCharArray());
        System.out.print(totalPossible - totalPalindromes + 1);
    }
}
