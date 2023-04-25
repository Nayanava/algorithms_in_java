package com.leetcode.hard.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class KnightDialer {
    public static final int row[] = {-2, -2, -1, -1, 1, 1, 2, 2};
    public static final int col[] = {-1, 1, -2, 2, -2, 2, -1, 1};

    public int knightDialer(int N) {
        Map<String, Integer> map = new HashMap<>();
        int m = 4, n = 3, count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(isSafe(m, n, i, j)) {
                    count += backtrack(m, n, i, j, N-1, map);
                }
            }
        }
        return count;
    }

    private int backtrack(int m, int n, int startI, int startJ, int N, Map<String, Integer> map) {
        System.out.println(startI + " " + startJ);
        if(N == 0) {
            return 1;
        }
        if(startI == m || startJ == n) {
            return 0;
        }
        String key = startI + "_" + startJ + "_" + N;

        if(map.containsKey(key))
            return map.get(key);
        int count = 0;

        for(int k = 0; k < row.length; k++) {
            int currRow = startI + row[k];
            int currCol = startJ + col[k];
            if(isSafe(m, n, currRow, currCol)) {
                count += backtrack(m, n, currRow, currCol, N-1, map);
            }
        }
        map.put(key, count);
        return count;
    }

    private boolean isSafe(int m, int n, int i, int j) {
        if( i == m-1 && (j == 0 || j == n-1))
            return false;

        return i >= 0 && j >= 0 && i < m && j < n;
    }

    public static void main(String[] args) {
        int N = 3;
        KnightDialer kd = new KnightDialer();
        System.out.print(kd.knightDialer(N));
    }
}
