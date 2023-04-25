package com.leetcode.hard.graph.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Dijkstra {
    public int[] dijkstra(int n, int[][] edges, int src) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] settled = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (settled[node[0]]) {
                continue;
            }
			settled[src] = true;
            for (int[] next : adj[node[0]]) {
                if (dist[next[0]] > dist[node[0]] + next[1]) {
                    dist[next[0]] = dist[node[0]] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int edges[][] = {{0, 1, 9},
                {0, 2, 6},
                {0, 3, 5},
                {0, 4, 3},
                {2, 1, 2},
                {2, 3, 4}};
        Dijkstra d = new Dijkstra();
        Arrays.stream(d.dijkstra(5, edges, 0)).forEach(val -> System.out.print(val + " "));
    }
}


