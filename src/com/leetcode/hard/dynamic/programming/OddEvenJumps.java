package com.leetcode.hard.dynamic.programming;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OddEvenJumps {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        if(n == 0 || n == 1) {
            return n;
        }
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        boolean dp[][] = new boolean[n][2];
        dp[n-1][0] = dp[n-1][1] = true;

        dp[n-2][0] = (arr[n-2] < arr[n-1]) ? false : true;
        dp[n-2][1] = (arr[n-2] > arr[n-1]) ? false : true;
        tree.put(arr[n-1], n-1);
        tree.put(arr[n-2], n-2);
        for(int i = n-3; i >= 0; i--) {
            //even move
            Map.Entry<Integer, Integer> floorEntry = tree.floorEntry(arr[i]);
            Map.Entry<Integer, Integer> ceilingEntry = tree.ceilingEntry(arr[i]);
            dp[i][0] =  (floorEntry == null) ? false : dp[floorEntry.getValue()][1];
            dp[i][1] = (ceilingEntry == null) ? false : dp[ceilingEntry.getValue()][0];

            tree.put(arr[i], i);
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            count += dp[i][1] ? 1 : 0;
        }

        return count;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 1, 1, 4};
        OddEvenJumps oej = new OddEvenJumps();
        System.out.println(oej.oddEvenJumps(arr));
        HashMap<Integer, Integer> map = new HashMap<>();
    }
}
