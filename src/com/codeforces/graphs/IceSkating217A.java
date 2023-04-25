package com.codeforces.graphs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class IceSkating217A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        DSU dsu = new DSU();
        int[][] uv = new int[n][2];
        for(int i = 0; i < n; i++) {
            uv[i] = new int[]{s.nextInt(), s.nextInt()};
            dsu.union(uv[i][0], 1000 + uv[i][1]);
        }
        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < n; i++) {
            seen.add(dsu.find(uv[i][0]));
        }
        System.out.println(seen.size()-1);
    }

    static class DSU {
        int[] parent = new int[2001];
        DSU() {
            for(int i = 1; i <= 2000; i++) {
                parent[i] = i;
            }
        }
        public void union(int a, int b) {
            int pA = find(a);
            int pB = find(b);
            parent[pA] = pB;
        }
        public int find(int a) {
            while(a != parent[a]) {
                a = parent[a];
            }
            return a;
        }
    }
}
