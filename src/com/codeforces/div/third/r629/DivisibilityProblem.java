package com.codeforces.div.third.r629;

import java.util.Scanner;

public class DivisibilityProblem {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int a = s.nextInt(), b = s.nextInt();
            if(a % b == 0) {
                System.out.println("0");
            } else {
                System.out.println((((a / b) + 1) * b - a));
            }
        }
    }
}
