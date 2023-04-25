package com.codeforces.sortings;

import java.util.Scanner;

public class LessOrEqual {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        int lo = 1, hi = (int)1e9+1;
        while( lo < hi) {
            int mid = (lo + hi) >>> 1;
            int count = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i] <= mid) {
                    count++;
                }
            }
            if(count == k) {
                System.out.println(mid);
                return;
            } else if(count < k) {
                lo = mid+1;
            } else {
                hi = mid;
            }
        }
        System.out.println("-1");
    }
}
