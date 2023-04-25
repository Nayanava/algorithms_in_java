package com.google.kickstart;

import java.util.*;

public class BusRoutes {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        int x = 0;
        while(++x <= T) {
            int n = s.nextInt();
            long D = s.nextLong();
            long min = D;
            long[] arr = new long[n];
            for(int i = 0; i < n; i++) {
                arr[i] = s.nextLong();
            }
            for(int i = n-1; i >= 0; i--) {
                min = Math.min(min, (min/arr[i])*arr[i]);
            }
            System.out.println("Case #" + x + ": " + min);
        }
    }
}
