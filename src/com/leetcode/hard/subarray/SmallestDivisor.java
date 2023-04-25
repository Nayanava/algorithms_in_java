package com.leetcode.hard.subarray;

public class SmallestDivisor {
    public int smallestDivisor(int[] nums, int threshold) {
        if(nums.length == 0) {
            return 0;
        }
        int low = 1, high = (int) 10e6;
        while(low <= high) {
            int mid = low +((high-low) >>>1);
            int sum = 0;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] % mid == 0) {
                    sum += nums[i]/mid;
                }
                else
                    sum += (nums[i] / mid) + 1;
            }
            if(sum <= threshold) {
                high = mid-1;
            } else
                low = mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        int arr[] = {962551,933661,905225,923035,990560};
        int threshold = 10;
        SmallestDivisor sd = new SmallestDivisor();
        System.out.print(sd.smallestDivisor(arr, threshold));
    }
}
