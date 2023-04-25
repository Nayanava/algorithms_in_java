package com.codeforces.div.third.r627;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaximumWhiteSubtree {
    static int[] dp;
    static int[] val;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer>[] adj = new List[n];
        dp = new int[n];
        val = new int[n];
        for(int i = 0; i < n; i++) {
            int in = s.nextInt();
            in = (in == 0) ? -1 : in;
            val[i] = in;
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int u = s.nextInt()-1;
            int v = s.nextInt()-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        postorderDfs(adj, 0, -1);
        preorderDfs(adj, 0, -1);
        for(int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    private static void preorderDfs(List<Integer>[] adj, int index, int parent) {
        for(int next : adj[index]) {
            if(next == parent) {
                continue;
            }
            dp[next] += Math.max(dp[index] - Math.max(dp[next], 0), 0);
            preorderDfs(adj, next, index);
        }
    }

    private static void postorderDfs(List<Integer>[] adj, int index, int parent) {
        int max = val[index];
        for(int next : adj[index]) {
            if(next == parent) {
                continue;
            }
            postorderDfs(adj, next, index);
            max = Math.max(max, max + Math.max(dp[next], 0));
        }
        dp[index] = max;
    }
}
