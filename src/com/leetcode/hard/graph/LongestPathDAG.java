package com.leetcode.hard.graph;

import com.leetcode.hard.utils.Graph;
import com.leetcode.hard.utils.Node;
import com.leetcode.hard.utils.Utils;

import java.util.Arrays;
import java.util.Stack;

public class LongestPathDAG {

    private static void longestPathInDAG(Graph g) {

        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[g.nvertices];

        TopologicalSort.topologicalSort(g, 1, visited, stack);

        Integer[] dist = new Integer[g.nvertices];
        Utils.memset(dist, Integer.MIN_VALUE);
        dist[1] = 0;
        while(!stack.empty()) {
            int x = stack.pop();
            Node node = g.nedges[x];
            while (null != node) {
                int y = node.y;
                if(dist[y] < dist[x] + node.weight) {
                    dist[y] = dist[x] + node.weight;
                }

                node = node.next;
            }
        }

        System.out.println("Maximum path length " + Utils.max(dist));
        Arrays.stream(dist).forEach(pathLength -> System.out.print(pathLength + " "));
    }

    public static void main(String[] args) {
        Graph g = new Graph(6, true);
        g.addEdge(0, 1, 5, false);
        g.addEdge(0, 2, 3, false);
        g.addEdge(1, 3, 6, false);
        g.addEdge(1, 2, 2, false);
        g.addEdge(2, 4, 4, false);
        g.addEdge(2, 5, 2, false);
        g.addEdge(2, 3, 7, false);
        g.addEdge(3, 5, 1, false);
        g.addEdge(3, 4, -1, false);
        g.addEdge(4, 5, -2, false);

        LongestPathDAG.longestPathInDAG(g);
    }
}
