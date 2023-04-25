package com.leetcode.hard.arrays;

import java.util.Arrays;
import java.util.TreeMap;

public class FindLargerThanSelf {
    public int[] greaterThanSelfOnRight(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[arr.length];
        int index = 0;
        for (int ele : arr) {
            res[index++] = map.tailMap(ele).size();
            map.put(ele, ele);
        }
        return res;
    }
    public static void main(String[] args) {
    	int[] arr = {4, 2, 1, 3, 5};
    	FindLargerThanSelf flts = new FindLargerThanSelf();
		Arrays.stream(flts.greaterThanSelfOnRight(arr)).forEach(ele -> System.out.print(ele + " "));
	}
}
