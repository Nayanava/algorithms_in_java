package com.sessions;

import java.io.*;
import java.util.Arrays;

public class ArraysBinarySearch {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int arr[] = {1, 2, 4, 5, 6, 7};
        int val = 3;
        int index = binarySearch(arr,0, arr.length, val);
        if(index < 0) {
            index = -(index+1);
        }
        out.println(index);
        out.close();
        //index = -3
        // = -(-3+1) = -(-2) = 2
    }

    public static int binarySearch(int[] arr, int fromIndex, int toIndex, int val) {
        int lo = fromIndex, hi = toIndex-1;
        while(lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if(arr[mid] == val) {
                return mid;
            }
            if(arr[mid] < val) {
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }
        return -(lo+1);
    }
}
