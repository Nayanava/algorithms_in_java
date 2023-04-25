package com.codeforces.sortings;

import java.util.Arrays;
import java.util.Scanner;

public class WeirdSort {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while (T-- > 0) {
            int m = s.nextInt(), n = s.nextInt();
            int arr[] = new int[m];
            int positions[] = new int[n];
            for (int i = 0; i < m; i++) {
                arr[i] = s.nextInt();
            }
            for (int i = 0; i < n; i++) {
                positions[i] = s.nextInt()-1;
            }
            Arrays.sort(positions);
            int start = positions[0], end = positions[0] + 1;
            for (int i = 1; i < n; i++) {
                if (positions[i] != positions[i - 1] + 1) {
                    Arrays.sort(arr, start, end + 1);
                    start = positions[i];
                }
                end = positions[i] + 1;
            }
            Arrays.sort(arr, start, end+1);
            System.out.println(sorted(arr) ? "YES" : "NO");
        }
    }

    private static boolean sorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}

/*
find the set of intervals and see if sorting those sorts the entire array.
 */