package com.codeforces.sortings;

import java.util.*;

public class DominatedSubarray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int min = Integer.MAX_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int num = s.nextInt();
                if(map.containsKey(num)) {
                    min = Math.min(min, i - map.get(num) + 1);
                }
                map.put(num, i);
            }
            System.out.println(min == Integer.MAX_VALUE ? "-1" : min);
        }
    }
}
