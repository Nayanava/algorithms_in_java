package com.codeforces.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JourneyPlanning {
    /*
    find all pairs where b[j] - b[i] = j-i
    
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Map<Integer, Long> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int temp = s.nextInt();
            map.put(temp - i, map.getOrDefault(temp - i, 0L) + temp);
        }
        long max = 0;
        for(int key : map.keySet()) {
            max = Math.max(max, map.get(key));
        }
        System.out.println(max);
    }
}
