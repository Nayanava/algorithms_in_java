package com.codeforces.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Journey {
    static List<Integer>[] adj;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int u = s.nextInt() - 1, v = s.nextInt() - 1;
            adj[u].add(v);
            adj[v].add(u);
        }
        System.out.println(postOrderDfs(0, -1));
    }
    private static double postOrderDfs(int index, int parent) {
        double count = 0;
        int children = 0;
        for(int next : adj[index]) {
            if(parent != next) {
                count += postOrderDfs(next, index);
                children++;
            }
        }
        if(children == 0)
            return 0;
        count = count/children + 1;
        return count;
    }
}
