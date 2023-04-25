package com.leetcode.hard.string;

import java.util.Arrays;

public class FitStringNumberOfTimes {

    public int wordsTypingBruteForce(String[] sentence, int rows, int cols) {
        int res = 0;
        int n = sentence.length;
        int i = 0;
        int remainingCols = cols;
        while(rows > 0) {
            int len = sentence[i%n].length();
            if(len <= remainingCols) {
                remainingCols -= (len + 1);
                i++;
                if(i == n) {
                   i = 0;
                   res += 1;
                }
            }
            else {
                rows--;
                remainingCols = cols;
            }
        }
        return res;
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int res = 0;
        int count = 0;
        return 0;
    }

    public static void main(String[] args) {
        String[] sentence = new String[]{"a", "bcd", "e"};
        int row = 3, col = 6;
        FitStringNumberOfTimes fsnt = new FitStringNumberOfTimes();
        String word = ".ad";
        System.out.print(word.contains("."));

        System.out.println(fsnt.wordsTyping(sentence, row, col));
    }
}
