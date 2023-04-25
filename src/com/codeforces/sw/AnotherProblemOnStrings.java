package com.codeforces.sw;

import java.util.Scanner;

public class AnotherProblemOnStrings {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int K = s.nextInt();
        char[] arr = s.next().toCharArray();

        if(K == 0) {
            System.out.println(count(arr, K));
            return;
        }
        System.out.println(count(arr, K) - count(arr, K-1));
    }
    private static long count(char[] arr, int K) {
        long res = 0;
        int start = 0, n = arr.length;
        for(int i = 0; i < n; i++) {
            if(arr[i] == '1') {
                K--;
            }
            while(K < 0) {
                if(arr[start++] == '1') {
                    K++;
                }
            }
            res += i-start+1;
        }
        return res;
    }
}
