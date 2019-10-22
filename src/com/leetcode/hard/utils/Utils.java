package com.leetcode.hard.utils;

public class Utils {
    public static <T> void memset(T arr[], T value) {
        for( int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }

    public static int max(Integer... arr) {
        int max = arr[0];
        for( int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public static int min(Integer... arr) {
        int min = arr[0];
        for( int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
