package com.codeforces.div.third.r617;

import java.util.*;

public class FightWithMonsters {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), a = s.nextInt(), b = s.nextInt(), k = s.nextInt();
        List<Integer> list = new ArrayList<>();
        int total = a + b;
        int res = 0;
        for(int i = 0; i < n; i++) {
            int val = s.nextInt();
            if(val % total != 0 && (val % total) - a <= 0) {
                res++;
            } else {
                int div;
                if(val % total == 0) {
                    div = (val/total)-1;
                } else {
                    div = (val/total);
                }
                val -= total*div + a;
                int counts = val % a == 0 ? (val/a) : (val/a) + 1;
                list.add(counts);
            }
        }
        Collections.sort(list);
        for(int val : list) {
            if(k - val < 0) {
                break;
            }
            res ++;
            k -= val;
        }
        System.out.println(res);
    }
}
