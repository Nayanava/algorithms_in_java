package com.atcoder.beginner.c163;

import java.util.*;

public class C163 {
    static Integer[] dp;
    static int INF = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] arr = new int[N];
        int max = 0;
        for(int i = 0; i < N-1; i++) {
            int curr = s.nextInt()-1;
            max = Math.max(max, curr);
            arr[curr]++;
        }
        for(int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }
}
