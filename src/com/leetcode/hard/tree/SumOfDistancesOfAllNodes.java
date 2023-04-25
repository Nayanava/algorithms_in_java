package com.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfDistancesOfAllNodes {
        List<Integer>[] adj;
        int[] dist;
        int[] count;
        int N;
        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            this.N = N;
            adj = new List[N];
            dist = new int[N];
            count = new int[N];

            for(int i = 0; i < N; i++) {
                adj[i] = new ArrayList<>();
            }
            for(int[] edge : edges) {
                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);
            }
            postorderDfs(0, -1);
            preorderDfs(0, -1);
            return dist;
        }

        private void postorderDfs(int index, int parent) {
            count[index] = 1;
            for(int next : adj[index]) {
                if(next == parent) {
                    continue;
                }
                postorderDfs(next, index);
                count[index] += count[next];
                dist[index] += dist[next]+count[next];
            }
        }
        private void preorderDfs(int index, int parent) {
            for(int next : adj[index]) {
                if(parent == next) {
                    continue;
                }
                dist[next] += dist[index] - count[next] + N - count[next];
            }
        }
    public static void main(String[] args) {
        int N = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};

        SumOfDistancesOfAllNodes sdan = new SumOfDistancesOfAllNodes();
        Arrays.stream(sdan.sumOfDistancesInTree(N, edges)).forEach(num -> System.out.print(num + " "));
    }
}
