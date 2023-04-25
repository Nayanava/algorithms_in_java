package com.leetcode.hard.binarysearch;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CountSmallerAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int index[] = new int[n];
        for(int i = 0; i < n; i++) {
            index[i] = i;
        }
        Integer count[] = new Integer[n];
        Arrays.fill(count, 0);
        findMinOnRight(nums, 0, n-1, index, count);
        return Arrays.asList(count);
    }
    private void findMinOnRight(int[] nums, int low, int high, int[] index, Integer[] count) {
        if(low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        findMinOnRight(nums, low, mid, index, count);
        findMinOnRight(nums, mid+1, high, index, count);

        sort(nums, low, mid+1, high, index, count);
    }

    private void sort(int[] nums, int low, int mid, int high, int[] index, Integer[] count) {
        int i = low, j = mid, k = 0;
        int temp[] = new int[high-low+1];
        int cnt = 0;
        while( i < mid) {
            while(j <= high && nums[index[j]] < nums[index[i]]) {
                temp[k++] = index[j++];
                cnt++;
            }
            while(i < mid && (j > high || nums[index[i]] <= nums[index[j]])) {
                count[index[i]] += cnt;
                temp[k++] = index[i++];
            }
        }
        while( j <= high) {
            temp[k++] = index[j++];
        }
        for(int x = low; x <= high; x++) {
            index[x] = temp[x-low];
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,9,7,8,5};
        CountSmallerAfterSelf csas = new CountSmallerAfterSelf();
        csas.countSmaller(arr).stream().forEach(ele -> System.out.print(ele + " "));
    }
}
