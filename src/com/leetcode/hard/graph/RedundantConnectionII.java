package com.leetcode.hard.graph;

import java.util.HashMap;
import java.util.Map;

class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> visited = new HashMap<>();
        DSU dsu = new DSU();
        for (int[] edge : edges) {
            if (visited.containsKey(edge[1])) {
                return edge;
            } else if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
            visited.put(edge[0], edge[1]);
        }
        return new int[0];
    }

    class DSU {
        Map<Integer, Integer> parent;

        DSU() {
            parent = new HashMap<>();
        }

        public boolean union(int a, int b) {
            int pA = find(a);
            int pB = find(b);
            if (pA == pB) {
                return false;
            }
            parent.put(pA, pB);
            return true;
        }

        public int find(int a) {
            while (parent.containsKey(a)) {
                a = parent.get(a);
            }
            return a;
        }
    }
}
