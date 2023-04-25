package com.codeforces.div.second.r577;

import java.util.Scanner;

public class ZeroArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long sum = 0, max = 0;
        for(int i = 0; i < n; i++) {
            int next = s.nextInt();
            sum += next;
            max = Math.max(max, next);
        }
        System.out.println(sum%2 == 0 && (2*max - sum) <= 0 ? "YES" : "NO");
    }
}
