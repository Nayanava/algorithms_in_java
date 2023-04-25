package com.codeforces.dp;

import java.util.Scanner;

public class ArtUnion {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt(), n = s.nextInt();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]) + s.nextInt();
            }
            System.out.print(dp[i+1][n] + " ");
        }
    }
}
