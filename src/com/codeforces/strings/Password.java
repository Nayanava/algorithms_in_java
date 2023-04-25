package com.codeforces.strings;

import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        int[] lps = lps(str);
        int n = str.length();
        if(lps[n-1] == 0) {
            System.out.print("Just a legend");
            return;
        }
        for(int i = 0; i < n-1; i++) {
            if(lps[i] == lps[n-1]) {
                System.out.println(str.substring(0, lps[n-1]));
                return;
            }
        }
        if(lps[lps[n-1]-1] == 0) {
            System.out.println("Just a legend");
            return;
        }
        System.out.print(str.substring(0, lps[lps[n-1]-1]));
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
