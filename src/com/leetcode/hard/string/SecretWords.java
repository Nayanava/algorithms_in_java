package com.leetcode.hard.string;

import java.util.Arrays;

public class SecretWords {
    public boolean isMatching(String s, String p) {
        int[] pat = new int[p.length()];
        int[] map = new int[128];
        for (int i = 0; i < pat.length; i++) {
            pat[i] = ++map[p.charAt(i)];
        }
        Arrays.fill(map, 0);
        int[] src = new int[pat.length];
        for (int i = 0; i < pat.length; i++) {
            src[i] = ++map[s.charAt(i)];
        }
        if(isMatching(src, pat)) {
        	return true;
		}
        int len = pat.length;
        for (int i = len, start = 1; i < s.length(); i++, start++) {
            Arrays.fill(map, 0);
            for (int k = start; k <= i; k++) {
                src[k - start] = ++map[s.charAt(k)];
            }
            if (isMatching(src, pat)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatching(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    	String a = "aaaasds", b = "gkg";
    	SecretWords sw = new SecretWords();
    	System.out.println(sw.isMatching(a, b));
	}
}
