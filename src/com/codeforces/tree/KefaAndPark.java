package com.codeforces.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KefaAndPark {
    static int n, m, res;
    static List<Integer>[] adj;
    static int arr[];
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        arr = new int[n+1];
        adj = new List[n+1];
        for(int i = 1; i <= n; i++) {
            arr[i] = s.nextInt();
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
        dfs(1, -1, 0);
        System.out.println(res);
    }
    private static void dfs(int index, int parent, int count) {
        int children = 0;
        count = (arr[index] == 1) ? count + 1 : 0;
        if(count > m) {
            return;
        }
        for(int next : adj[index]) {
            if(parent != next) {
                dfs(next, index, count);
                children++;
            }
        }
        if(children == 0) {
            res++;
        }
    }
}
