package com.leetcode.hard.graph.paths;

import java.util.*;
//Intuition::
// We implement kind of the same algorithm as Dijkstra's
// If we have implemented Dijkstra's using a TreeMap
//then the undestanding of this algorithm becomes very easy.
// All we have to do here is replace the treeMap which takes logn to perform a search with a bucket array of size maximum weight * number of vertices.
//which performs better when the range of the bucket is less
public class DialsAlgorithm {
    private int[] dial(int[][] edges, int n, int src, int maxWeight) {
        int size = maxWeight * n;
        Queue<Integer>[] bucket = new LinkedList[size];
        List<Node>[] adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < size; i++) {
            bucket[i] = new LinkedList<>();
        }
        for(int[] edge : edges) {
            adj[edge[0]].add(new Node(edge[1], edge[2]));
            adj[edge[1]].add(new Node(edge[0], edge[2]));
        }

        int dist[] = new int[n];
        boolean[] settled = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        bucket[0].offer(src);
        int nextBucket = 0;
        while(nextBucket != -1){
            nextBucket = smallestBucket(bucket, nextBucket);
            if(nextBucket == -1)
                break;
            while(!bucket[nextBucket].isEmpty()) {
                int first = bucket[nextBucket].poll();
                if (settled[first]) {
                    continue;
                }
                settled[first] = true;
                for (Node next : adj[first]) {
                    if (dist[next.y] > dist[first] + next.weight) {
                        dist[next.y] = dist[first] + next.weight;
                        bucket[dist[next.y]].offer(next.y);
                    }
                }
            }
        }
        return dist;
    }
    int smallestBucket(Queue<Integer>[] bucket, int nextBucket) {
        for(int i = nextBucket; i < bucket.length; i++) {
            if(!bucket[i].isEmpty())
                return i;
        }
        return -1;
    }
    class Node {
        int y;
        int weight;
        Node(int y, int weight) {
            this.y = y;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 4},
                {0, 7, 8},
                {1, 2, 8},
                {1, 7, 11},
                {2, 3, 7},
                {2, 8, 2},
                {2, 5, 4},
                {3, 4, 9},
                {3, 5, 14},
                {4, 5, 10},
                {5, 6, 2},
                {6, 7, 1},
                {6, 8, 6},
                {7, 8, 7}};
        int K = 14;
        int N = 9;
        int src = 0;
        DialsAlgorithm da = new DialsAlgorithm();
        Arrays.stream(da.dial(edges, N, src, K)).forEach(val -> System.out.print(val + " "));
    }
}

