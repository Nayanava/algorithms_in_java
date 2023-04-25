package com.codeforces.dp;

import java.util.*;

public class SashaAndBitOfRelax {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long res = 0;
        int xored = 0;
        Map<Integer, Integer> evenMap = new HashMap<>();
        Map<Integer, Integer> oddMap = new HashMap<>();
        evenMap.put(0,1);
        for(int i = 1; i <= n; i++) {
            xored ^= s.nextInt();
            Map<Integer, Integer> map = (i % 2 == 0) ? evenMap : oddMap;
            res += map.getOrDefault(xored, 0);
            map.put(xored, map.getOrDefault(xored,0) + 1);
        }
        System.out.println(res);
    }
}
