package com.leetcode.hard.divide.and.conquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        List<Long> lis = new ArrayList<>();
        int n = nums.length, count = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (sum >= lower && sum <= upper) {
                count++;
            }
            int index = ceiling(lis, sum - upper);
            if (index >= 0 && index < lis.size()) {
                count += (lis.size() - index);
            }
            index = ceiling(lis, sum - lower + 1);
            if (index >= 0 && index < lis.size()) {
                count -= (lis.size() - index);
            }
            int loc = Collections.binarySearch(lis, sum);
            if (loc < 0) {
                loc = -(loc + 1);
            }
            lis.add(loc, sum);
        }
        return count;
    }

    //for one I need the floor and for the other I need the ceiling
    private int ceiling(List<Long> list, long key) {
        int low = 0, high = list.size() - 1;
        while(low <= high) {
            int mid = (low + high) >>> 1;
            if(list.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int arr[] = {-2, 5, -1};
        int lower = -2, upper = 2;
        CountOfRangeSum crs = new CountOfRangeSum();
        System.out.println(crs.countRangeSum(arr, lower, upper));
    }
}
