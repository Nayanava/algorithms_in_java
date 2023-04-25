package com.codeforces.div.third.r617;

import java.util.Scanner;

public class ArrayWithOddSum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int even = 0, odd = 0;
            for(int i = 0; i < n; i++) {
                if (s.nextInt() % 2 == 0) {
                    even++;
                } else
                    odd++;
            }
            System.out.println(even == n ? "NO" : n%2 == 0 && odd == n ? "NO" : "YES");
        }
    }

}
