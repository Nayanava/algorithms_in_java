package com.atcoder.beginner.c161;

import java.util.*;

public class E161 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt(), c = s.nextInt();
        String str = s.next();
        int[] dpl = new int[n+1];
        int[] dpr = new int[n+1];
        for(int i = 1; i <= n; i++) {
            dpl[i] = dpl[i-1];
            if(str.charAt(i-1) == 'o') {
                dpl[i] = Math.max(dpl[i], dpl[Math.max(0, i-c-1)] + 1);
            }
        }
        for(int i = n-1; i >= 0; i--) {
            dpr[i] = dpr[i+1];
            if(str.charAt(i) == 'o') {
                dpr[i] = Math.max(dpr[i], dpr[Math.min(n, i+c+1)] + 1);
            }
        }
        Map<Integer, Integer> map = new HashMap();
        for(int i = 1; i <= n; i++) {
            if(str.charAt(i-1) == 'o' && dpl[i] + dpr[i-1] - 1 == k) {
                map.put(dpl[i], map.getOrDefault(dpl[i], 0) + 1);
            }
        }
        for(int i = 1; i <= n; i++) {
            if(str.charAt(i-1) == 'o' && dpl[i] + dpr[i-1] - 1 == k && map.getOrDefault(dpl[i], 0) == 1) {
                System.out.println(i);
            }
        }
    }
}
