package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.List;
//DFS with DP to find the maximum level of a node in a DAG
class ParallelCourses {
    public int minimumSemesters(int N, int[][] relations) {
        N = N+1;
        List<Integer>[] adj = new List[N];
        for(int i = 1; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] rel : relations) {
            adj[rel[0]].add(rel[1]);
        }
        int dp[] = new int[N];
        boolean[] seen = new boolean[N];
        int max = 0;
        for(int i = 1; i < N; i++) {
            max = Math.max(max, dfs(adj, i, seen, dp));
            if(max == Integer.MAX_VALUE) {
                return -1;
            }
        }
        return max;
    }

    private int dfs(List<Integer>[] adj, int start, boolean[] seen, int[] dp) {
        if(seen[start]) {
            return Integer.MAX_VALUE;
        }
        if(dp[start] != 0) {
            return dp[start];
        }
        seen[start] = true;
        int max = 1;
        for(int i : adj[start]) {
            int temp = dfs(adj, i, seen, dp);
            if(temp == Integer.MAX_VALUE) {
                return temp;
            }
            max = Math.max(max, temp+1);
        }
        seen[start] = false;
        return dp[start] = max;
    }

    public static void main(String[] args) {
        int[][] relations = {{1,3},{2,3}};
        ParallelCourses pc = new ParallelCourses();
        System.out.print(pc.minimumSemesters(3, relations));
    }

}