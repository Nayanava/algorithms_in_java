package com.codeforces.graphs;

import java.util.*;

public class TheChildAndToy {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        int arr[] = new int[n+1];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)-> b[1] - a[1]);
        for(int i = 1; i <= n; i++) {
            arr[i] = s.nextInt();
            q.offer(new int[] {i, arr[i]});
        }
        List<Integer>[] adj = new List[n+1];
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 1; i <= m; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        int res = 0;
        Set<Integer> seen = new HashSet<>();
        while(!q.isEmpty()) {
            int[] next = q.poll();
            seen.add(next[0]);
            for(int index : adj[next[0]]) {
                if(!seen.contains(index)) {
                    res += arr[index];
                }
            }
        }
        System.out.println(res);
    }
}
