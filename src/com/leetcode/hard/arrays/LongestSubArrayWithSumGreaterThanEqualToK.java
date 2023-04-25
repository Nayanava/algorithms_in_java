package com.leetcode.hard.arrays;

import java.util.Stack;

public class LongestSubArrayWithSumGreaterThanEqualToK {

    public int longestSubArray(int[] arr, int target) {
        int n = arr.length;
        int[] presum = new int[n+1];
        for(int i = 0; i < n; i++) {
            presum[i+1] = presum[i] + arr[i];
        }
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i = 1; i <= n; i++) {
            if(!st.isEmpty() && presum[st.peek()] > presum[i]) {
                st.push(i);
            }
        }
        int res = 0;
        for(int i = n; i >= 1; i--) {
            while(!st.isEmpty() && presum[i] - presum[st.peek()] >= target) {
                res = Math.max(res, i-st.pop());
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int [] arr = {-1, 4, -2, -5, 6, -8};
        LongestSubArrayWithSumGreaterThanEqualToK lswsgtetk = new LongestSubArrayWithSumGreaterThanEqualToK();
        System.out.println(lswsgtetk.longestSubArray(arr, 0));
        arr = new int[]{-5, -6};
        System.out.println(lswsgtetk.longestSubArray(arr, 0));
    }
}