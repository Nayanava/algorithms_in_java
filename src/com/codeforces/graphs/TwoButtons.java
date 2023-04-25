package com.codeforces.graphs;

import java.util.Scanner;

public class TwoButtons {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt(), b = s.nextInt();
        int res = 0;
        while(b > a) {
            if(b % 2 == 1) {
                b++;
                res++;
            }
            b /= 2;
            res++;
        }
        res += (a-b);
        System.out.println(res);
    }
}
