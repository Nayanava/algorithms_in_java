package com.leetcode.hard.arrays;

import java.util.Arrays;

class Solution {
    public int maxSumDivThree(int[] nums) {
        return maxSumDivK(nums, 3);
    }
    private int maxSumDivK(int[] nums, int k) {
        int[] dp = new int[k];
        for(int num : nums) {
            int[] tmp = Arrays.copyOf(dp, k);
            for(int i = 0; i < k; i++) {
                dp[(num + tmp[i]) % k] = Math.max(dp[(num+tmp[i]) % k], num + tmp[i]);
            }
        }
        return dp[0];
    }
}