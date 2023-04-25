package com.leetcode.hard.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubstringWithConcatentionOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        int[] chars = new int[26];
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                chars[c - 'a']++;
            }
        }
        long wordsHash = genHash(chars);
        int dictLen = words.length, wordLen = words[0].length();
        int windowLen = dictLen * wordLen;
        chars = new int[26];
        for (int i = 0; i < windowLen; i++) {
            chars[s.charAt(i) - 'a']++;
        }
        List<Integer> list = new ArrayList<>();
        long stringHash = genHash(chars);
        for (int i = windowLen, start = 0; i <= s.length(); i++, start++) {
            if (stringHash == wordsHash) {
                if (matchStrings(s.substring(start, i),
                        map.entrySet()
                                .stream()
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                                ,wordLen)){
                    list.add(start);
                }
            }
            if (i == s.length()) {
                break;
            }
            stringHash += 1 << (s.charAt(i) - 'a');
            stringHash -= 1 << (s.charAt(start) - 'a');
        }

        return list;
    }

    private long genHash(int[] arr) {
        long hash = 0;
        for (int i = 0; i < arr.length; i++) {
            hash = hash + arr[i] * (1 << i);
        }
        return hash;
    }

    private boolean matchStrings(String s, Map<String, Integer> map, int wordLen) {
        for (int i = 0; i < s.length() - wordLen + 1; i += wordLen) {
            String str = s.substring(i, i + wordLen);
            if (!map.containsKey(str)) {
                return false;
            }
            map.put(str, map.get(str) - 1);
            if (map.get(str) == 0) {
                map.remove(str);
            }
        }
        return map.isEmpty();
    }
}

