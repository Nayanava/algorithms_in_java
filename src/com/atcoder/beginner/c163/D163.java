package com.atcoder.beginner.c163;

import java.util.*;

public class D163 {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long N = s.nextLong();
        long K = s.nextLong();
        long res = 0;
        for(long k = K; k <= N; k++) {
            long left = (k*(k-1))/2;
            long right = (k*(2*N + (k-1)*(-1)))/2;
            res = (res + right - left + 1) % INF;
        }
        System.out.println(res+1);
    }
}
