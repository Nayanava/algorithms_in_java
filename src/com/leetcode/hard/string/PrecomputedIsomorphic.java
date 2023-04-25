package com.leetcode.hard.string;

import java.util.*;

public class PrecomputedIsomorphic {
    Map<String, List<String>> map;

    public PrecomputedIsomorphic(String[] dict) {
        map = new HashMap<>();
        for (String word : dict) {
            map.computeIfAbsent(encode(word), x -> new ArrayList<>()).add(word);
        }
    }

    public boolean isIsomorphic(String s) {
        String encoded = encode(s);
        return !map.getOrDefault(encoded, Collections.EMPTY_LIST).isEmpty();
    }

    private String encode(String word) {
        int map[] = new int[128];
        StringBuilder encoded = new StringBuilder();
        for (char c : word.toCharArray()) {
            encoded.append(++map[c]).append("_");
        }
        return encoded.toString();
    }

    public static void main(String[] args) {
    	String[] dict = {"sad", "dad", "pop", "cat", "dog", "hat", "egg", "add", "bob", "mom", "foo", "test"};
    	PrecomputedIsomorphic pis = new PrecomputedIsomorphic(dict);
		System.out.println(pis.isIsomorphic("did")); // true because 'did' and 'bob' are isomorphic
		System.out.println(pis.isIsomorphic("tea")); // true because 'tea' and 'dog' are isomorphic
		System.out.println(pis.isIsomorphic("aaa")); //false
		System.out.println(pis.isIsomorphic("abcde")); //false
	}
}
