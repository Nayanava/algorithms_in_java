package com.codeforces.sortings;

import java.util.Arrays;
import java.util.Scanner;

public class VanyaAndLanterns {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int end = s.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        Arrays.sort(arr);
        double left = arr[0] - 0, right = end - arr[n-1];
        double mid = 0;
        for(int i = 1; i < n; i++) {
            mid = Math.max(mid, arr[i] - arr[i-1]);
        }
        double res = Math.max(left, mid/2);
        res = Math.max(right, res);
        System.out.println(res);
    }
}
