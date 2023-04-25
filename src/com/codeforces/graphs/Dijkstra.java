package com.codeforces.graphs;

import java.util.*;

public class Dijkstra {
    static long INF = (long)1e15+7;
    static List<int[]>[] adj;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        adj = new List[n+1];
        for(int i =1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int u = s.nextInt(), v = s.nextInt(), w = s.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        boolean[] settled = new boolean[n+1];
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);
        q.offer(new long[]{1,0});
        while(!q.isEmpty()) {
            long[] curr = q.poll();
            if(curr[0] == n) {
                break;
            }
            if(settled[(int)curr[0]]) {
                continue;
            }
            settled[(int)curr[0]] = true;
            for(int[] next : adj[(int)curr[0]]) {
                if(dist[next[0]] > dist[(int)curr[0]] + next[1]) {
                    dist[next[0]] = dist[(int)curr[0]] + next[1];
                    parent[next[0]] = (int)curr[0];
                    q.offer(new long[]{next[0], (int)dist[next[0]]});
                }
            }
        }
        if(dist[n] == INF) {
            System.out.println(-1);
            return;
        }
        print(parent, n);
    }
    private static void print(int[] parent, int index) {
        if(parent[index] != -1)
            print(parent, parent[index]);
        System.out.println(index);
    }
}
