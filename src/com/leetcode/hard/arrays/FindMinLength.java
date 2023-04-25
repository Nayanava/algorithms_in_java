package com.leetcode.hard.arrays;

public class FindMinLength {
    public int findLength(int[] arr, int K) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        int min = n, second_min = n;
        int start = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            while (start < n && sum > K) {
                sum -= arr[start++];
            }
            if (sum == K) {
                if (i - start + 1 < min) {
                    second_min = min;
                    min = i - start + 1;
                } else if (i - start + 1 < second_min) {
                    second_min = i - start + 1;
                }
            }
        }
        return min + second_min > n ? -1 : min + second_min;
    }
    public static void main(String[] args) {
    	int arr[] = {1, 2, 2, 5, 2, 4, 2};
    	int K = 5;
    	FindMinLength fml = new FindMinLength();
    	System.out.print(fml.findLength(arr, K));
	}
}
