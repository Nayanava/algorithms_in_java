package com.codeforces.dp;

import java.util.Scanner;

public class HardProblem {
    static int[] cost;
    static String[] words;
    static Long[][] dp;
    static int n;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        cost = new int[n];
        words = new String[n];
        dp = new Long[n][2];
        for(int i = 0; i < n; i++) {
            cost[i] = s.nextInt();
        }
        for(int i = 0; i < n; i++) {
            words[i] = s.next();
        }
        long no_rev = dfs(1, 0);
        long rev = dfs(1, 1);
        if(rev != Long.MAX_VALUE) {
            rev += cost[0];
        }
        long res = Math.min(rev, no_rev);
        System.out.println(res == Long.MAX_VALUE ? -1 : res);
    }
    private static long dfs(int index, int reversed) {
        if(index == n) {
            return 0;
        }
        if(dp[index][reversed] != null) {
            return dp[index][reversed];
        }
        if(sorted(index, reversed, 0)) {
            //two choices either reverse current index or don't reverse current index;
            //if reversing breaks sort can't reverse
            long do_rev = Long.MAX_VALUE, no_rev = 0;
            if(sorted(index, reversed, 1)) {
                do_rev = dfs(index+1, 1);
                if(do_rev != Long.MAX_VALUE) {
                    do_rev += cost[index];
                }
            }
            no_rev = dfs(index+1, 0);
            return dp[index][reversed] = Math.min(no_rev, do_rev);
        }
        if(!sorted(index, reversed, 1)) {
            return Long.MAX_VALUE;
        }
        long rev = dfs(index+1, 1);
        if(rev != Long.MAX_VALUE) {
            rev += cost[index];
        }
        return dp[index][reversed] = rev;
    }

    private static boolean sorted(int index, int old_reversed, int new_reversed) {
        char[] tempNew = words[index].toCharArray();
        char[] tempOld = words[index-1].toCharArray();
        if(old_reversed == 1) {
            reverse(tempOld);
        } if(new_reversed == 1) {
            reverse(tempNew);
        }
        for(int i = 0; i < Math.min(tempNew.length, tempOld.length); i++) {
            if(tempOld[i] != tempNew[i]) {
                return tempOld[i] < tempNew[i];
            }
        }
        return tempNew.length >= tempOld.length;
    }
    private static void reverse(char[] arr) {
        for(int start = 0, end = arr.length-1; start < end; start++, end--) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }
}
