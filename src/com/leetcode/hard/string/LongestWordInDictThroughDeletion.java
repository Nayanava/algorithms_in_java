package com.leetcode.hard.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestWordInDictThroughDeletion {
    public String findLongestWord(String s, List<String> d) {
        List<Integer>[] arr = new List[26];
        int maxLength = 0;
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a'].add(i);
        }
        for (String word : d) {
            int i = 0;
            if(arr[word.charAt(0)-'a'].isEmpty()) {
                continue;
            }
            int sIndex = arr[word.charAt(0)-'a'].get(0) + 1;
            for (i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                List<Integer> list = arr[c - 'a'];
                if (list.isEmpty()) {
                    break;
                }
                int k = binarySearch(list, sIndex);
                if (k == -1) {
                    break;
                }
                if(k == list.size()) {
                    break;
                }
                sIndex = list.get(k) + 1;
            }
            if (i == word.length()) {
                if(maxLength < word.length() || (maxLength == word.length() && word.compareTo(res) > 0)) {
                    maxLength = word.length();
                    res = word;
                }
            }
        }
        return res;
    }

    int binarySearch(List<Integer> list, int key) {
        //searching for the higher of the key.
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) < key) {
                low = mid+1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        String s = "aewfafwafjlwajflwajflwafj";
        String[] words = {"ewafeffewafewf","apple","ewaf","awefawfwaf","awef","awefe"};
        LongestWordInDictThroughDeletion obj = new LongestWordInDictThroughDeletion();
        System.out.print(obj.findLongestWord(s, Arrays.asList(words)));
    }
}