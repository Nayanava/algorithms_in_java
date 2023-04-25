package com.codeforces.dp;

import java.util.Scanner;

public class BunsIterative {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt(), m = s.nextInt(), dc = s.nextInt(), cost = s.nextInt();
        int[][] dp = new int[N+1][m+1];
        int[] a = new int[m];
        int[] b = new int[m];
        int[] c = new int[m];
        int[] d = new int[m];
        for(int i = 0; i < m; i++) {
            a[i] = s.nextInt();
            b[i] = s.nextInt();
            c[i] = s.nextInt();
            d[i] = s.nextInt();
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= m; j++) {
                for(int k = 0; k <= a[j-1]/b[j-1]; k++) {
                    if(i - c[j-1]*k >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-c[j-1]*k][j-1] + k*d[j-1]);
                    }
                }
            }
        }
        int max = 0;
        for(int k = 0; k <= N; k++) {
            max = Math.max(max, dp[k][m] + ((N-k)/dc)*cost);
        }
        System.out.println(max);
    }
}
