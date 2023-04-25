package com.codeforces.educational.round1;

import java.util.Scanner;

public class TrickySum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            long n = s.nextLong();
            long sum = 0;
            for (int i = 1; i <= n; i *= 2) {
                sum -= i;
            }
            sum *= 2;
            sum += n * (n + 1) / 2;
            System.out.println(sum);
        }
    }
}
