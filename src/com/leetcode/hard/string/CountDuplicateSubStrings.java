package com.leetcode.hard.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountDuplicateSubStrings {
    int p = 31;
    int mod = (int) 1e9 + 7;

    public long hash(String s, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (hash * p + (s.charAt(i) - 'a' + 1)) % mod;
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

    public Set<Map<String, Integer>> countAllDuplicateSubStrings(String s) {
        //calculate pow everytime for length
        //for every length generate the hash initially and then start the iteration
        //store hash in a map
        Map<Long, Map<String, Integer>> map = new HashMap<>();
        int n = s.length();
        for (int l = 1; l <= n; l++) {
            //calculate the sHash of the starting string from i = 0 to len;
            //calculate the pow for the length
            long sHash = hash(s.substring(0, l), l);
            long p_pow = pow(l);
            for (int i = 1; i <= n - l + 1; i++) {
                map.putIfAbsent(sHash, new HashMap<>());
                String temp = s.substring(i - 1, i + l - 1);
                Map<String, Integer> tempMap = map.get(sHash);
                tempMap.put(temp, tempMap.getOrDefault(temp, 0) + 1);
                if (i == n - l + 1) {
                    break;
                }
                sHash = sHash - ((s.charAt(i - 1) - 'a' + 1) * p_pow) % mod;
                sHash = (sHash * p + (s.charAt(i + l - 1) - 'a' + 1)) % mod;
            }
        }

        Set<Map<String, Integer>> res = new HashSet<>();
        for (Map<String, Integer> value : map.values()) {
            res.add(value);
        }
        return res;
    }

    public static void main(String[] args) {
        String word = "abcabcabcbcd";
        CountDuplicateSubStrings cds = new CountDuplicateSubStrings();
        Set<Map<String, Integer>> res = cds.countAllDuplicateSubStrings(word);
        for (Map<String, Integer> map : res) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

}
