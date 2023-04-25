package com.leetcode.hard.graph.paths;

import java.util.*;

public class DijkstraUsingBucket {
    public int[] dijkstraUsingBucket(int[][] edges, int n, int src) {
        List<Node>[] adjList = new List[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            adjList[edge[0]].add(new Node(edge[1], edge[2]));
        }
        TreeMap<Integer, Set<Integer>> treeMap = new TreeMap<>();
        treeMap.putIfAbsent(0, new LinkedHashSet<>());
        treeMap.get(0).add(src);
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        boolean settled[] = new boolean[n];
        while(!treeMap.isEmpty()) {
            int key = treeMap.firstKey();
            int node = treeMap.get(key).iterator().next();
            treeMap.get(key).remove(node);
            if(treeMap.get(key).isEmpty())
                treeMap.remove(key);
            if(settled[node]) {
                continue;
            }
            settled[node] = true;
            for(Node adj : adjList[node]) {
                if(dist[adj.y] > dist[node] + adj.weight) {
                    //1. remove the node from the treeMap;
                    if(dist[adj.y] != Integer.MAX_VALUE) {
                        treeMap.get(dist[adj.y]).remove(adj.y);
                        if (treeMap.get(dist[adj.y]).isEmpty())
                            treeMap.remove(dist[adj.y]);
                    }
                    dist[adj.y] = dist[node] + adj.weight;
                    treeMap.putIfAbsent(dist[adj.y], new LinkedHashSet<>());
                    treeMap.get(dist[adj.y]).add(adj.y);
                }
            }
            //since there will be no more nodes with the same value
        }
        return dist;
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
        int edges[][] = {{0, 1, 9},
                {0, 2, 6},
                {0, 3, 5},
                {0, 4, 3},
                {2, 1, 2},
                {2, 3, 4}};
        DijkstraUsingBucket dub = new DijkstraUsingBucket();
        Arrays.stream(dub.dijkstraUsingBucket( edges, 5, 0)).forEach(val -> System.out.print(val + " "));
    }

}
