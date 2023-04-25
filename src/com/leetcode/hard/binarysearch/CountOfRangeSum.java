package com.leetcode.hard.binarysearch;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        TreeMap<Long, Long> map = new TreeMap<>();
        long sum = 0;
        int range = (nums.length * (nums.length+1)) >>> 1;
        for(int num : nums) {
            sum += num;
//            if (sum >= lower && sum <= upper) {
//                range++;
//            }
            NavigableMap<Long, Long> headMap = map.headMap(sum-upper, true);
            for (long key : headMap.keySet()) {
                range -= headMap.get(key);
            }
            headMap = map.tailMap(sum-lower, true);
            for (long key : headMap.keySet()) {
                range -= headMap.get(key);
            }
            map.put(sum, map.getOrDefault(sum, 0L)+1);
        }
        return range;
    }

    public static void main(String[] args) {
        int arr[] = {2147483647,-2147483648,-1,0};
        int lower = -1;
        int upper = 0;

        CountOfRangeSum crs = new CountOfRangeSum();
        System.out.println(crs.countRangeSum(arr, lower, upper));
    }
}
