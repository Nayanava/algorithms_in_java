package com.leetcode.hard.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class TheMazeIII {
    int row[] = {0, 0, -1, 1};
    int col[] = {-1, 1, 0, 0};
    int m, n;
    Map<String, Character> map = new HashMap<>();
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        map.put(row[0] + "_" + col[0], 'l');
        map.put(row[1] + "_" + col[1], 'r');
        map.put(row[2] + "_" + col[2], 'u');
        map.put(row[3] + "_" + col[3], 'd');

        m = maze.length;
        n = maze[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Point dist[] = new Point[m*n];
        boolean[] settled = new boolean[m*n];
        for(int i = 0; i < m*n; i++) {
            dist[i] = new Point(Integer.MAX_VALUE);
        }
        q.offer(new int[]{ball[0]*n + ball[1], 0});
        dist[ball[0]*n + ball[1]] = new Point(0);
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            if(settled[curr[0]]) {
                continue;
            }
            settled[curr[0]] = true;
            int x = curr[0] / n, y = curr[0] % n;
            for(int i = 0; i < 4; i++) {
                int nextRow = x, nextCol = y;
                int len = 0;
                while(isSafe(maze, row[i] + nextRow, col[i] + nextCol)) {
                    nextRow += row[i];
                    nextCol += col[i];
                    len++;
                    if(nextRow == hole[0] && nextCol == hole[1]) {
                        break;
                    }
                }
                if(nextRow == x && nextCol == y) {
                    continue;
                }
                int index = nextRow*n + nextCol;
                if(dist[index].dist > curr[1] + len
                        || ((dist[index].dist == curr[1] + len) && dist[index].path.compareTo(dist[curr[0]].path + map.get(row[i] + "_" + col[i])) > 0)) {
                    dist[index].dist = curr[1] + len;
                    dist[index].path = dist[curr[0]].path + map.get(row[i] + "_" + col[i]);
                    q.offer(new int[]{index, dist[index].dist});
                }
            }
        }
        int resIndex = hole[0]*n + hole[1];
        if(dist[resIndex].dist == Integer.MAX_VALUE) {
            return "impossible";
        }
        return dist[resIndex].path;
    }

    private boolean isSafe(int[][] maze, int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == 0;
    }

    class Point {
        int dist;
        String path;
        Point(int dist) {
            this.dist = dist;
            this.path = new String();
        }
    }

    public static void main(String[] args) {
        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int ball[] = {4,3};
        int hole[] = {0,1};

        TheMazeIII thm = new TheMazeIII();
        System.out.println(thm.findShortestWay(maze, ball, hole));
    }
}