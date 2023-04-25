package com.atcoder.beginner.c162;

import java.util.Scanner;

public class C162 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int K = s.nextInt();
        long res = 0;
        for(int a = 1; a <= K; a ++) {
            for (int b = 1; b <= K; b++) {
                for(int c = 1; c <= K; c++) {
                    res += gcd(a, b, c);
                }
            }
        }
        System.out.println(res);
    }
    private static int gcd(int a, int b, int c) {
        return gcd(gcd(a, b), c);
    }
    private static int gcd(int a, int b) {
        if( a == 0) {
            return b;
        }
        return gcd(b%a, a);
    }
}
