package com.leetcode.hard.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            int[] arr = new int[26];
            for(char c : str.toCharArray()) {
                arr[c-'a'] ++;
            }
            StringBuilder bitSet = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                bitSet.append(arr[i]);
            }
            System.out.println(str + " " + bitSet);
            map.putIfAbsent(bitSet.toString(), new ArrayList<>());
            map.get(bitSet.toString()).add(str);
        }
        return map.entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] arr = new String[] {"eat","tea","tan","ate","nat","bat"};
        GroupAnagrams ga = new GroupAnagrams();
        List<List<String>> result = ga.groupAnagrams(arr);

    }
}
