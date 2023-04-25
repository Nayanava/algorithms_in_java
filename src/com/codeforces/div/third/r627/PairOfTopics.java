package com.codeforces.div.third.r627;

import java.util.Arrays;
import java.util.Scanner;

public class PairOfTopics {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int A[] = new int[n];
        int B[] = new int[n];
        int diff[] = new int[n];
        for(int i = 0; i < n; i++) {
            A[i] = s.nextInt();
        }
        for(int i = 0; i < n; i++) {
            B[i] = s.nextInt();
        }
        for(int i = 0; i < n; i++) {
            diff[i] = A[i] - B[i];
        }
        Arrays.sort(diff);
        int res = 0;
        for(int i = 0; i < n; i++) {
            int target = -(diff[i]) + 1;
            int index = ceiling(diff, i+1, n, target);
            res += (n-index);
        }
        System.out.println(res);
    }
    private static int ceiling(int[] diff, int lo, int hi, int target) {
        while(lo < hi) {
            int mid = (lo + hi) >>> 1;
            if(diff[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
