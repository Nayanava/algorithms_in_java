package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BreadSliceAssignment {
    int[] res;
    boolean[] visited;
    public int[] breadAssignment(int n, int[][] connections, int host) {
        List<Integer>[] adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] conn : connections) {
            adj[conn[0]].add(conn[1]);
            adj[conn[1]].add(conn[0]);
        }

        res = new int[n];
        visited = new boolean[n];
        if(!assignBread(adj, host, 0, n-1)) {
            return new int[0];
        }
        return res;
    }

    public boolean assignBread(List<Integer>[] adj, int start, int low, int high) {
        if(low > high) {
            return true;
        }

        res[start] = high;
        visited[start] = true;
        for(int next : adj[start]) {
            if(visited[next])
                continue;
            int mid = ( high - low + 1) >>> 1;
            int div = 1 << (adj[next].size()-1);
            if(div > high+low+1) {
                return false;
            }
            if(div < mid) {
                assignBread(adj, next, high - div, high - 1);
                high = high-div-1;
            }
            else {
                assignBread(adj, next, low, low+div-1);
                low = div;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[][] connections = {{0, 1}, {2, 3}, {1, 6}, {1, 2}, {0, 4}, {4, 5}, {0, 7}};
        int n = 8;
        BreadSliceAssignment bsa = new BreadSliceAssignment();
        Arrays.stream(bsa.breadAssignment(n, connections, 0)).forEach(num -> System.out.print(num + " "));
        System.out.println();
        connections = new int[][]{{0, 2}, {2, 1}, {0, 3}};
        n = 4;
        Arrays.stream(bsa.breadAssignment(n, connections, 0)).forEach(num -> System.out.print(num + " "));
    }
}
