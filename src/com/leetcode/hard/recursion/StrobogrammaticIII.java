package com.leetcode.hard.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammaticIII {
    public int strobogrammaticInRange(String low, String high) {
        int res = 0;
        for (int i = low.length(); i <= high.length(); i++) {
            res += helper(low, i, high);
        }
        return res;
    }

    private int helper(String low, int len, String high) {
        List<String> res = Arrays.asList("");
        int n = len;
        if (len % 2 == 1) {
            res = Arrays.asList("0", "1", "8");
            n--;
        }
        for (int i = 2; i <= n; i += 2) {
            List<String> temp = new ArrayList<>();
            for (String num : res) {
                if (i != n) {
                    temp.add("0" + num + "0");
                }
                temp.add("1" + num + "1");
                temp.add("6" + num + "9");
                temp.add("8" + num + "8");
                temp.add("9" + num + "6");
            }
            res = temp;
        }
        int count = res.size();
        if (len == low.length() || len == high.length()) {
            for (String str : res) {
                if (str.length() == low.length() && low.compareTo(str) > 0) {
                    count--;
                } else if (str.length() == high.length() && str.compareTo(high) > 0) {
                    count--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String low = "0", high = "0";
        StrobogrammaticIII strobo = new StrobogrammaticIII();
        System.out.println(strobo.strobogrammaticInRange(low, high));
    }

}
