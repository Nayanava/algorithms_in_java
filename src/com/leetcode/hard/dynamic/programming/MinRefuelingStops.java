package com.leetcode.hard.dynamic.programming;

import java.util.Arrays;

public class MinRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int dp[] = new int[target+1];
        Arrays.fill(dp, target+1);
        if(target == 0) {
            return -1;
        }
        if(target <= startFuel) {
            return 0;
        }
        for(int i = 0; i <= startFuel; i++) {
            dp[i] = 0;
        }

        for(int i = startFuel + 1; i <= target; i++) {
            for(int j = 0; j < stations.length; j++) {
                if(dp[stations[j][0]] >= target + 1)
                    continue;
                if( i < stations[j][0])
                    continue;
                if(i - stations[j][1] < 0) {
                    dp[i] = Math.min(dp[i], dp[i-stations[j][0]] + 1);
                }
                else
                    dp[i] = Math.min(dp[i], dp[i-stations[j][1]] + 1);
            }
        }
        return dp[target] >= target+1 ? -1 : dp[target];
    }
    public static void main(String[] args) {
        int[][] stations = {{25,25},{50,100},{100,100},{150,40}};
        MinRefuelingStops mrs = new MinRefuelingStops();
        System.out.print(mrs.minRefuelStops(200, 50, stations));
    }
}
