package com.codeforces.dp;

import java.util.Scanner;

public class FlippingGame {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        int S = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
            S += arr[i];
        }
        if(n == 1) {
            System.out.println(1-arr[0]);
            return;
        }
        int i;
        for(i = 0; i < n && arr[i] == 1; i++);
        if( i == n) {
            System.out.println(n-1);
            return;
        }
        i--;
        int max = 0, sum = 0;
        while(++i < n) {
            sum += (arr[i] == 0 ? 1 : -1);
            if( sum < 0) {
                sum = 0;
            }
            max = Math.max(max, sum);
        }
        System.out.println((S + max));
    }
}
