package com.atcoder.beginner.c161;

import java.util.Scanner;

public class F161 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long N = s.nextLong(), res = 1;
        long sqrt = (long)Math.sqrt(N);
        for(long i = 2; i <= N; i++) {
            long temp = N;
            if(temp%i == 0) {
                temp /= i;
            }
            if(temp % i == 1) {
                res++;
            }
        }
        System.out.println(res);
    }
}
