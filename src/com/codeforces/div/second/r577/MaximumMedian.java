package com.codeforces.div.second.r577;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumMedian {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        Arrays.sort(arr);
        long lo = arr[n/2], hi = (long)1e9 + (long)2e5 + 1;
        while(lo < hi) {
            long mid = (lo + hi + 1) / 2 ;
            long count = 0;
            for(int i = n/2; i < n; i++) {
                if(mid-arr[i] > 0)
                count += mid-arr[i];
            }
            if(count > k) {
                hi = mid-1;
            } else {
                lo = mid;
            }
        }
        System.out.println(lo);
    }
}
