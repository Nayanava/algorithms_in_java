package com.codeforces.graphs;

import java.util.Scanner;

public class VatsyaAndIsolatedVertices {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong(), m = s.nextLong();
        long min = n-2*m;
        min = min < 0 ? 0 : min;
        long used = 0, total = 0;
        for(int i = 1; i <= n && total < m; i++) {
            total += (i-1);
            used++;
        }
        System.out.print(min + " " + (n-used));
    }
}
