package com.leetcode.hard.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VertexCover {
    int dp[][];
    int N;
    public int vertexCover(int[][] connections) {
        N = connections.length + 1;
        dp = new int[2][N+1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        List<Integer>[] adj = new List[N];
        for(int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] conn : connections) {
            int u = conn[0];
            int v = conn[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        return Math.min(vertexCover(adj, 0, 0, -1), vertexCover(adj, 0, 1, -1));
    }
    public int vertexCover(List<Integer>[] adj, int vertex, int guard, int parent) {
        if(adj[vertex].size() == 0) {
            return guard;
        }
        if(dp[guard][vertex] != -1) {
            return dp[guard][vertex];
        }
        int sum = 0;
        for(int next : adj[vertex]) {
            if(next == parent) {
                continue;
            }
            if(guard == 0) {
                sum += vertexCover(adj, next, 1, vertex);
            } else {
                sum += Math.min(vertexCover(adj, next, 0, vertex),  vertexCover(adj, next, 1, vertex));
            }
        }
        return dp[guard][vertex] = sum + guard;
    }

    public static void main(String[] args) {
        int[][] connections = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};
        VertexCover cover = new VertexCover();
        System.out.println(cover.vertexCover(connections));
    }
}