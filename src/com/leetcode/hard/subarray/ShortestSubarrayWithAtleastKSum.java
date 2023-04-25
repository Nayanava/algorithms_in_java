package com.leetcode.hard.subarray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ShortestSubarrayWithAtleastKSum {
    public int shortestSubarray1(int[] A, int K) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int n = A.length;
        map.put(Integer.MIN_VALUE, -1);
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(A[i] >= K) {
                return 1;
            }
            sum += A[i];
            if(sum >= K) {
                minLength = Math.min(minLength, i+1);
            }
            int minSub = sum - K;
            int floorKey = map.floorKey(minSub);
            while(floorKey != Integer.MIN_VALUE) {
                int value = map.get(floorKey);
                minLength = Math.min(minLength, i - value);
                map.remove(floorKey);
                floorKey = map.lowerKey(floorKey);
            }
            map.put(sum, i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }


    public int shortestSubarray(int[] arr, int K) {
        Meta lis[] = new Meta[arr.length];
        int len = 0, sum = 0;
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int index = binarySearch(lis, 0, len, sum);
            if( index < 0) {
                index = -(index + 1);
            }
            lis[index] = new Meta(sum, i);
            if(index == len) {
                len++;
            }
        }
        int minLength = Integer.MAX_VALUE;
        sum = 0;
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sum >= K) {
                minLength = Math.min(minLength, i + 1);
            }
            int index = floor(lis, 0, len, sum-K);
            if(index < 0) {
                continue;
            }
            if(sum - lis[index].key >= K && i > lis[index].index)
                minLength = Math.min(minLength, i - lis[index].index);
        }

        return minLength;
    }

    private int floor(Meta[] arr, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while(low <= high) {
            int mid = (low + high) >>> 1;
            if(arr[mid].key <= key && (mid == high || arr[mid + 1].key > key)) {
                return mid;
            }
            if(arr[mid].key < key) {
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return -1;
    }
    private int binarySearch(Meta[] arr, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while( low <= high) {
            int mid = (low + high) >>> 1;
            if(arr[mid].key < key) {
                low = mid + 1;
            }
            else if (arr[mid].key > key) {
                high = mid - 1;
            }
            else return mid;
        }
        return -(low + 1);
    }

    class Meta {
        int key;
        int index;

        Meta(int key, int index) {
            this.key = key;
            this. index = index;
        }
    }
    public static void main(String[] args) {
        int arr[] = {45,95,97,-34,-42};
        ShortestSubarrayWithAtleastKSum kSum = new ShortestSubarrayWithAtleastKSum();
        System.out.println(kSum.shortestSubarray(arr, 21));
    }
}
