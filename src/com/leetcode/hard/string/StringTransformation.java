package com.leetcode.hard.string;

import java.util.*;

public class StringTransformation {

    public boolean canConvert(String str1, String str2) {
        Map<Character, List<Integer>> map = new HashMap();
        Map<Character, List<Integer>> map2 = new HashMap();
        int index = 0;
        for(char c : str1.toCharArray()) {
            List<Integer> list;
            if(!map.containsKey(c)) {
                list = new ArrayList<>();
            }
            else {
                list = map.get(c);
            }
            list.add(index);
            map.put(c, list);
            index++;
        }

        index = 0;
        for(char c : str2.toCharArray()) {
            List<Integer> list;
            if(!map2.containsKey(c)) {
                list = new ArrayList<>();
            }
            else {
                list = map2.get(c);
            }
            list.add(index);
            map2.put(c, list);
            index++;
        }

        if(map2.size() == 26)
            return false;

        for(Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            if(entry.getValue().size() > 0) {
                List<Integer> indices = entry.getValue();
                Set<Character> characterSet = new HashSet<>();
                for( int id : indices) {
                    characterSet.add(str2.charAt(id));
                }
                if(characterSet.size() != 1) {
                    return false;
                }

            }
        }

        return true;
    }

    public static void main(String[] args) {
        StringTransformation stringTransformation = new StringTransformation();
        String str1 = "leetcode";
        String str2 = "codeleet";
        System.out.println(stringTransformation.canConvert(str1, str2));
    }
}
