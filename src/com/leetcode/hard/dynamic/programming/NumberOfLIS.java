package com.leetcode.hard.dynamic.programming;

public class NumberOfLIS {
    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        int[][] lis = new int[n][2];
        for(int i = 0; i < n; i++) {
            lis[i][0] = lis[i][1] = 1;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    if(lis[i][0] < 1 + lis[j][0]) {
                        lis[i][0] = lis[j][0] + 1;
                        lis[i][1] = lis[j][1];
                    } else if(lis[i][0] == 1 + lis[j][0]) {
                        lis[i][1] += lis[j][1];
                    }
                }
            }
        }
        int maxVal = 1, maxCount = 1;
        for(int i = 0; i < n; i++) {
            if(maxVal < lis[i][0]) {
                maxVal = lis[i][0];
                maxCount = lis[i][1];
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,4,7};
        NumberOfLIS nLis = new NumberOfLIS();
        System.out.println(nLis.findNumberOfLIS(arr));
    }
}
