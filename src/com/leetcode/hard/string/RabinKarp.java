package com.leetcode.hard.string;

public class RabinKarp {
    int p = 31;
    int m = (int)1e9+7;
    private long pow(int length) {
        long p_pow = 1;
        for(int i = 1; i < length; i++) {
            p_pow = (p_pow*p)%m;
        }
        return p_pow;
    }

    private long hash(String pat, int length) {
        long hash = 0;
        for(int i = 0; i < length; i++) {
            hash = (hash * p + (pat.charAt(i) - 'a' + 1)) % m;
        }
        return hash;
    }

    private int stringMatch(String str, String pat) {
        int length = pat.length();
        int n = str.length();
        if(str.length() < length) {
            return 0;
        }
        int res = 0;
        long p_pow = pow(length);
        long pHash = hash(pat, length);
        long sHash = hash(str, length);
        for(int i = 1; i <= n-length+1; i++) {
            if(sHash == pHash) {
                if(str.substring(i-1, i+length-1).equals(pat)){
                    res++;
                }
            }
            if(i == n-length+1)
                break;
            sHash = (sHash - (str.charAt(i-1) - 'a' + 1) * p_pow)%m;
            sHash = (sHash*p + (str.charAt(i + length - 1) - 'a' + 1))%m;
        }
        return res;
    }

    public static void main(String[] args) {
        String S = "ababcbabcabbabc";
        String pat = "abc";
        RabinKarp rk = new RabinKarp();
        System.out.println(rk.stringMatch(S, pat));
    }
}

