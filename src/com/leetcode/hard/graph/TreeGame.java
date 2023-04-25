package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeGame {
    public int findMaximum(int[][] edges, int N) {
        List<Integer>[] adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }
        int[] parent = new int[N + 1];
        int[] subTrees = new int[N + 1];
        Arrays.fill(subTrees, 1);
        boolean[] visited = new boolean[N + 1];
        populateSubTree(adj, 1, parent, subTrees);
        int start = 1;
        int res = 0;
        for(int i = 1; i <= N; i++) {
			res = Math.max(res,  findMax(adj, start, parent, subTrees, visited, subTrees[start]));
		}
        return res;
    }

    private int populateSubTree(List<Integer>[] adj, int start, int parent[], int[] subTrees) {
        for (int next : adj[start]) {
            if (parent[next] != 0) {
                continue;
            }
            subTrees[start] += populateSubTree(adj, next, parent, subTrees);
        }
        return subTrees[start];
    }

    private int findMax(List<Integer>[] adj, int start, int parent[], int[] subTrees,boolean[] visited,  int size) {
        int maxResult = size;
        for (int i = 0; i < adj[start].size(); i++) {
            int next = adj[start].get(i);
            if(visited[next]) {
            	continue;
			}
            visited[next] = true;
            int leftSubTreeSize = Math.min(subTrees[next], subTrees[start]);
            int left = findMax(adj, next, parent, subTrees, visited, leftSubTreeSize);
            int right = findMax(adj, start, parent, subTrees, visited, size - leftSubTreeSize);
            maxResult = Math.max(maxResult, left * right);
            visited[next] = false;
        }
        return maxResult;
    }

    public static void main(String[] args) {
    	int N = 8;
    	int[][] edges = {{1, 2},
				{1, 3},
				{2, 4},
				{2, 5},
				{3, 6},
				{3, 7},
				{6, 8}};
    	TreeGame tg = new TreeGame();
    	System.out.println(tg.findMaximum(edges, N));
	}
}
