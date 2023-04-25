package com.leetcode.hard.string;

import java.util.*;
import java.util.stream.Collectors;

class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Arrays.sort(strings, (a, b)->a.length() - b.length());
        int n = strings[strings.length-1].length()+1;
        Map<String, List<String>>[] mapArr = new Map[n];
        for(int i = 0; i < n; i++) {
            mapArr[i] = new HashMap<>();
        }
        List<List<String>> res = new ArrayList<>();
        int i = 0;
        List<String> list = new ArrayList<>();
        for(; i < n; i++) {
            if(strings[i].length() == 1) {
                list.add(strings[i]);
            } else {
                break;
            }
        }
        if(!list.isEmpty()) {
            res.add(list);
        }
        mapArr[strings[i].length()].computeIfAbsent(strings[i], x -> new ArrayList<>()).add(strings[i]);
        for(int start = i+1; start < strings.length; start++) {
            boolean matchFound = false;
            for(String string : mapArr[strings[start].length()].keySet()) {
                if(matchedShift(string, strings[start])) {
                    mapArr[strings[start].length()].get(string).add(strings[start]);
                    matchFound = true;
                    break;
                }
            }
            if(!matchFound) {
                mapArr[strings[start].length()].computeIfAbsent(strings[start], x-> new ArrayList<>()).add(strings[start]);
            }
        }
        for(Map<String, List<String>> map : mapArr) {
            res.addAll(map.values().stream().collect(Collectors.toList()));
        }
        return res;
    }
    
    boolean matchedShift(String a, String b) {
        if(b.compareTo(a) > 0) {
            return matchedShift(b, a);
        }
        char[] chs1 = a.toCharArray();
        char[] chs2 = b.toCharArray();
        int diff = chs1[0] - chs2[0];
        for(int i = 1; i < a.length(); i++) {
            if(chs1[i] < chs2[i]) {
                chs1[i] += 26;
            }
            if(diff != chs1[i]- chs2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] arr = {"abc","bcd","acef","xyz","az","ba","a","z"};
        GroupShiftedStrings gss = new GroupShiftedStrings();
        gss.groupStrings(arr);
    }
}