package com.codeforces.dividenconquer;

import java.util.Arrays;
import java.util.Scanner;

public class CreativeSnap {
    static Long[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt(), A = s.nextInt(), B = s.nextInt();
        dp = new Long[k];
        for(int i = 0; i < k; i++) {
            dp[i] = s.nextLong();
        }
        Arrays.sort(dp);
        System.out.println(dnc(1, 1 << n, A, B));
    }

    private static long dnc(int lo, int hi, int A, int B) {
        if(lo > hi) {
            return 0;
        }
        int ceil = first(lo);
        int floor = last(hi);
        if(lo == hi) {
            return floor < ceil ? A : (long)(hi-lo+1)*(floor-ceil+1)*B;
        }
        if(floor < ceil) {
            return A;
        }
        long total = (long)(floor - ceil + 1)*(hi-lo+1)*B;
        int mid = (lo + hi)>>>1;
        long divided = dnc(lo, mid,A, B);
        divided += dnc(mid+1, hi, A, B);
        return Math.min(total, divided);
    }

    private static int first(int index) {
        int lo = 0, hi = dp.length;
        while(lo < hi) {
            int mid = (lo + hi) >>> 1;
            if(dp[mid] < index) {
                lo = mid+1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private static int last(int index) {
        int lo = -1, hi = dp.length-1;
        while( lo < hi) {
            int mid = (lo + hi + 1) >>> 1;
            if(dp[mid] > index) {
                hi = mid-1;
            } else {
                lo = mid;
            }
        }
        return lo;
    }
}
