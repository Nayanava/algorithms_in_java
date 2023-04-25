package com.leetcode.medium;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PathsInMazeThatLeadToSame {
    boolean[] visited;
    List<Integer> adj[];
    public int numberOfPaths(int n, int[][] corridors) {
        n += 1;
        visited = new boolean[n];
        adj = new List[n];
        for(int i = 1; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for(int[] edge : corridors) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int result = 0;
        for(int i = 1; i < n; i++) {
            result += numberOfPaths(i, new HashSet<>(), -1);
        }
        return result;
    }

    private int numberOfPaths(int index, Set<Integer> seen, int parent) {
        if(seen.contains(index)) {
            return seen.size() == 3 ? 1 : 0;
        }
        if(index < parent) {
            return 0;
        }
        seen.add(index);
        visited[index] = true;
        int result = 0;
        for(int next : adj[index]) {
            if(next != parent)
                result += numberOfPaths(next, seen, index);
        }
        seen.remove(index);
        return result;
    }

    public static void main(String[] args) {
        PathsInMazeThatLeadToSame obj = new PathsInMazeThatLeadToSame();
        System.out.println(obj.numberOfPaths(5, new int[][]{{1,2},{5,2},{4,1},{2,4},{3,1},{3,4}}));
    }
}
