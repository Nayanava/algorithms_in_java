package com.leetcode.hard.grundy.numbers;

import java.io.*;
import java.util.*;

public class TowerBreakers {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int T = s.nextInt(), max = 0;
        int arr[][] = new int[T][2];
        for(int x = 0; x < T; x++) {
            int n = s.nextInt();
            int m = s.nextInt();
            arr[x][0] = n;
            arr[x][1] = m;
            max = Math.max(arr[x][1], max);
        } 
        int[] dp = new int[max+1];
        dp[0] = dp[1] = 0;
        for(int i = 2; i <= max; i++) {
            Set<Integer> set = new HashSet<>();
            for(int j = 1; j <= Math.sqrt(i); j++) {
                if(i % j == 0) {
                    set.add(dp[j]);
                }
            }
            dp[i] = mex(set);
        }
        for(int i = 0; i < T; i++) {
            if(arr[i][0] % 2 == 0) {
                System.out.println("2");
            } else {
                System.out.println(dp[arr[i][1]] == 0 ? "2" : "1");
            }
        }
    }
    
    private static int mex(Set<Integer> set) {
        for(int i = 0; i < set.size(); i++) {
            if(!set.contains(i)) {
                return i;
            }
        }
        return set.size();
    }
}