package com.leetcode.hard.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class SecretWord {
    //2. length
    //4. sort character set. match
    int mod = (int) 1e9 + 7;

    private int[] genHash(String word, int length) {
        int[] map = new int[26];
        int ri = 0;
        for (int i = length-1; i >= 0; i--) {
        	int index = word.charAt(i) - 'a';
            map[index] += 1 << ri++;
        }
        return map;
    }

    public boolean matchSecretWord(String s, String pat) {
        int length = pat.length();
        int[] pMap = genHash(pat, length);
        Set<Integer> pHash = mapToSet(pMap);
        int[] sMap = genHash(s, length);
        Set<Integer> sHash = mapToSet(sMap);
        for (int i = 1; i <= s.length() - length + 1; i++) {
            if (sHash.containsAll(pHash)) {
                return true;
            }
            if (i == s.length()-length+1) {
                break;
            }
            sHash = modifyMapAndSet(s.charAt(i - 1) - 'a', s.charAt(i + length - 1) - 'a', sMap, length);
        }
        return false;
    }

    private Set<Integer> mapToSet(int[] map) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                set.add(map[i]);
            }
        }
        return set;
    }

    private Set<Integer> modifyMapAndSet(int out, int in, int[] map, int length) {
		map[out] ^= 1 << (length - 1);
        for (int i = 0; i < 26; i++) {
            if (map[i] == 0) {
                continue;
            }
            map[i] = map[i] << 1;
        }
		map[in] += 1;
        return mapToSet(map);
    }

    public static void main(String[] args) {

//    	System.out.println(sw.matchSecretWord(s, p));
		SecretWord sw = new SecretWord();
		String[][] arr = new String[][] {{"abcabcd", "xyzxyz"}, {"abca", "xyzd"},
				{"aaaasds", "gkg"}, {"abababababababa", "cvb"}};
		for(String[] val : arr) {
			boolean res = sw.matchSecretWord(val[0], val[1]);
			System.out.println("encoded: " +val[0] + " secret: " +val[1] + " res: " +res);
		}

	}

}
