package com.leetcode.hard.backtracking;

import java.util.HashSet;
import java.util.Set;

public class MinimumKnightsMove {
    private static final int row[] = {2,2,-2,-2,-1,-1,1,1};
    private static final int col[] = {1,-1,1,-1,2,-2,2,-2};
    int n = 300;
    public int minKnightMoves(int x, int y) {
        Set<String> startSet = new HashSet<>();
        String startLocation = new String(0 + "_" + 0);
        startSet.add(startLocation);
        x = Math.abs(x);
        y = Math.abs(y);
        if(x == 0 && y == 0) return 0;
        String endLocation = x + "_" + y;
        Set<String> seen = new HashSet<>();
        return bfs(startSet, endLocation, seen, 0);
    }

    private boolean isSafe(int i, int j) {
        return i >= 0 && j >= 0 && (i + j <= n);
    }
    private int bfs(Set<String> set, String endLocation, Set<String> seen, int level) {

        Set<String> temp = new HashSet<>();
        for(String str : set) {
            int[] latLong = location(str);
            for(int k = 0; k < row.length; k++) {
                int currRow = Math.abs(latLong[0] + row[k]);
                int currCol = Math.abs(latLong[1] + col[k]);

                String loc = currRow + "_" + currCol;
                if(loc.equals(endLocation)) {
                    return level + 1;
                }
                if(!seen.contains(loc)  && isSafe(currRow, currCol)) {
                    temp.add(loc);
                    seen.add(loc);
                }
            }
        }

        return bfs(temp, endLocation, seen, level + 1);
    }
    private int[] location(String str) {
        String[] loc = str.split("_");
        return new int[]{Integer.valueOf(loc[0]), Integer.valueOf(loc[1])};
    }

    public static void main(String[] args) {
        int x = 1, y = 1;
        MinimumKnightsMove mkm = new MinimumKnightsMove();
        System.out.print(mkm.minKnightMoves(x, y));
    }
}
