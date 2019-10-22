package com.leetcode.hard.dynamic.programming;

public class CuttingARod {

    private int dpTabulated(int price[], int N) {
        int dp[] = new int[N+1];

        dp[0] = 0;
        dp[1] = price[1];

        for( int i = 2; i < N+1; i++){
            dp[i] = price[i];
            for( int j = 1; j < i; j++) {
                if(price[j] + dp[i-j] > dp[i]) {
                    dp[i] = price[j] + dp[i-j];
                }
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        int price[] = {0, 3,   5,   8,   9,  10,  17,  17,  20};

        CuttingARod cuttingARod = new CuttingARod();
        System.out.println(cuttingARod.dpTabulated(price, 8));
    }
}
