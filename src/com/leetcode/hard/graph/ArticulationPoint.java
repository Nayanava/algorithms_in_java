package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.List;

public class ArticulationPoint {
    int timer = 0;
    int[] tin;
    int[] low;
    boolean[] visited;
    List<Integer> res;

    public List<Integer> articulationPoints(int[][] connections, int n) {
        tin = new int[n];
        low = new int[n];
        visited = new boolean[n];
        res = new ArrayList<>();
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] conn : connections) {
            adj[conn[0]].add(conn[1]);
            adj[conn[1]].add(conn[0]);
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, i, -1);
            }
        }
        return res;
    }

    private void dfs(List<Integer>[] adj, int start, int parent) {
        int children = 0;
        visited[start] = true;
        low[start] = tin[start] = timer++;
        for (int next : adj[start]) {
            if (next == parent) {
                continue;
            }
            if (visited[next]) {
                low[start] = Math.min(low[start], tin[next]);
            } else {
                dfs(adj, next, start);
                ++children;
                low[start] = Math.min(low[start], low[next]);
                if (parent != -1 && low[next] > tin[start]) {
                    res.add(start);
                }
            }
        }
        if (parent == -1 && children >= 2) {
            res.add(start);
        }
    }

    public static void main(String[] args) {

	}
}
