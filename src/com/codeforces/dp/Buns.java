package com.codeforces.dp;

import java.util.Scanner;

public class Buns {
    static int  n, dc, cost;
    static Integer[][][] dp;
    static int[] a, b, c, d;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
         int total = s.nextInt();
         n = s.nextInt();
         dc = s.nextInt();
         cost = s.nextInt();
         a = new int[n];
         b = new int[n];
         c = new int[n];
        d = new int[n];
        int max = 0;
         for(int i = 0; i < n; i++) {
             a[i] = s.nextInt();
             max = Math.max(a[i], max);
             b[i] = s.nextInt();
             c[i] = s.nextInt();
             d[i] = s.nextInt();
         }
        dp = new Integer[n][total+1][max+1];
         System.out.println(dfs(0, total));
    }
    private static int dfs(int index, int total) {
        if(total <= 0 || index == n) {
            return 0;
        }
        if(dp[index][total][a[index]] != null) {
            return dp[index][total][a[index]];
        }
        //I use this stuffing
        int stuffed = 0, non_stuffed = 0, excl;
        if(b[index] <= a[index] && c[index] <= total) {
            a[index] -= b[index];
            stuffed = dfs(index, total-c[index]) + d[index];
            a[index] += b[index];
        }
        //I want to use but without stuffing;
        if(total >= dc) {
            non_stuffed = dfs(index, total - dc) + cost;
        }
        //If I dont want to use this dough at all
        excl = dfs(index + 1, total);
        return dp[index][total][a[index]] = Math.max(stuffed, Math.max(non_stuffed, excl));
    }
}
