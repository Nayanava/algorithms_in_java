package com.leetcode.hard.string;

import java.util.HashSet;
import java.util.Set;

class EchoDistinctSubstrings {
    int p = 31;
    int mod = (int) 1e9 + 7;

    public int distinctEchoSubstrings(String s) {
        int res = 0, n = s.length();
		Set<String> seen = new HashSet<>();
        for (int l = 1; l <= n / 2; l++) {
            long sHash = hash(s.substring(0, l));
            long pHash = hash(s.substring(l, 2 * l));
            long pow = pow(l);
            for (int i = 0; i <= n - 2 * l + 1; i++) {
                if (sHash == pHash) {
                    seen.add(s.substring(i-1, i+l-1));
                }
                if (i == n - 2 * l + 1) {
                    break;
                }
                sHash = genHash(sHash, s.charAt(i - 1), s.charAt(i + l - 1), pow);
                pHash = genHash(pHash, s.charAt(i + l - 1), s.charAt(i + 2 * l - 1), pow);
            }
        }
        return seen.size();
    }

    private long genHash(long sHash, char c_out, char c_in, long pow) {
        sHash = (sHash - (c_out - 'a' + 1) * pow) % mod;
        sHash = (sHash * p + (c_in - 'a' + 1)) % mod;
        return sHash;
    }

    public long hash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * p + (str.charAt(i) - 'a' + 1)) % mod;
        }
        return hash;
    }

    public long pow(int len) {
        long p_pow = 1;
        for (int i = 1; i < len; i++) {
            p_pow = (p_pow * p) % mod;
        }
        return p_pow;
    }
}
