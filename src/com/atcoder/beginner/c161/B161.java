package com.atcoder.beginner.c161;

import java.util.Scanner;

public class B161 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        Integer[] arr = new Integer[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
            sum += arr[i];
        }
        double div = (double) sum / (4*m);
        for(int i = 0; i < n && m > 0; i++) {
            if(arr[i] >= div) {
                m--;
            }
        }
        if(m <= 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
