package com.leetcode.hard.string;

import java.util.ArrayList;
import java.util.List;

public class ShortestConcatenationsToFormString {
    public int countShortest(String src, String target) {
        List<Integer>[] arr = new List[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = new ArrayList<>();
        }
        int index = 0;
        for (char c : src.toCharArray()) {
            arr[c - 'a'].add(index++);
        }

        index = 0;
        int cnt = 0;
        for (char c : target.toCharArray()) {
            List<Integer> list = arr[c - 'a'];
            if (list.isEmpty()) {
                return -1;
            }
            if (list.get(list.size() - 1) < index) {
                index = 0;
                cnt++;
            }
            int k = binSearch(list, index);
            index = list.get(k)+1;
        }
        return cnt;
    }

    private int binSearch(List<Integer> list, int key) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid;
            }

        }
        return low;
    }
    public static void main(String[] args) {
    	String src = "dbcfda";
    	String target = "abcd";

    	ShortestConcatenationsToFormString sc = new ShortestConcatenationsToFormString();
    	System.out.println(sc.countShortest(src, target));
	}
}
