package com.codeforces.sortings;

import java.util.Arrays;
import java.util.Scanner;

public class OptimalPointOnALine {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(arr[(n-1)/2]);
    }

//    private static int randomSelect(int[] arr, int lo, int hi, int K) {
//        while(lo <= hi) {
//            int pivot = partition(arr, lo, hi);
//            int range = pivot-lo+1;
//            if(range == K) {
//                return arr[pivot];
//            }
//            if(range < K) {
//                lo = pivot+1;
//                K -= range;
//            } else {
//                hi = pivot-1;
//            }
//        }
//        return -1;
//    }
//
//    private static int partition(int[] arr, int lo, int hi) {
//        int rand = lo + new Random().nextInt(hi-lo+1);
//        swap(arr, rand, lo);
//        int pivot = lo, i = lo;
//        for(int j = lo; j <= hi; j++) {
//            if(arr[j] < arr[pivot]) {
//                swap(arr, ++i, j);
//            }
//        }
//        swap(arr, pivot, i);
//        return i;
//    }
//    private static void swap(int[] arr, int lo, int hi) {
//        int temp = arr[lo];
//        arr[lo] = arr[hi];
//        arr[hi] = temp;
//    }
}
