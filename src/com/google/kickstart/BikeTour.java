package com.google.kickstart;

import java.util.*;

public class BikeTour {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        int x = 0;
        while(++x <= T) {
            System.out.println();
            int n = s.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
            }
            int res = 0;
            for(int i = 1; i < n-1; i++) {
                if(arr[i] > arr[i-1] && arr[i] > arr[i+1])
                    res++;
            }
            System.out.print("Case #" + x + ": " + res);
        }
    }
}
