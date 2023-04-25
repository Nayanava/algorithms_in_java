package com.leetcode.hard.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class ShortestPathWithAlternatingColors {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        String[] color = new String[n];
        Queue<Node>[] adj = new Queue[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for(int i = 0; i < n; i++) {
            color[i] = new String("");
        }
        for(int[] edge : red_edges) {
            adj[edge[0]].add(new Node(edge[1], "R"));
        }
        for(int[] edge : blue_edges) {
            adj[edge[0]].add(new Node(edge[1], "B"));
        }
        color[0] = "";
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, ""));
        Node saveNext = null;
        while(!q.isEmpty()) {
            Node curr = q.poll();
            while(!adj[curr.index].isEmpty()) {
                Node next = adj[curr.index].poll();
                if (curr.color.endsWith(next.color)) {
                    saveNext = next;
                    continue;
                }
                if (color[next.index].equals("") || color[next.index].length() > curr.color.length() + next.color.length()) {
                    color[next.index] = curr.color + next.color;
                }
                q.offer(new Node(next.index, curr.color + next.color));
            }
            if(saveNext != null) {
                adj[curr.index].offer(saveNext);
                saveNext = null;
            }
        }
        int[] dist = new int[n];
        for(int i = 1; i < n; i++) {
            dist[i] = color[i].isEmpty() ? -1 : color[i].length();
        }
        return dist;
    }
    class Node {
        int index;
        String color;
        Node(int index, String color) {
            this.index = index;
            this.color = color;
        }
    }

    public static void main(String[] args) {
       int n = 5;
       int[][] red_edges = {{0,1},{1,2},{2,3},{3,4}};
       int[][] blue_edges = {{1,2},{2,3},{3,1}};
        ShortestPathWithAlternatingColors s = new ShortestPathWithAlternatingColors();
       Arrays.stream(s.shortestAlternatingPaths(n, red_edges, blue_edges)).forEach(a -> System.out.print(a + " "));
    }
}