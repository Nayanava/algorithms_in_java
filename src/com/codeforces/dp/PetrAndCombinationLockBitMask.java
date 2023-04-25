package com.codeforces.dp;

import java.util.Scanner;

public class PetrAndCombinationLockBitMask {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        for(int i = 0; i <= (1<<n) - 1; i++) {
            int sum = 0;
            for(int d = 0; d < n; d++) {
                if(((1 << d) & i) > 0) {
                    sum -= arr[d];
                } else {
                    sum += arr[d];
                }
            }
            if(sum % 360 == 0) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}
