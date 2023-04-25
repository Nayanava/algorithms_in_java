package com.leetcode.hard.string;

import java.util.*;

public class OccurenceInABigram {
    int p = 128;
    int mod = (int)1e9+7;
    public String[] findOcurrences(String text, String first, String second) {
        String pattern = first + " " + second;
        int len = pattern.length();
        long p_pow = pow(len);
        long pHash = genHash(pattern);
        long sHash = genHash(text.substring(0, len));
        int n = text.length();
        List<String> list = new ArrayList<>();
        for( int i = 1; i <= n-len+1; i++) {
            if(pHash == sHash) {
                list.add(findNextWord(text, i + len));
            }
            if( i == n-len+1) {
                break;
            }
            sHash = genHash(sHash, text.charAt(i-1), text.charAt(i+len-1), p_pow);
        }
        String[] res = new String[list.size()];
        int index = 0;
        for(String word : list) {
            res[index++] = word;
        }
        return res;
    }

    private long pow(int len) {
        long p_pow = 1;
        for(int i = 1; i < len; i++) {
            p_pow = (p_pow * p) % mod;
        }
        return p_pow;
    }

    private long genHash(String s) {
        long sHash = 0;
        for(char c : s.toCharArray()) {
            sHash = (sHash * p + c) % mod;
        }
        return sHash;
    }

    private long genHash(long hash, char c_out, char c_in, long p_pow) {
        hash = (hash - (c_out*p_pow) % mod + mod) % mod;
        return (hash * p + c_in)%mod;

    }

    private String findNextWord(String s, int start) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < s.length() && s.charAt(i) != ' '; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String text = "alice is a good girl she is a good student";
        String first = "a";
        String second = "good";
        OccurenceInABigram oab = new OccurenceInABigram();
        Arrays.stream(oab.findOcurrences(text, first, second)).forEach(word -> System.out.print(word + " "));
    }
}