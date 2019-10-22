package com.leetcode.hard.graph;

import com.leetcode.hard.utils.Graph;
import com.leetcode.hard.utils.Node;

import java.util.*;

public class BFS {

    public void BFSVisit(Graph g, int start, boolean visited[]) {

        if(g == null) {
            return;
        }

        Node node = g.nedges[start];

        if(node == null){
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node.x);
        visited[node.x] = true;
        while(!queue.isEmpty()) {
            int x = ((LinkedList<Integer>) queue).pop();
            node = g.nedges[x];
            System.out.println(node.x);
            while(node != null) {
                if (!visited[node.y]) {
                    visited[node.y] = true;
                    queue.add(node.y);
                }
                node = node.next;
            }
        }
    }


    public static void main(String[] arg) {
        Graph g = new Graph(4, true);
        g.addEdge(0, 1, true);
        g.addEdge(0, 2, true);
        g.addEdge(1, 2, true);
        g.addEdge(2, 0, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 3, true);

        BFS bfs = new BFS();
        boolean[] visited = new boolean[4];
        bfs.BFSVisit(g, 2, visited);
    }

}
