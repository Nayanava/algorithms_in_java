package com.leetcode.hard.string;
//https://leetcode.com/discuss/interview-question/494571/Google-or-Onsite-or-Pairs-of-Adjacent-Subsequences-or-Hard
import java.util.*;

public class ManagePairs {
    int mod = (int) 1e9 + 7;
    int p = 31;

    public List<List<Integer>> findPairsInList(String[] L, String[][] pairs) {
        int len = pairs[0][0].length();
        long p_pow = pow(len);
        int n = L.length;
        Map<Long, Set<Integer>>[] map = new Map[n];
        List<List<Integer>> res = new ArrayList<>();
        //Map<Integer, Map<Integer, List<Integer>>> global = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<>();
        }
        int index = 0;
        for (String s : L) {
            long sHash = genHash(s, len);
            map[index].computeIfAbsent(sHash, x -> new HashSet<>()).add(0);
            for (int i = len; i < s.length(); i++) {
                sHash = updateHash(sHash, s.charAt(i - len), s.charAt(i), p_pow);
                map[index].computeIfAbsent(sHash, x -> new HashSet<>()).add(i-len+1);
            }
            index += 1;
        }

        for (String[] pair : pairs) {
            long p1Hash = genHash(pair[0], len);
            long p2Hash = genHash(pair[1], len);
            for (int i = 0; i < n - 1; i++) {
                if (map[i].containsKey(p1Hash) && map[i + 1].containsKey(p2Hash)) {
                    Set<Integer> common = new HashSet<>(map[i].get(p1Hash));
                    common.retainAll(map[i + 1].get(p2Hash));
                    for (int start : common) {
                        if (compare(L[i], start, pair[0]) && compare(L[i + 1], start, pair[1])) {
                            res.add(Arrays.asList(start, i, i + 1));
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean compare(String s, int start, String t) {
        for (char c : t.toCharArray()) {
            if (c != s.charAt(start++)) {
                return false;
            }
        }
        return true;
    }

    private long genHash(String s, int len) {
        long sHash = 0;
        for (int i = 0; i < len; i++) {
            sHash = ((sHash * p) % mod + s.charAt(i) - 'A' + 1 + mod) % mod;
        }
        return sHash;
    }

    private long updateHash(long sHash, char out, char in, long pow) {
        sHash = (sHash - ((out - 'A' + 1) * pow) % mod + mod) % mod;
        sHash = ((sHash * p) % mod + (in - 'A' + 1) + mod) % mod;

        return sHash;
    }

    private long pow(int len) {
        long p_pow = 1;
        for (int i = 1; i < len; i++) {
            p_pow = (p_pow * p) % mod;
        }
        return p_pow;
    }

    public static void main(String [] args) {
    	String[] L = {"SAPTRADFBSF", "ADFASATWSD", "SAPTRADFBSF"};
    	String[][] pairs = {{"RAD", "SAT"}, {"ASA", "TRA"}, {"BSF", "TWS"}};
    	ManagePairs mp = new ManagePairs();
    	List<List<Integer>> res = mp.findPairsInList(L, pairs);
    	for(List<Integer> list : res) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
