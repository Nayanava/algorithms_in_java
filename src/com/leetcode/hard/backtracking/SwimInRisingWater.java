package com.leetcode.hard.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SwimInRisingWater {
        int row[] = {0, 0, -1, 1};
        int col[] = {-1, 1, 0, 0};
        public int swimInWater(int[][] grid) {
            if(grid.length == 0) {
                return 0;
            }
            int m = grid.length, n = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            Set<Integer> seen = new HashSet<>();
            q.offer(new int[]{0, 0, grid[0][0]});

            int max = grid[0][0];
            seen.add(0);
            while(!q.isEmpty()) {
                int[] curr = q.poll();
                System.out.println(curr[0] + " " + curr[1]);
                max = Math.max(max, curr[2]);
                if(curr[0] == m-1 && curr[1] == n-1) {
                    break;
                }
                int min = 0;
                int nextRow = -1, nextCol = -1;
                for(int i = 0; i < 4; i++) {
                    int nr = curr[0] + row[i];
                    int nc = curr[1] + col[i];
                    if(isSafe(m, n, nr, nc) && seen.add(nr*n + nc)) {
                        if(min > grid[nr][nc]) {
                            min = grid[nr][nc];
                            nextRow = nr; nextCol = nc;
                        }
                    }
                }
                //seen.add(nextRow * n + nextCol);
                q.offer(new int[]{nextRow, nextCol, min});
            }
            return max;
        }
        private boolean isSafe(int m, int n, int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }

    public static void main(String[] args) {
        int arr[][] = {{0,2},{1,3}};
        SwimInRisingWater srw = new SwimInRisingWater();
        System.out.println(srw.swimInWater(arr));
    }
}
