package com.leetcode.hard.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SplitIntoFibonacci {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= S.length(); i++) {
            int first = Integer.valueOf(S.substring(0, i));
            list.add(first);
            if(dfs(first, S.substring(i), list, 1)) {
                break;
            }
            list.remove(list.size() - 1);
        }
        return list;
    }


    private boolean dfs(int first, String S, List<Integer> list, int count) {
        if(S.isEmpty()) {
            return true;
        }
        for(int end = 1; end <= S.length(); end++) {
            int second = Integer.valueOf(S.substring(0, end));
            int third = first+second;
            String remaining = S.substring(end);
            if(remaining.startsWith(String.valueOf(third))) {
                list.add(second);
                System.out.println(second);
                if(dfs(second, S.substring(end), list, count + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
            if(count >= 2) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "123456579";
        SplitIntoFibonacci sif = new SplitIntoFibonacci();
        List<Integer> res = sif.splitIntoFibonacci(s);

    }
}
