package com.leetcode.hard.arrays;

import java.util.HashMap;
import java.util.Map;

public class FindMinLengthWithNegatives {
    public int findMinLength(int[] arr, int K) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int min = n, secondMin = n, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - K)) {
                int len = i - map.get(sum - K);
                if (len < min) {
                    secondMin = min;
                    min = len;
                } else if (len < secondMin) {
                    secondMin = len;
                }
            }
            map.put(sum, i);
        }
        return min + secondMin > n ? -1 : min + secondMin;
    }

    public static void main(String[] args) {
    	int arr[] = {-1, 2, 4, 5};
    	int K = 5;
    	FindMinLengthWithNegatives fmlwn = new FindMinLengthWithNegatives();
    	System.out.print(fmlwn.findMinLength(arr, K));
	}
}
