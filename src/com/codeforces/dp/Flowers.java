package com.codeforces.dp;

import java.util.Scanner;

public class Flowers {
    static int mod = 1_000_000_007;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        int k = s.nextInt();
        int[][] arr = new int[T][2];
        int max = 0;
        int index = -1;
        while (++index < T) {
            arr[index][0] = s.nextInt();
            arr[index][1] = s.nextInt();
            max = Math.max(max, arr[index][1]);
        }
        int dp[] = new int[max + 1];
        dp[0] = 1;
        for (int i = 1; i <= max; i++) {
            dp[i] = dp[i - 1];
            if (i >= k) {
                dp[i] = (dp[i] + dp[i - k]) % mod;
            }
        }
        dp[0] = 0;
        for(int i = 1; i <= max; i++) {
            dp[i] = (dp[i] + dp[i-1]) % mod;
        }
        for (int i = 0; i < T; i++) {
            int total = dp[arr[i][1]] - dp[arr[i][0] - 1];
            if(total < 0) {
                total += mod;
            }
            System.out.println(total);
        }
    }
}
