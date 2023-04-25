package com.leetcode.hard.contest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class EscapeTheGhosts {
    Set<String> seen = new HashSet<>();
    Set<String> deadends = new HashSet<>();
    int[] tgt;
    int[] row = {0, 0, -1, 1};
    int[] col = {-1, 1, 0, 0};
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        tgt = target;
        Queue<int[]> q = new LinkedList<>();
        for(int[] ghost : ghosts) {
            deadends.add(ghost[0] + "_" + ghost[1]);
            q.offer(new int[]{ghost[0], ghost[1], 0});
        }
        String start = "0_0";
        String end = target[0] + "_" + target[1];
        if(start.equals(end) && !deadends.contains(end)) {
            return true;
        }
        if(deadends.contains(start) || deadends.contains(end)) {
            return false;
        }
        seen.add(end); seen.add(start);
        q.offer(new int[]{0, 0, 1});
        Boolean res = null, found = false;
        while(!q.isEmpty() && !found) {
            int size = q.size();
            while(size-- > 0) {
            int[] curr = q.poll();
                for(int i = 0; i < 4; i++) {
                    int x = curr[0] + row[i];
                    int y = curr[1] + col[i];
                    if(x == target[0] && y == target[1]) {
                        if(res == null || res == true) {
                            res = curr[2] == 0 ? false : true;
                        }
                        found = true;
                    }
                    if(isSafe(x + "_" + y, x, y)) {
                        q.offer(new int[]{x, y, curr[2]});
                    }
                }
            }
        }
        return res;
    }
    
    private boolean isSafe(String point, int x, int y) {
        return  seen.add(point) && Math.abs(x) <= 10000 && Math.abs(y) <= 10000;
    }
}