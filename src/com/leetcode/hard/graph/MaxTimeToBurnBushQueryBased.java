package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.List;

public class MaxTimeToBurnBushQueryBased {

    int N;
    List<Integer>[] adj;
    int in[];
    int out[];
	int res[];
    //since its a try there will be n-1 connections.
    //which means there will be n nodes
    //nodes are considered 0-indexed
    //The root of the tree is not known.
    public MaxTimeToBurnBushQueryBased(int[][] connections) {
        N = connections.length + 1;
        adj = new List[N];
        in = new int[N];
        out = new int[N];
        res = new int[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] conn : connections) {
            int u = conn[0], v = conn[1];
            adj[u].add(v);
            adj[v].add(u);
        }
//choosing any random node as the root.
//here we start with 0.
//output is expected to the same with all nodes.
        postorderDfs(0, -1);
        preorderDfs(0, -1);
        for(int i = 0; i < N; i++) {
        	res[i] = Math.max(in[i], out[i]);
		}
    }

    //this dfs guarantees the maximum distance from the root node.
//here we had considered it as 0;
    void postorderDfs(int root, int parent) {
        int max = 0;
        for (int next : adj[root]) {
            if (next == parent) {
                continue;
            }
            postorderDfs(next, root);
            max = Math.max(in[next]+1, max);
        }
        in[root] = max;
    }

    //the farthest node could lie in one of the following
//1. subtree of the current node.
//2. path via one of its siblings.
//3. path via the parent.
    void preorderDfs(int root, int parent) {
        int max = 0, second_max = 0;
		for (int next : adj[root]) {
			if(next == parent) {
				continue;
			}
			if (in[next] > max) {
				second_max = max;
				max = in[next];
			} else if (in[next] > second_max) {
				second_max = in[next];
			}
		}
        for (int next : adj[root]) {
			if(next == parent) {
				continue;
			}
			int use = in[next] == max ? second_max : max;
			out[next] = Math.max(out[root] + 1, 2 + use);
            preorderDfs(next, root);
        }
    }

    public static void main(String[] args) {
    	int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 7}, {3, 10}, {7, 8}, {7, 9}, {10, 11}, {10, 12}, {10, 13}, {13, 14}, {13, 15}};
    	MaxTimeToBurnBushQueryBased obj = new MaxTimeToBurnBushQueryBased(connections);
    	for(int i = 0; i < obj.res.length; i++) {
    		System.out.println( i + " = " + obj.res[i]);
		}
	}
}
