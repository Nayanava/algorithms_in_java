package com.leetcode.hard.utils;

public class Graph {

    public Node[] nedges;
    public int[] degree;
    public boolean directed;
    public int nvertices;
    public int[] indegree;

    public Graph(int nvertices, boolean directed) {
        this.nvertices = nvertices;
        this.directed = directed;
        this.degree = new int[nvertices];
        this.indegree = new int[nvertices];
        this.nedges = new Node[nvertices];
        for( int i = 0; i < nvertices; i++) {
            degree[i] = 0;
            nedges[i] = null;
        }
    }

    public void addEdge(int x, int y, int weight, boolean directed) {
        Node node = new Node(x, y, weight);
        node.next = this.nedges[x];
        this.nedges[x] = node;

        if(!directed) {
            addEdge(y, x, weight, true);
        }
        this.degree[x]++;
        this.indegree[y]++;

    }
    public void addEdge(int x, int y, boolean directed) {
        addEdge(x, y, 0, directed);
    }
}
