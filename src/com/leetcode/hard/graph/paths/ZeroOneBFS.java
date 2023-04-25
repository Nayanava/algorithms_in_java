package com.leetcode.hard.graph.paths;

import java.util.*;

//Intuition :: 0-1 BFS is used to find the minimum cost path of a graph where the edge of the weights are restricted to only 0 or 1
//In general the minimum cost path or the shortest path algorithm in which we use BFS basically has equal edge weight of 1.
// This algorithm is an improvisation on Dijkstra's shortest path algorithm.
// Instead of using a priority queue to pop the already settled element we use a double ended queue.
// We store 0 edge weight elements in the front and 1 edge weight elements at the rear of the double ended queue.
// This can also be implemented using the concept of 0/1 bucket to store 0 edge weight elements and 1 edge weight elements separately
// and pop vertices from lower edge weight bucket.
//Will see the extension of this in the implementation of the Dial's Algorithm
public class ZeroOneBFS {
    private int[] bfs01(int[][] edges, int n, int src) {
        boolean[] settled = new boolean[n];
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<Node>[] adjList = new List[n];
        for(int i = 0; i < n; i++) {
            adjList[i]= new ArrayList<>();
        }
        for(int[] edge : edges) {
            adjList[edge[0]].add(new Node(edge[1], edge[2]));
        }
        //PriorityQueue substitute Deque
        Deque<Integer> q = new LinkedList<>();
        q.addFirst(src);
        dist[src] = 0;
        while(!q.isEmpty()) {
            int first = q.removeFirst();
            if(settled[first])
                continue;
            settled[first] = true;
            for(Node adjNode : adjList[first]) {
                if(dist[adjNode.y] > dist[first] + adjNode.weight) {
                    dist[adjNode.y] = dist[first] + adjNode.weight;
                }
                if(adjNode.weight == 0) {
                    q.addFirst(adjNode.y);
                } else {
                    q.addLast(adjNode.y);
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        int edges[][] = {{0, 1, 1},
                {0, 2, 0},
                {0, 3, 1},
                {0, 4, 1},
                {2, 1, 0},
                {2, 3, 1}};
        ZeroOneBFS zoBfs = new ZeroOneBFS();
        Arrays.stream(zoBfs.bfs01( edges, 5, 0)).forEach(val -> System.out.print(val + " "));

    }
    class Node{
        int y;
        int weight;
        Node(int y, int weight) {
            this.y = y;
            this.weight = weight;
        }
    }
}
