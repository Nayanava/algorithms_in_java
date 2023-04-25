package com.codeforces.dp;

import java.util.Scanner;

public class TwoArrays1288C {
    static int mod = (int)1e9+7;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), K = s.nextInt();
        int[][] incDp = new int[n+1][K+1];
        int[][] descDp = new int[n+1][K+1];

        for(int i = 1; i <= n; i++) {
            incDp[i][1] = 1;
            descDp[i][1] = 1;
        }
        for(int k = 2; k <= K; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= i; j++) {
                    incDp[i][k] += incDp[j][k-1];
                    if(incDp[i][k] > mod) incDp[i][k] -= mod;
                }
            }
        }
        for(int k = 2; k <= K; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = n; j >= i; j--) {
                    descDp[i][k] += descDp[j][k-1];
                    if(descDp[i][k] > mod) descDp[i][k] -= mod;
                }
            }
        }
        for(int i = n-1; i >= 1; i--) {
            descDp[i][K] += descDp[i+1][K];
        }
        long res = 0;
        for(int i = 1; i <= n; i++) {
            long temp = ((long)incDp[i][K]*descDp[i][K])%mod;
            if(temp < 0) {
                temp += mod;
            }
            res = (res + temp)%mod;
        }
        System.out.println(res);
    }
}
