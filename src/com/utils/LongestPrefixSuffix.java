package com.utils;

import java.util.Scanner;

public class LongestPrefixSuffix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int[] res = lps(s);
        for(int i = 0; i < s.length(); i++) {
            System.out.print(res[i] + " ");
        }
    }

    private static int[] lps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int index = 0;
        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == s.charAt(index)) {
                lps[i] = ++index;
            } else {
                if(index != 0) {
                    index = lps[index-1];
                    i--;
                } else {
                    lps[i] = index;
                }

            }
        }
        return lps;
    }
}
