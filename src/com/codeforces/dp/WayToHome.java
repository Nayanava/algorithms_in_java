package com.codeforces.dp;

import java.util.Scanner;

public class WayToHome {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int d = s.nextInt();
        String str = s.next();
        char[] arr = str.toCharArray();
        int curr_start = 0, res = 1, farthest = 0;
        while(curr_start < n-1) {
            for(int i = 1; i <= d && curr_start+i < n; i++) {
                if(arr[curr_start+i] == '1') {
                    farthest = curr_start + i;
                }
            }
            if(farthest == n-1) {
                break;
            }
            if(farthest == curr_start) {
                res = -1;
                break;
            }
            res++;
            curr_start = farthest;
        }
        System.out.println(res);
    }
}
