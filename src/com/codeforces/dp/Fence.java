package com.codeforces.dp;

import java.util.Scanner;

public class Fence {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        int[] arr = new int[n+1];
        for(int i = 1; i <= n; i++) {
            arr[i] = s.nextInt();
        }
        int sum = 0, min = 0, res= 1;
        for(int i = 1; i <= k; i++) {
            sum += arr[i];
        }
        min = sum;
        for(int start = 1, i = k+1; i <= n; i++, start++) {
            sum += arr[i];
            sum -= arr[start];
            if(min > sum) {
                min = sum;
                res = start+1;
            }
        }
        System.out.println(res);
    }
}
