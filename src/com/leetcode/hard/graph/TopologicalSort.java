package com.leetcode.hard.graph;

import com.leetcode.hard.utils.Graph;
import com.leetcode.hard.utils.Node;

import java.util.Stack;

public class TopologicalSort {

    public static void topologicalSort(Graph g, int vertex, boolean visited[], Stack<Integer> stack) {
        if(visited[vertex])
            return;

        visited[vertex] = true;
        Node node = g.nedges[vertex];
        while(node != null) {
            if(!visited[node.y]){
                topologicalSort(g, node.y, visited, stack);
            }
            node = node.next;
        }
        stack.push(vertex);
    }

    public static void main(String[] args) {
        Graph g = new Graph(6, true);
        g.addEdge(5, 2, true);
        g.addEdge(5, 0, true);
        g.addEdge(4, 0, true);
        g.addEdge(4, 1, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 1, true);

        boolean[] visited = new boolean[6];
        Stack<Integer> stack = new Stack<>();
        for( int i = 0; i < 6; i++) {
            topologicalSort(g, i, visited, stack);
        }

        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
