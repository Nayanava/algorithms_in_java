package com.codeforces.tree;

import java.util.Scanner;

public class Friends {
    public static void main(String[] args) {
        Scanner s = new Scanner((System.in));
        int m = s.nextInt();
        int[] arr = new int[6];
        for(int i = 1; i <= m; i++) {
            int u = s.nextInt(), v = s.nextInt();
            arr[u]++;
            arr[v]++;
        }
        boolean pair = false, no_pair = false;
        for(int i = 1; i <= 5; i++) {
            if(arr[i] >= 3) {
                no_pair = true;
            }
            if(arr[i] <= 1) {
                pair = true;
            }
        }
        System.out.println((pair || no_pair) ? "WIN" : "FAIL");
    }
}
