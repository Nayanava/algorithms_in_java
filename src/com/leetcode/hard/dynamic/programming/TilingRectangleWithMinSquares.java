package com.leetcode.hard.dynamic.programming;

import java.util.Arrays;

public class TilingRectangleWithMinSquares {
    public int countMinSquares(int height, int width) {
        int area = height * width;
        int side = Math.max(height, width);

        int dp[] = new int[area + 1];
        Arrays.fill(dp, area+1);

        dp[0] = 0;
        for (int i = 1; i <= area; i++) {
            for (int j = 1; j <= side; j++) {
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                } else {
                    break;
                }
            }
        }
        return dp[area];
    }

    public static void main(String[] args) {
        int height = 11, width = 13;
        TilingRectangleWithMinSquares trwms = new TilingRectangleWithMinSquares();
        System.out.println(trwms.countMinSquares(height, width));
    }
}
