package com.codeforces.tree;

import java.util.*;

public class TimofeyAndTree {
    static List<Integer>[] adj;
    static int res = -1;
    static int[] colors;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        colors = new int[n];
        adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int u = s.nextInt()-1, v = s.nextInt()-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        for(int i = 0; i < n; i++) {
            colors[i] = s.nextInt();
        }
        postorderDfs(0, -1);
        System.out.println(res == -1 ? "NO" : "YES");
        if(res != -1)
            System.out.println(res);
    }

    private static Set<Integer> postorderDfs(int index, int parent) {
        Set<Integer> seen = new HashSet<>();
        int children = 0;
        seen.add(colors[index]);
        for(int next : adj[index]) {
            if (next != parent) {
                seen.addAll(postorderDfs(next, index));
                children++;
            }
        }
        if(seen.size() == 1 && children != 0) {
            res = index;
        }
        return seen;
    }
}
