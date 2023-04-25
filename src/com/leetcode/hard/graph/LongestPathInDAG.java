package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LongestPathInDAG {
    public int longestPathInDAG(int N, int[][] connections, int[] weight) {
        List<Integer>[] adj = new List[N];
        int indegree[] = new int[N];
        for(int i = 0; i < N; i++) {
        	adj[i] = new ArrayList<>();
		}
        for (int[] conn : connections) {
            int u = conn[0];
            int v = conn[1];
            adj[u].add(v);
            indegree[v]++;
        }
        boolean[] visited = new boolean[N];
        Stack<Integer> st = new Stack<>();
        List<Integer> zeroDegree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
			if (indegree[i] == 0) {
				zeroDegree.add(i);
			}
		}
		for (int i : zeroDegree) {
			topSort(adj, i, visited, st);
		}

        int[] dist = new int[N];
        dist[st.peek()] = weight[st.peek()];
        while (!st.isEmpty()) {
            int u = st.pop();
            for (int v : adj[u]) {
                if (dist[v] < dist[u] + weight[v]) {
                    dist[v] = dist[u] + weight[v];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, dist[i]);
        }
        return res;
    }

    public void topSort(List<Integer>[] adj, int start, boolean[] visited, Stack<Integer> st) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        for (int next : adj[start]) {
            topSort(adj, next, visited, st);
        }
        st.push(start);
    }

    public static void main(String[] args) {
    	int weight[] = {2, 3, 4, 5};
    	int[][] conn = {{1, 0}, {2, 0}, {3, 2}, {3, 1}};

    	LongestPathInDAG lpd = new LongestPathInDAG();
    	System.out.println(lpd.longestPathInDAG(4, conn, weight));
	}
}
