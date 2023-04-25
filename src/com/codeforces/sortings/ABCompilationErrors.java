package com.codeforces.sortings;

import java.util.Scanner;

public class ABCompilationErrors {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int xor1 = 0, xor2 = 0, xor3 = 0;
        for(int i = 0; i < n; i++) {
            xor1 ^= s.nextInt();
        }
        for(int i = 0; i < n-1; i++) {
            xor2 ^= s.nextInt();
        }
        for(int i = 0; i < n-2; i++) {
            xor3 ^= s.nextInt();
        }
        System.out.println((xor1^xor2) + "\n" + (xor2^xor3));
    }
}
