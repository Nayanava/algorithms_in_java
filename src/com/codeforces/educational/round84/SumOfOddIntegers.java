package com.codeforces.educational.round84;

import java.util.Scanner;

public class SumOfOddIntegers {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            long n = s.nextInt(), k = s.nextInt();
            if((n % 2 == 0 && k % 2 == 1) || (n%2 == 1 && k % 2 == 0) || k*k > n) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
