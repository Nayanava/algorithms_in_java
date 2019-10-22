package com.leetcode.hard.dynamic.programming;

public class AssemblyLineScheduling {

    private int minAssemblyLineScheduling(int a[][], int t[][], int entry[], int exit[]) {
        int dp[][] = new int[a.length][a[0].length];

        for(int i = 0; i < a.length; i++) {
            a[i][0] += entry[i];
            a[i][a[0].length-1] += exit[i];
        }

        for( int i = 0; i < a.length; i++) {
            dp[i][0] = a[i][0];
        }
        for( int j = 1; j < a[0].length; j++) {
            for(int i = 0; i < a.length; i++) {
            dp[i][j] = Math.min(dp[i][j-1], dp[(i+1)%2][j-1] + t[(i+1)%2][j]) + a[i][j];
            }
        }

        return Math.min(dp[0][dp[0].length-1], dp[1][dp[0].length-1]);
    }

    public static void main (String[] args)
    {
        int a[][] = {{13,2 , 1, 15},
                {2, 10, 1, 3}};
        int t[][] = {{0, 1, 3, 2},
                {0, 4, 2, 5}};
        int e[] = {10, 12}, x[] = {18, 7};

        System.out.println(new AssemblyLineScheduling().minAssemblyLineScheduling(a, t, e, x));

    }
}
