package com.leetcode.hard.subarray;

public class MaximumConsecutiveOnes {
    public int longestOnes(int[] A, int k) {
        int n = A.length, j = 0, res = 0;
        for(int i = 0; i < n; i++) {
            if(A[i] == 0)
                k--;
            while( k < 0) {
                if(A[i] == 0) {
                    k++;
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int arr[] = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        MaximumConsecutiveOnes mco = new MaximumConsecutiveOnes();
        System.out.println(mco.longestOnes(arr, k));
    }
}
