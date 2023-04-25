package com.atcoder.beginner.c160;

import java.util.Scanner;

public class C160C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int K = s.nextInt();
        int n = s.nextInt();

        if( n == 1) {
            System.out.println("0");
        }
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        int res = K;
        for(int i = 0; i < n; i++) {
            if(i == 0)
                res = Math.min(res, Math.min(arr[n-1]-arr[0], K-(arr[i+1] - arr[0])));
            else if(i == n-1)
                res = Math.min(res, Math.min(Math.abs(arr[n-1]-arr[0]), K - (arr[n-1] - arr[n-2])));
            else
                res = Math.min(res, Math.min(K - (arr[i] - arr[i-1]), K - (arr[i+1] - arr[i])));
        }
        System.out.println(res);
    }
}
