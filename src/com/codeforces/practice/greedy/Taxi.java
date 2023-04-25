package com.codeforces.practice.greedy;

import java.util.Scanner;

public class Taxi {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[5];
        for(int i = 0; i < n; i++) {
            arr[s.nextInt()]++;
        }
        int count = Math.min(arr[1], arr[3]);
        arr[1] -= count;
        arr[3] -= count;
        count += arr[3];
        count += arr[4] + arr[2]/2;
        arr[2] -= (arr[2]/2)*2;
        if(arr[2] == 1 && arr[1] > 0) {
            count++;
            arr[2] = 0;
            arr[1] = Math.max(0,arr[1]-2);
        }
        if(arr[2] == 1) {
            count++;
        }
        count += (arr[1] % 4 == 0) ? arr[1]/4 : (arr[1]/4) + 1;
        System.out.println(count);
    }
}
