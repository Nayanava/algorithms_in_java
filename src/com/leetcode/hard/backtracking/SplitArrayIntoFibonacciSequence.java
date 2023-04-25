package com.leetcode.hard.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {
    public List<Integer> splitIntoFibonacci(String s) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= s.length()/2; i++) {
            long first = Long.valueOf(s.substring(0, i));
            if(first > Integer.MAX_VALUE) {
                break;
            }
            list.add((int)first);
            if(dfs(first, -1, s.substring(i), list)) {
                break;
            }
            list.remove(list.size() - 1);
        }
        return list;
    }
    private boolean dfs(long first, long second, String s, List<Integer> list) {
        if(s.isEmpty()) {
            list.add((int)second);
            return true;
        }
        if(second == -1) {
            for(int i = 1; i <= s.length(); i++) {
                String secondStr = s.substring(0, i);
                if(i > 1 && secondStr.startsWith("0")) {
                    return false;
                }
                second = Long.valueOf(secondStr);
                if(second > Integer.MAX_VALUE) {
                    return false;
                }
                String third = String.valueOf(first + second);
                if(s.substring(i).length() >= third.length() && s.substring(i).startsWith(third)) {
                    list.add((int)second);
                    if(dfs(second, Long.valueOf(third), s.substring(secondStr.length() + third.length()), list)) {
                        return true;
                    }
                    list.remove(list.size()-1);
                }
            }
        } else {
            String third = String.valueOf(first + second);
            String sub = s.substring(0, third.length());
            if(sub.length() >= third.length() && sub.startsWith(third)) {
                list.add((int)second);
                if(dfs(second, Long.valueOf(third), s.substring(third.length()), list)) {
                    return true;
                }
                list.remove(list.size()-1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String num = "11235813";
        SplitArrayIntoFibonacciSequence saifs = new SplitArrayIntoFibonacciSequence();
        saifs.splitIntoFibonacci(num);
    }
}
