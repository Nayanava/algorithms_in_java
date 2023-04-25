package com.atcoder.beginner.c163;

import java.util.Arrays;
import java.util.Scanner;

public class E163 {
    static Long[][] dp;
    static int INF = (int) 1e9 + 7;
    static Integer[][] arr;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        arr = new Integer[N][2];
        for(int i = 0; i < N; i++) {
            arr[i][0] = s.nextInt();
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        dp = new Long[N][N];
        System.out.println(dfs(0, 0, N-1));
    }

    private static long dfs(int index, int left, int right) {
        if(index == arr.length) {
            return 0;
        }
        if(dp[left][right] != null) {
            System.out.println("hit");
            return dp[left][right];
        }
        return dp[left][right] = Math.max(dfs(index+1, left+1, right) + (long)arr[index][0]*Math.abs(arr[index][1] - left),
                dfs(index+1, left, right-1) + (long)arr[index][0]*Math.abs(arr[index][1] - right));
    }
}
