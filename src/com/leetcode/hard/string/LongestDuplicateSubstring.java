package com.leetcode.hard.string;

class LongestDuplicateSubstring {
    int p = 31;
    int mod = (int) 1e9 + 7;

    public String longestDupSubstring(String S) {
        String res = "";
        if (S.length() == 0) {
            return res;
        }
        int low = 0, high = S.length();
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int start = isDuplicate(S, mid);
            if (start != -1) {
                res = S.substring(start, start + mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    private int isDuplicate(String s, int len) {
        long p_pow = pow(len);
        for (int x = 0; x < s.length() - len + 1; x++) {
            long pHash = hash(s.substring(x, x + len));
            long sHash = pHash;
            int count = 0;
            for (int i = x + 1; i <= s.length(); i++) {
                if (sHash == pHash) {
                    count += 1;
                    if (count > 1) {
                        return i - 1;
                    }
                }
                if (i == s.length()) {
                    break;
                }
                sHash = (sHash - (p_pow * (s.charAt(i - 1) - 'a' + 1))) % mod;
                sHash = (sHash + s.charAt(i + len - 1) - 'a' + 1) % mod;
            }
        }
        return -1;
    }

    private long pow(int len) {
        long p_pow = 1;
        for (int i = 1; i < len; i++) {
            p_pow = (p_pow * p) % mod;
        }
        return p_pow;
    }

    private long hash(String str) {
        long pHash = 0;
        for (char c : str.toCharArray()) {
            pHash = (pHash * p + (c - 'a' + 1)) % mod;
        }
        return pHash;
    }
}


