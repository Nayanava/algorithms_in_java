package com.codeforces.dp;

import java.util.Scanner;

public class KTree {
    static int mod = (int)1e9+7;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt(), d = s.nextInt();
        System.out.println((countWays(n, k) - countWays(n, d-1) + mod) % mod);
    }
    private static long countWays(int n, int k) {
        if(k == 0) {
            return 0;
        }
        long dp[] = new long[n+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                if(i >= j) {
                    dp[i] = dp[i] + dp[i-j];
                    if(dp[i] >= mod) {
                        dp[i] -= mod;
                    }
                }
            }
        }
        return dp[n];
    }
}
