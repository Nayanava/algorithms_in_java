package com.codeforces.graphs;

import java.util.Arrays;
import java.util.Scanner;

public class PollandBallAndForest {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        DSU dsu = new DSU(n);
        for(int i = 1; i <= n; i++) {
            int v = s.nextInt();
            dsu.union(i, v);
        }
        System.out.println(dsu.component);
    }

    static class DSU {
        int[] parent;
        int[] weight;
        int component;
        DSU(int n) {
            parent = new int[n+1];
            weight = new int[n+1];
            for(int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            Arrays.fill(weight, 1);
            component = n;
        }
        public void union(int a, int b) {
            int pA = find(a);
            int pB = find(b);
            if(pA == pB) {
                return;
            }
            component--;
            if(weight[pA] > weight[pB]) {
                parent[pB] = pA;
                weight[pA] += weight[pB];
            } else {
                parent[pA] = pB;
                weight[pB] += weight[pA];
            }
        }

        public int find(int a) {
            if(parent[a] != parent[parent[a]]) {
                parent[a] = parent[parent[a]];
            }
            return parent[a];
        }
    }
}
