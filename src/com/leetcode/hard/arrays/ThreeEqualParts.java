package com.leetcode.hard.arrays;
/**
 * @author nayanava
 */

import java.io.*;

public class ThreeEqualParts {
    int MOD = (int)1e9 + 7;
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        for(int i = 0, num = 0, j = 1, next = 0; i < n-2; i++) {
            num = (num * 2 + arr[i])%MOD;
            if(i != 0) {
                next = (next - arr[i]*pow(2, j-i)) % MOD;
            }
            while( j < n-1 && (next < num)) {
                next = (next * 2 + arr[j++]) % MOD;
            }
            if(next == num && isSame(arr, num, j)) {
                return new int[] {i, j};
            }
        }
        return new int[]{-1, -1};
    }

    private int pow(int x, int y) {
        if(y == 0) {
            return 1;
        }
        if( y == 1) {
            return x;
        }
        if(y % 2 == 0) {
            return pow((x*x)%MOD, y/2);
        }
        return ( x*pow((x*x)%MOD, (y-1)/2) ) % MOD;
    }

    private boolean isSame(int[] arr, int num, int index) {
        int res = 0;
        while(index < arr.length) {
            res = (res*2 + arr[index++])%MOD;
        }
        return res == num;
    }

    public static void main(String[] args) {
        int arr[] = {0,1,0,1,1};
        ThreeEqualParts tep = new ThreeEqualParts();
        System.out.print(tep.threeEqualParts(arr));
    }
}
