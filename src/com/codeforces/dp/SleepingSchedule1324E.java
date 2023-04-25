package com.codeforces.dp;

import java.util.Scanner;

public class SleepingSchedule1324E {
    static int n, h, l, r;
    static int[] arr;
    static Integer[][] dp;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();h = s.nextInt();l = s.nextInt();r = s.nextInt();
        arr = new int[n];
        dp = new Integer[h][n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(dfs(0, 0));
    }
    private static int dfs(int index, int wakeUp) {
        if(index == n) {
            return 0;
        }
        if(dp[wakeUp][index] != null) {
            return dp[wakeUp][index];
        }
        int before = (wakeUp + arr[index] - 1)%h;
        int excl = dfs(index+1, before) + good(before);

        int after = (wakeUp + arr[index]) % h;
        int incl = dfs(index + 1, after) + good(after);

        return dp[wakeUp][index] = Math.max(incl, excl);
    }
    private static int good(int before) {
        return before >= l && before <= r ? 1 : 0;
    }
}
