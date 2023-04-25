package com.leetcode.hard.backtracking;

import java.lang.reflect.Array;
import java.util.*;

public class StrobogrammaticNumbersII {
    public List<String> findStrobogrammatic(int n) {
        List<String> res = Arrays.asList("");
        if(n % 2 == 1) {
            res = Arrays.asList("0", "1", "8");
            n--;
        }
        for(int i = 1; i <= n/2; i++) {
            List<String> temp = new ArrayList<>();
            for(String s : res) {
                if(i != n/2)
                    temp.add("0" + s + "0");
                temp.add("1" + s + "1");
                temp.add("8" + s + "8");
                temp.add("6" + s + "9");
                temp.add("9" + s + "6");
            }
            res = temp;
        }
        return res;
    }

    private boolean isStrobo(String curr, Map<Character, Character> map) {
        if (curr.length() == 1) {
            return map.get(curr.charAt(0)) == curr.charAt(0);
        }
        for (int start = 0, end = curr.length() - 1; start <= end; start++, end--) {
            if (map.get(curr.charAt(start)) != curr.charAt(end)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StrobogrammaticNumbersII sn = new StrobogrammaticNumbersII();
        sn.findStrobogrammatic(3).forEach(num -> System.out.println(num));
    }
}
