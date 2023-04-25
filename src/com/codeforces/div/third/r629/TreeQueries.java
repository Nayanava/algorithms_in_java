package com.codeforces.div.third.r629;

import java.io.*;
import java.util.*;

public class TreeQueries {
    static int[] parent;
    static int[] in, outTime;
    static int N, count;
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        in = new int[N+1];
        outTime = new int[N+1];
        adj = new List[N+1];
        parent[1] = 1;
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        dfs(1, 1);
        while (M-- > 0) {
            st = new StringTokenizer(f.readLine());
            int k = Integer.parseInt(st.nextToken());
            boolean found = true;
            int root = parent[Integer.parseInt(st.nextToken())];
            for (int i = 1; i < k; i++) {
                int next = parent[Integer.parseInt(st.nextToken())];
                if(samePath(root, next)) {
                    root = next;
                } else if(!samePath(next, root)) {
                    System.out.println("NO");
                    found = false;
                    break;
                }
            }
            if(found) {
                System.out.println("YES");
            }
        }
    }
    private static boolean samePath(int a, int b) {
        return in[a] <= in[b] && outTime[b] <= outTime[a];
    }

    static void dfs(int index, int p) {
        in[index] = ++count;
        for (int next : adj[index]) {
            if (next != p) {
                parent[next] = index;
                dfs(next, index);
            }
        }
        outTime[index] = ++count;
    }
}
