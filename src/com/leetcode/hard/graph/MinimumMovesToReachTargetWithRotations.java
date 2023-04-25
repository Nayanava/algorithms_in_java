package com.leetcode.hard.graph;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumMovesToReachTargetWithRotations {
    int n;
    public int minimumMoves(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        n = grid.length;
        if(grid[n-1][n-2] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        boolean[][][] visited = new boolean[n][n][2];
        q.offer(new int[]{0, 1, 0});
        int steps = 0;
        visited[0][0][0] = visited[0][1][0] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            steps++;
            while(size-- > 0) {
                int[] curr = q.poll();
                //let's consider right as 0 and down as 1.

                //if the snake moved down from right then the direction doesn't change
                //direction only changes when the snake is rotating..
                //cool.......
                if(canGoRight(curr, visited, grid)) {
                    if(curr[0] == n-1 && curr[1]+1 == n-1 && curr[2] == 0) {
                        return steps;
                    }
                    visited[curr[0]][curr[1] + 1][curr[2]] = true;
                    q.offer(new int[]{curr[0], curr[1]+1, curr[2]});
                }
                if(canGoDown(curr, visited, grid)) {
                    if(curr[0]+1 == n-1 && curr[1] == n-1 && curr[2] == 0) {
                        return steps;
                    }
                    visited[curr[0]+1][curr[1]][curr[2]] = true;
                    q.offer(new int[]{curr[0]+1, curr[1], curr[2]});
                }
                int r = (curr[2] == 0) ? 1 : -1;
                int c = (curr[2] == 0) ? -1 : 1;

                if(canRotate(grid, curr, r, c, visited)) {
                    if(curr[0] + r == n-1 && curr[1] + c == n-1 && (curr[2]^1) == 0) {
                        return steps;
                    }
                    visited[curr[0]+r][curr[1]+c][curr[2]^1] = true;
                    q.offer(new int[]{curr[0]+r, curr[1]+c, curr[2]^1});
                }
            }
        }
        return -1;
    }

    private boolean canGoRight(int[] curr, boolean visited[][][], int grid[][]) {
        if(curr[1] + 1 >= n) {
            return false;
        }
        if(curr[2] == 0) {
            return !visited[curr[0]][curr[1] + 1][curr[2]] && grid[curr[0]][curr[1] + 1] == 0;
        }
        return !visited[curr[0]-1][curr[1]+1][curr[2]] && !visited[curr[0]][curr[1]+1][curr[2]] && grid[curr[0]-1][curr[1]+1] == 0 && grid[curr[0]][curr[1]+1] == 0;
    }
    private boolean canGoDown(int[] curr, boolean[][][] visited, int grid[][]) {
        if(curr[0] + 1 >= n) {
            return false;
        }
        if(curr[2] == 1) {
            return !visited[curr[0]+1][curr[1]][curr[2]] && grid[curr[0]+1][curr[1]] == 0;
        }
        return !visited[curr[0]+1][curr[1]-1][curr[2]] && !visited[curr[0]+1][curr[1]][curr[2]] && grid[curr[0]+1][curr[1]-1] == 0 && grid[curr[0]+1][curr[1]] == 0;
    }

    private boolean canRotate(int[][] grid, int[] curr, int r, int c, boolean[][][] visited) {
        int i = curr[0], j = curr[1];
        if(i+r < 0 || i+r >= n || j+c < 0 || j+c >= n || visited[i+r][j+c][curr[2]^1] || grid[i+r][j+c] == 1) {
            return false;
        }
        if(r == -1 && c == 1) {
            return  grid[i][j+c] == 0;
        }
        else if(r == 1 && c == -1) {
            return grid[i+r][j] == 0;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,0,0,0,0,1},
                {1,1,0,0,1,0},
                {0,0,0,0,1,1},
                {0,0,1,0,1,0},
                {0,1,1,0,0,0},
                {0,1,1,0,0,0}};
        MinimumMovesToReachTargetWithRotations obj = new MinimumMovesToReachTargetWithRotations();
        System.out.println(obj.minimumMoves(matrix));
    }
}
