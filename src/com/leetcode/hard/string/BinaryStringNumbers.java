package com.leetcode.hard.string;

import java.util.HashSet;
import java.util.Set;

class BinaryStringNumbers {
    public boolean queryString(String s, int N) {
        int maxLength = Integer.numberOfTrailingZeros(Integer.highestOneBit(N))+1;
        Set<Integer> seen = new HashSet<>();
        int found = 0;
        int pow = 1;
        for(int i = 1; i <= maxLength; i++) {
            int hash = genHash(s, i);
            for(int k = i; k <= s.length(); k++) {
                if(hash > 0 && hash <= N && seen.add(hash)) {
                    found++;
                }
                if(k == s.length()) {
                    break;
                }
                hash -= (s.charAt(k-i) - '0')*pow;
                hash = hash*2 + (s.charAt(k) - '0');
                
            }
            pow *= 2;
        }
        for(int num : seen) {
            System.out.print(num + " ");
        }
        System.out.println(found);
        return found == N;
    }
    int genHash(String s, int len) {
        int hash = 0;
        for(int i = 0; i < len; i++) {
            hash = hash*2 + s.charAt(i) - '0';
        }
        return hash;
    }

    public static void main(String[] args) {
        int N = 7;
        String s = "1011100";
        System.out.println(new BinaryStringNumbers().queryString(s, N));
    }
}