package com.leetcode.hard.subarray;

public class MinimumBalanceString {
    public int balancedString(String s) {
        int length = s.length();
        char[] arr = s.toCharArray();
        if(length % 4 != 0) {
            return -1;
        }
        int n = length/4;
        int[] qwer = new int[128];
        for(int i = 0; i < length; i++) {
            qwer[arr[i]]++;
        }

        int i = 0, minLength = length+1;
        for(int j = 0; j < length; j++) {
            qwer[arr[j]]--;
            while(i < length && qwer['Q'] <= n && qwer['W'] <= n && qwer['E'] <= n && qwer['R'] <= n) {
                minLength = Math.min(minLength, j - i + 1);
                qwer[arr[i]]++;
                i++;
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        String s = "QQQQ";
        MinimumBalanceString mbs = new MinimumBalanceString();
        System.out.println(mbs.balancedString(s));
    }
}
