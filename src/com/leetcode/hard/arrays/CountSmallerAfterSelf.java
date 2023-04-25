package com.leetcode.hard.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CountSmallerAfterSelf {
    public List<Integer> countSmaller(int[] arr) {
        List<Integer> sortedList = new LinkedList<>();
        int[] res = new int[arr.length];
        for(int i = arr.length-1; i >= 0; i--) {
            int index = binarySearch(sortedList, arr[i]);
            if(index < 0) {
                index = -(index+1);
            }
            res[i] = index;
            sortedList.add(index, arr[i]);
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    private int binarySearch(List<Integer> list, int target) {
        int lo = -1, hi = list.size();
        while(lo < hi) {
            int mid = (lo + hi + 1) >>> 1;
            if(list.get(mid) >= target) {
                hi = mid-1;
            } else {
                lo = mid;
            }
        }
        return lo;
    }
}
