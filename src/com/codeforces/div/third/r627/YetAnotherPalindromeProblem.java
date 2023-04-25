package com.codeforces.div.third.r627;

import java.util.Scanner;

public class YetAnotherPalindromeProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T-- > 0) {
            int n = scanner.nextInt();
            int arr[] = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            if(lps(arr) >= 3) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static int lps(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int i = 0; i < n-1; i++) {
            dp[i][i+1] = arr[i] == arr[i+1] ? 2 : 1;
        }
        for(int l = 3; l <= n; l++) {
            for(int i = 0; i < n-l+1; i++) {
                int j = i+l-1;
                if(arr[i] == arr[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
