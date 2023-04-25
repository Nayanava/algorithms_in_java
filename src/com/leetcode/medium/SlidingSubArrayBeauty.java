package com.leetcode.medium;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SlidingSubArrayBeauty {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            list.add(nums[i]);
        }
        int[] result = new int[n-k+1];
        int index = 0;
        int first = randomSelect(list, 0, k-1, x);
        result[index++] = first < 0 ? first : 0;

        for(int start = 0, end = k; end < n; start++, end++) {
            list.remove(start);
            list.add(nums[end]);
            int res = randomSelect(list, 0, k-1, x);
            result[index++] = res < 0 ? res : 0;
        }
        return result;
    }

    private int randomSelect(List<Integer> nums, int lo, int hi, int k) {
        int pivot = partition(nums, lo, hi);

        int rank = pivot - lo + 1;
        if(rank == k) {
            return nums.get(pivot);
        }
        if(rank < k) {
            return randomSelect(nums, pivot+1, hi, k-rank);
        }
        return randomSelect(nums, lo, pivot-1, k);
    }

    private int partition(List<Integer> nums, int lo, int hi) {
        //int pivot = lo + (int)Math.random() % (hi - lo + 1);
        //swap(nums, lo, pivot);
        int i = lo, pivot = lo;
        for(int j = lo+1; j <= hi; j++) {
            if(nums.get(j) <= nums.get(pivot)) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i, pivot);
        return i;
    }
    void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void main(String[] args) {
        SlidingSubArrayBeauty obj = new SlidingSubArrayBeauty();
        obj.getSubarrayBeauty(new int[] {1,-1,-3,-2,3}, 3, 2);
    }
}
