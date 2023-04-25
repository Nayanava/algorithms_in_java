package com.codeforces.dp;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        int dp[][] = new int[n+1][3];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        dp[0][0] = 0;
        dp[0][1] = arr[0] == 1 || arr[0] == 3 ? 1 : 0;
        dp[0][2] = arr[0] == 2 || arr[0] == 3 ? 1 : 0;
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
            if(arr[i] == 3 || arr[i] == 1)
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]) + 1;
            if(arr[i] == 3 || arr[i] == 2)
                dp[i][2] = Math.max(dp[i-1][0], dp[i-1][1]) + 1;
        }
        System.out.println(n-Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2])));
    }
}
