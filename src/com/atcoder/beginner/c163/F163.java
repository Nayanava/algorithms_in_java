package com.atcoder.beginner.c163;

import java.util.*;

public class F163 {
    static int[] dp;
    static int INF = (int) 1e9 + 7;
    static int[] res;
    static int n;
    static List<Integer>[] adj;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        adj = new List[n+1];
        dp = new int[n+1];
        res = new int[n+1];
        int colors[] = new int[n+1];
        TreeMap<Integer, Integer> sameColors = new TreeMap<>();
        for(int i = 1; i <= n; i++) {
            colors[i] = s.nextInt();
            sameColors.put(colors[i], sameColors.getOrDefault(colors[i], 0) + 1);
        }
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        postorderdfs(1, -1);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(colors[i], map.getOrDefault(colors[i], 0) + (n-1 + dp[i]*(n-1-dp[i]) + 1));
        }
        for(int key : sameColors.keySet()) {
            System.out.println(map.get(key) - (sameColors.get(key)-1));
        }
    }
    private static int postorderdfs(int index, int parent) {
        int children = 0;
        for (int next : adj[index]) {
            if(next != parent) {
                dp[index] += postorderdfs(next, index);
                children++;
            }
        }
        dp[index] += children;
        return dp[index];
    }
}
