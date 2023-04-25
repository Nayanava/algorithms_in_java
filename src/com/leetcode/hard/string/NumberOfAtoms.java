package com.leetcode.hard.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NumberOfAtoms {
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = elementCount(formula, 1);
        PriorityQueue<String> q = new PriorityQueue<>();
        for(String key : map.keySet()) {
            q.offer(key);
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            String key = q.poll();
            sb.append(key);
            if(map.get(key) > 1) {
                sb.append(map.get(key));
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> elementCount(String formula, int count) {
        int start = 0, n = formula.length();
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        while( i < n ) {
            StringBuilder ele = new StringBuilder();
            if(isUpper(formula.charAt(i))) {
                ele.append(formula.charAt(i++));
            }
            for(; i < n && isSmall(formula.charAt(i)); i++) {
                ele.append(formula.charAt(i));
            }
            if(i < n && Character.isDigit(formula.charAt(i))) {
                int num = 0;
                for(;i < n && Character.isDigit(formula.charAt(i)); i++) {
                    num = num * 10 + formula.charAt(i) - '0';
                }
                map.put(ele.toString(), map.getOrDefault(ele.toString(), 0) + num);
            } else if(ele.length() != 0){
                map.put(ele.toString(), map.getOrDefault(ele.toString(), 0) + 1);
            }
            if(i < n && formula.charAt(i) == '(') {
                start = i++;
                for(int open = 1; i < n && open != 0; i++) {
                    if(formula.charAt(i) == '(') {
                        open++;
                    } else if(formula.charAt(i) == ')') {
                        open--;
                    }
                }
                int end = i-1;
                int num = 0;
                for(;i < n && Character.isDigit(formula.charAt(i)); i++) {
                    num = num * 10 + formula.charAt(i) - '0';
                }
                Map<String, Integer> temp = elementCount(formula.substring(start+1, end), num);
                for(String key : temp.keySet()) {
                    map.put(key, map.getOrDefault(key, 0) + temp.get(key));
                }
            }
        }
        if(count > 1) {
            for(String key : map.keySet()) {
                map.put(key, map.get(key)*count);
            }
        }
        return map;
    }
    private boolean isSmall(char c) {
        return c >= 'a' && c <= 'z';
    }
    private boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static void main(String[] args) {
        String s = "((HHe28Be26He)9)34";
        NumberOfAtoms na = new NumberOfAtoms();
        System.out.println(na.countOfAtoms(s));
    }
}
