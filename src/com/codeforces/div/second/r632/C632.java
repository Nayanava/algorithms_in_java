package com.codeforces.div.second.r632;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class C632 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int start = 0;
        long sum = 0, res = 0;
        Map<Long, Integer> map = new HashMap<>();
        long presum[] = new long[n];
        for(int i = 0; i < n; i++) {
            int next = s.nextInt();
            if(next == 0) {
                start = i+1;
                sum = 0;
                continue;
            }
            sum += next;
            if(sum == 0) {
                sum -= presum[start];
                start += 1;
            }
            if(map.containsKey(sum) && map.get(sum) >= start) {
                start = map.get(sum) + 2;
                sum -= presum[start-1];
            }
            map.put(sum, i);
            presum[i] = sum;
            res += start <= i ? i-start+1 : 0;
        }
        System.out.println(res);
    }
}
