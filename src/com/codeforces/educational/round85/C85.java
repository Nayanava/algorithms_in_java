package com.codeforces.educational.round85;

import java.util.Scanner;

public class C85 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            long a[] = new long[n+1];
            long b[] = new long[n+1];
            for(int i = 0; i < n; i++) {
                a[i] = s.nextLong();
                b[i] = s.nextLong();
            }
            a[n] = a[0];
            b[n] = b[0];
            long startWith0 = a[0];
            for(int i = 1; i < n; i++) {
                startWith0 += a[i] < b[i-1] ? 0 : a[i] - b[i-1];
            }
            long startWith1 = a[1];
            for(int i = 2; i <= n; i++) {
                startWith1 += a[i] < b[i-1] ? 0 : a[i] - b[i-1];
            }
            System.out.println(Math.min(startWith0, startWith1));
        }
    }
}
