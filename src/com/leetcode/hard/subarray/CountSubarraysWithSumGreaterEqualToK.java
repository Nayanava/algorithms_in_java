package com.leetcode.hard.subarray;

class CountSubarraysWithSumGreaterEqualToK {
    public int countSubarrays(int[] arr, int K) {
        int start = 0, sum = 0, n = arr.length;
        int res = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            while(sum >= K) {
                res += n-i;
                sum -= arr[start++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[] = {0, 0, 0, 0, 1, 1};
        CountSubarraysWithSumGreaterEqualToK csk = new CountSubarraysWithSumGreaterEqualToK();
        System.out.println(csk.countSubarrays(arr, 1));
    }
}
