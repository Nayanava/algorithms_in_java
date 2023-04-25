package com.leetcode.hard.string;

import java.util.*;

public class LexicographicallyShortestAndLargest {
    TreeSet<Character> set = new TreeSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public String findSmallestLexString(String s) {
        TreeMap<Character, List<Integer>> vowels = new TreeMap<>();
        TreeMap<Character, List<Integer>> cons = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                vowels.computeIfAbsent(s.charAt(i), x -> new ArrayList<>()).add(i);
            } else{
                cons.computeIfAbsent(s.charAt(i), x -> new ArrayList<>()).add(i);
            }
        }
        if(vowels.isEmpty()) {
        	return "";
		}
        char ch = vowels.firstKey();
        Character next = cons.higherKey(ch);
        while (next != null) {
            int k = Collections.binarySearch(cons.get(next), vowels.get(ch).get(0));
            if (-(k + 1) < cons.get(next).size()) {
                break;
            } else {
                next = cons.higherKey(next);
            }
        }

        if (null == next) {
            return "";
        }
        String res = "z";
        List<Integer> consList = cons.get(next);
        for (int index : vowels.get(ch)) {
            int k = Collections.binarySearch(consList, index);
            k = -(k + 1);
            if (k == consList.size()) {
                break;
            }
            String cmpString = s.substring(index, consList.get(k)+1);
			if(res.compareTo(cmpString) > 0) {
				res = cmpString;
			}
        }
        return res;
    }

    public static void main(String[] args) {
    	LexicographicallyShortestAndLargest lsl = new LexicographicallyShortestAndLargest();
    	String s = "abust";
    	System.out.println(lsl.findSmallestLexString(s));
	}
}
