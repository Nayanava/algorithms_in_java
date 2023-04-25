package com.codeforces.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CutThemAll {
    static int n;
    static List<Integer>[] adj;
    static int[][] edges;
    static int[] parent;
    static int[] count;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        adj = new List[n];
        edges = new int[n-1][2];
        count = new int[n];
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int u = s.nextInt()-1, v = s.nextInt()-1;
            adj[u].add(v);
            adj[v].add(u);
            edges[i] = new int[]{u, v};
        }
        dfs(0, -1);
        int res = 0;
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int par = u, child = v;
            if(parent[u] == v) {
                par = v;
                child = u;
            }
            if(count[child] % 2 == 0 && (n-count[child]) % 2 == 0) {
                res++;
            }
        }
        System.out.println(res != 0? res : n % 2 == 0 ? 0 : -1);
    }
    private static void dfs(int index, int p) {
        count[index] = 1;
        for(int next : adj[index]) {
            if(next != p) {
                parent[next] = index;
                dfs(next, index);
                count[index] += count[next];
            }
        }
    }
}
