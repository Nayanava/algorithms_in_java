package com.atcoder.beginner.c160;

import java.util.Scanner;

public class C160D {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt(), X = s.nextInt(), Y = s.nextInt();
        int[] res = new int[N];
        for(int i = 1; i < N; i++) {
            for(int j = i+1; j <= N; j++) {
                if( i <= X && j >= Y) {
                    res[j-i + (X-Y) + 1]++;
                } else {
                    res[j-i]++;
                }
            }
        }
        for(int i = 1; i < N; i++) {
            System.out.println(res[i]);
        }
    }
}
