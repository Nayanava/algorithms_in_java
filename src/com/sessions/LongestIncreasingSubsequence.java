package com.sessions;

import java.io.*;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int[] arr = {1, 2, 4, 50, 25, 3, 100, 75, 100, 4, 25, 75, 100};
        out.println(lisDP(arr));
        out.close();
    }

    private static int lis(int[] arr) {
        int lis[] = new int[arr.length];
        int len = 0;
        for(int i = 0; i < arr.length; i++) {
            int index = ArraysBinarySearch.binarySearch(lis, 0, len, arr[i]);
            if(index < 0) {
                index = -(index+1);
            }
            lis[index] = arr[i];
            if(index == len) {
                len += 1;
            }
        }
        return len;
    }

    private static int lisDP(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(1 + dp[j], dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    //{idx- 0, idx - 2}
    //{1, 2, 4, 50, 25, 3, 100, 75, 100, 4, 25, 75, 100}   //{1, 1, 4} -> dp[0] -> 1, dp[1] -> 1, dp[2] -> 2
    //lis[0] = {1, 2, 4, 50}

    // { 1, 3, 2, 3, 6} -> { 1, 3, 4, 2, 6} -> DP[4] -> 4, Dp[3] -> 2, DP[4] -> 3
    //1 - 1
    //2 - 2
    //4 - 1, 2, 4
    //lis[4] = lis(4)- lis[4] - O(1)

}
