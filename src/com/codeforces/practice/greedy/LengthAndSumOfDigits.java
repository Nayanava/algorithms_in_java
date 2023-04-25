package com.codeforces.practice.greedy;

import java.util.Scanner;

public class LengthAndSumOfDigits {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int S = s.nextInt();
        int temp = S, index = 0;
        int[] arr = new int[m];
        if(S == 0 && m > 1 || S > 9*m) {
            System.out.println("-1 -1");
            return;
        }
        while(temp > 0) {
            arr[index] = Math.min(9, temp);
            temp -= arr[index++];
        }
        int[] min = new int[m];
        for(int i = 0; i < m; i++) {
            min[i] = arr[m-i-1];
        }
        if(min[0] == 0) {
            int nz = 0;
            while(nz < m && min[nz] == 0) {
                nz++;
            }
            if(nz < m) {
                min[0] = 1;
                min[nz]--;
            }
        }
        for(int i = 0; i < m; i++)
            System.out.print(min[i]);
        System.out.print(" ");
        for(int i = 0; i < m; i++)
            System.out.print(arr[i]);
    }
}
