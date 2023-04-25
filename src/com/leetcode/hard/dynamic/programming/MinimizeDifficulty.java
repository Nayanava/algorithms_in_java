package com.leetcode.hard.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class MinimizeDifficulty {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        Map<String, Integer> map = new HashMap<>();
        int res = recur(jobDifficulty, n, d, 0, map);
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    private int recur(int[] jd, int n, int d, int maxInSchedule, Map<String, Integer> map) {
        if(n == 0 && d == 0) {
            return 0;
        }
        if(n == 0 || d == 0) {
            return Integer.MAX_VALUE;
        }
        String key = n + "_" + d + "_" + maxInSchedule;
        if(map.containsKey(key)) {
            return map.get(key);
        }

        int maxInThis = recur(jd, n-1, d, Math.max(maxInSchedule, jd[n-1]), map);
        int maxNextThis = recur(jd, n-1, d-1, jd[n-1], map);
        if(maxNextThis != Integer.MAX_VALUE) {
            maxNextThis += maxInSchedule;
        }
        map.put(key, Math.min(maxInThis, maxNextThis));
        return map.get(key);
    }


    public static void main(String[] args) {
        int arr[] = {186,398,479,206,885,423,805,112,925,656,16,932,740,292,671,360};
        int D = 4;
        MinimizeDifficulty md = new MinimizeDifficulty();
        System.out.println(md.minDifficulty(arr, D));
    }
}
