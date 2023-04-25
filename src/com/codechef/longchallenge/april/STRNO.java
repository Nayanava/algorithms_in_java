package com.codechef.longchallenge.april;

import java.util.Scanner;

public class STRNO {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int X = s.nextInt(), K = s.nextInt();
            while (X > 1) {
                if(X%2 != 0) {
                    break;
                }
                K--;
                X /= 2;
            }
            System.out.println(X == 1 && K == 0 ? 1 : 0);
        }
    }
}
