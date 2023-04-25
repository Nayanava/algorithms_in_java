package com.leetcode.hard.subarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MajorityChecker {

    public Map<Integer, List<Integer>> map = new HashMap<>();
    int[] arr;

    public MajorityChecker(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        this.arr = arr;
    }

    int findCandidate(int left, int right) {
        int index = left, count = 1;
        Integer.parseInt("123");
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] == arr[index]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    index = i;
                    count = 1;
                }
            }
        }
        return arr[index];
    }

    int findFloor(List<Integer> list, int key) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) <= key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return high;
    }

    int findCeiling(List<Integer> list, int key) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) >= key) {
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return low;
    }

    public int query(int left, int right, int threshold) {
        return 1;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */