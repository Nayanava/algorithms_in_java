package com.codeforces.graphs;

import java.util.*;

public class TheTwoRoutes {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Set<Integer>[] railAdj = new Set[n+1];
        Set<Integer>[] roadAdj = new Set[n+1];
        for(int i = 0; i <= n; i++) {
            railAdj[i] = new HashSet<>();
            roadAdj[i] = new HashSet<>();
        }
        int m = s.nextInt();
        for(int i = 1; i <= m; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            railAdj[u].add(v);
            railAdj[v].add(u);
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j && !railAdj[i].contains(j))
                    roadAdj[i].add(j);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        int railDist[] = new int[n+1];
        int roadDist[] = new int[n+1];
        railDist[1] = 0;
        roadDist[1] = 0;
        Set<Integer> seen = new HashSet<>();
        seen.add(1);
        boolean routeFound = false;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for(int next : railAdj[curr]) {
                if(next == n) {
                    routeFound = true;
                }
                if(seen.add(next)) {
                    railDist[next] = 1 + railDist[curr];
                    q.offer(next);
                }
            }
        }
        if(!routeFound) {
            System.out.println(-1);
            return;
        }
        q.offer(1);
        seen.clear();
        seen.add(1);
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int next : roadAdj[curr]) {
                if(next == n) {
                    System.out.println(Math.max(railDist[next], 1 + roadDist[curr]));
                    return;
                }
                if(railDist[next] != 1 + roadDist[curr] && seen.add(next)) {
                    roadDist[next] = 1 + roadDist[curr];
                    q.offer(next);
                }
            }
        }
        System.out.println("-1");
    }
}
