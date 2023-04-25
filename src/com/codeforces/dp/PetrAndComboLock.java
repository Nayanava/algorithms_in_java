package com.codeforces.dp;

import java.util.Scanner;

public class PetrAndComboLock {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
            sum += arr[i];
        }
        if(sum%2 != 0) {
            System.out.println("NO");
            return;
        }
        if(sum % 360 == 0) {
            System.out.println("YES");
            return;
        }
        int count = sum / 360;
        for(int k = 0; k <= count; k++) {
            int temp = sum - k * 360;
            boolean dp[][] = new boolean[n + 1][temp / 2 + 1];
            for (int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }
            for (int i = 1; i <= temp / 2; i++) {
                dp[0][i] = false;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= temp / 2; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= arr[i - 1]) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                    }
                }
            }
            if (dp[n][temp / 2]) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}
