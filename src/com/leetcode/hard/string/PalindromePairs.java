package com.leetcode.hard.string;

import java.util.*;

public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        int index = 0;
        for(String word : words) {
            map.put(reversed(word), index++);
        }
        for(String word : words) {
            for(int i = word.length(); i >= 0; i--) {
                Integer contained;
                String suff = word.substring(i), pref = word.substring(0, i);
                if( null != (contained = map.get(pref)) && contained != index && isPalindrome(suff)) {
                    res.add(Arrays.asList(index, contained));
                }
                if((contained = map.get(suff)) != null && contained != index && isPalindrome(pref)) {
                    res.add(Arrays.asList(contained, index));
                }
            }
            index++;
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        for(int start = 0, end = s.length()-1; start < end; start++, end--) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
        }
        return true;
    }
    private String reversed(String word) {
        char[] chs = word.toCharArray();
        for(int start = 0, end = chs.length-1; start < end; start++, end--) {
            char temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;
        }
        return String.valueOf(chs);
    }

    public static void main(String[] args) {
        String[] arr = {"abcd","dcba","lls","s","sssll"};
        PalindromePairs pp = new PalindromePairs();
        System.out.println(pp.palindromePairs(arr));
    }
}
