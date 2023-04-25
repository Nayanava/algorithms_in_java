package com.codeforces.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DistanceInTree {
    static List<Integer>[] adj;
    static int[][] dp;
    static int K, N;
    static long res, absolute;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        K = s.nextInt();
        dp = new int[N+1][K+1];
        adj = new List[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < N-1; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        postorderDfs(1, -1);
        preorderDfs(1, -1);
        System.out.println(absolute + res/2);
    }

    private static void postorderDfs(int index, int parent) {
        dp[index][0] = 1;
        for(int next : adj[index]) {
            if(next == parent) {
                continue;
            }
            postorderDfs(next, index);
            for(int i = 1; i <= K; i++) {
                dp[index][i] += dp[next][i-1];
            }
        }
    }

    private static void preorderDfs(int index, int parent) {
        absolute += dp[index][K];
        for(int next : adj[index]) {
            if(next == parent) {
                continue;
            }
            for(int k = 1; k < K; k++) {
                res += (dp[next][k-1]*(dp[index][K-k] - dp[next][K-k-1]));
            }
            preorderDfs(next, index);
        }
    }
}
