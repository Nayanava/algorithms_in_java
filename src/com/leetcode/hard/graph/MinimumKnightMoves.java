package com.leetcode.hard.graph;

import java.util.HashSet;
import java.util.Set;

public class MinimumKnightMoves {
    int[] rows = {-2, -2, -1, 1, 2, 2, 1, -1};
    int[] cols = {1, -1, 2, 2, 1, -1, -2, -2};
    Set<String> seen = new HashSet<>();
    int count = 0;
    public int minKnightMoves(int x, int y) {
        Set<String> src = new HashSet<>();
        Set<String> dest = new HashSet<>();
        String start = 0 + "_" + 0;
        String end = x + "_" + y;
        if(start.equals(end)) return 0;
        src.add(start);
        dest.add(end);
        seen.add(start);
        seen.add(end);
        bfs(src, dest);
        return count;
    }

    private void bfs(Set<String> src, Set<String> dest) {
        if(src.isEmpty()) {
            count = -1;
            return;
        }
        if(src.size() > dest.size()) {
            bfs(dest, src);
            return;
        }
        boolean found = false;
        Set<String> temp = new HashSet<>();
        for(String index : src) {
            String[] split = index.split("_");
            int point[] = {Integer.valueOf(split[0]), Integer.valueOf(split[1])};
            for(int x = 0; x < 8; x++) {
                int nextX = point[0] + rows[x];
                int nextY = point[1] + cols[x];
                String nPoint = nextX + "_" + nextY;
                if(dest.contains(nPoint)) {
                    found = true;
                    break;
                }
                if(isSafe(nPoint, nextX, nextY)) {
                    temp.add(nPoint);
                }
            }
        }
        ++count;
        if(!found) {
            bfs(temp, dest);
        }
    }
    boolean isSafe(String nPoint, int x, int y) {
        return seen.add(nPoint) && Math.abs(x) + Math.abs(y) <= 300;
    }

    public static void main(String[] args) {
        int x = 5, y = 5;
        MinimumKnightMoves mkm = new MinimumKnightMoves();
        System.out.println(mkm.minKnightMoves(x, y));
    }
}
