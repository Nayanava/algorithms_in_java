package com.leetcode.hard.subarray;

import java.util.Map;

public class KOddNumbersArray {
    public int numberOfSubarrays(int[] nums, int k) {
        int start = 0;
        int n = nums.length;
        int res = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] % 2 == 1) {
                k--;
            }

            while(k < 0) {
                if(nums[start] % 2 == 1) {
                    k++;
                }
                start++;
            }
            res += (i-start+1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,1,2,2,1,2,2,2};
        int k = 2;
        KOddNumbersArray sol = new KOddNumbersArray();
        System.out.println(sol.numberOfSubarrays(arr, k) - sol.numberOfSubarrays(arr, k-1));
    }
}
