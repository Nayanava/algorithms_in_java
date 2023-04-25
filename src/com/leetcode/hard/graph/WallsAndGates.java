package com.leetcode.hard.graph;

import java.util.LinkedList;
import java.util.Queue;

class WallsAndGates {
    int m, n;
    int[] row = {0, 0, -1, 1};
    int[] col = {1, -1, 0, 0};
    public void wallsAndGates(int[][] rooms) {
        m = rooms.length;
        n = rooms[0].length;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rooms[i][j] == 0) {
                    q.offer(i*n + j);
                }
            }
        }
        while(!q.isEmpty()) {
            int index = q.poll();
            int x = index/n, y = index%n;
            for(int i = 0; i < 4; i++) {
                int nextRow = row[i] + x;
                int nextCol = col[i] + y;
                if(isSafe(nextRow, nextCol, rooms)) {
                    if(rooms[nextRow][nextCol] == Integer.MAX_VALUE) {
                        rooms[nextRow][nextCol] = rooms[x][y] + 1;
                        q.offer(nextRow * n + nextCol);
                    }
                }
            }
        }
    }
    
    private boolean isSafe(int i, int j, int[][] rooms) {
        return i >= 0 && i < m && j >= 0 && j < n && rooms[i][j] != -1;
    }

    public static void main(String[] args) {
        int[][] arr = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        WallsAndGates wng = new WallsAndGates();
        wng.wallsAndGates(arr);
        for(int i = 0; i < arr.length; i++) {
            for(int val : arr[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}