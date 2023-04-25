package com.codeforces.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StringPermutation {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            String str = s.next();
            char[] chs = str.toLowerCase().toCharArray();
            Arrays.sort(chs);
            Set<String> perms = permutations(chs);
            for(String perm : perms) {
                System.out.print(perm + " ");
            }
            System.out.println();
        }
    }

    private static Set<String> permutations(char[] chs) {
        Set<String> strings = new HashSet<>();
        strings.add(String.valueOf(chs));
        int n = chs.length;
        while(true) {
            int i = n-2;
            for(; i >= 0; i--) {
                if(chs[i] < chs[i+1]) {
                    break;
                }
            }
            if(i < 0) {
                break;
            }
            for(int k = n-1; k > i; k--) {
                if(chs[k] > chs[i]) {
                    swap(chs, k, i);
                    break;
                }
            }
            reverse(chs, i+1, n-1);
            strings.add(String.valueOf(chs));
        }
        return strings;
    }
    private static void reverse(char[] chs, int i, int j) {
        for(int start = i, end = j; start < end; start++, end--) {
            swap(chs, start, end);
        }
    }
    private static void swap(char[] chs, int start, int end) {
        char temp = chs[start];
        chs[start] = chs[end];
        chs[end] = temp;
    }

}
