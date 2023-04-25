package com.codeforces.dp;

import java.util.Scanner;

public class Boredom {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[100001];
        int max = 0;
        for(int i = 0; i < n; i++) {
            int next = s.nextInt();
            max = Math.max(max, next);
            arr[next]++;
        }
        long incl = 0, excl = 0;
        for(int i = 1; i <= max; i++) {
            long max_excl = Math.max(incl, excl);
            incl = excl + (long)arr[i]*i;
            excl = max_excl;
        }
        System.out.println(Math.max(incl, excl));
    }
}
