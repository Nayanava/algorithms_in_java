package com.leetcode.hard.arrays;

import java.util.ArrayList;
import java.util.List;

public class MinimumSwaps {
    public int minimumSwaps(String s, char given) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == given) {
                list.add(i);
            }
        }
        int mid = list.size()/2;

        int minDistance = 0;
        int index = 0;
        for (int number : list) {
            minDistance += Math.abs(number - list.get(mid)) - Math.abs(mid - index);
            index++;
        }
        return minDistance;
    }

    public static void main(String[] args) {
    	String s = "bbaabb";
    	System.out.println(new MinimumSwaps().minimumSwaps(s, 'b'));
	}
}
