package com.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeightOfAnNAryTree {

    int[] in, out;
    public int[] maxHeight(int N, int[][] connections) {
        List<Integer>[] adj = new List[N];
        in = new int[N];
        out = new int[N];
        for(int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] conn : connections) {
            adj[conn[0]].add(conn[1]);
            adj[conn[1]].add(conn[0]);
        }
        postOrderDfs(adj, 0, -1);
        preOrderDfs(adj, 0, -1);

        int[] ht = new int[N];
        for(int i = 0; i < N; i++) {
            ht[i] = Math.max(in[i], out[i]);
        }
        return ht;
    }

    void postOrderDfs(List<Integer>[] adj, int root, int parent) {
        for(int next : adj[root]) {
            if(parent == next) {
                continue;
            }
            postOrderDfs(adj, next, root);
            in[root] = Math.max(in[root], 1 + in[next]);
        }
    }

    void preOrderDfs(List<Integer>[] adj, int root, int parent) {
        int max = 0, second_max = 0;
        for(int next : adj[root]) {
            if(parent == next) {
                continue;
            }
            if(in[next] > max) {
                second_max = max;
                max = in[next];
            } else if(in[next] > second_max) {
                second_max = in[next];
            }
        }

        for(int next : adj[root]) {
            if(next == parent) {
                continue;
            }
            int use = (in[next] == max) ? second_max : max;
            out[next] = Math.max(1 + out[root], 2 + use);
            preOrderDfs(adj, next, root);
        }
    }

    public static void main(String[] args) {
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 7}, {3, 10}, {7, 8}, {7, 9}, {10, 11}, {10, 12}, {10, 13}, {13, 14}, {13, 15}};
        int N = 16;
        MaxHeightOfAnNAryTree mht = new MaxHeightOfAnNAryTree();
        Arrays.stream(mht.maxHeight(N, connections)).forEach(ht -> System.out.print(ht + " "));
    }
}
