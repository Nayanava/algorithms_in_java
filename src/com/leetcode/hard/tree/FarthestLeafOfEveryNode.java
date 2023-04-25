package com.leetcode.hard.tree;

import java.util.ArrayList;
import java.util.List;

public class FarthestLeafOfEveryNode {
    int in[][], out[][];
    int N;

    public int[][] findFarthestLeaf(int[][] edges, int n) {
        this.N = n;
		in = new int[N][2];
		out = new int[N][2];
        List<Integer>[] adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        postorderDfs(adj, 0, -1);
        preorderDfs(adj, 0, -1);
        int[] res[] = new int[N][2];
        for (int i = 0; i < N; i++) {
            if(in[i][0] > out[i][0]) {
                res[i] = in[i];
            } else {
                res[i] = out[i];
            }
        }
        return res;
    }

    private void postorderDfs(List<Integer>[] adj, int index, int parent) {
        int[] max = new int[]{0, index};
        for (int next : adj[index]) {
            if (next == parent) {
                continue;
            }
			postorderDfs(adj, next, index);
            if(max[0] < in[next][0]+1) {
                max[0] = in[next][0]+1;
                max[1] = in[next][1];
            }
        }
        in[index] = max;
    }

    private void preorderDfs(List<Integer>[] adj, int index, int parent) {
        Integer[] max = {-2, null}, secondMax = {-2, null};
        for (int next : adj[index]) {
            if (next == parent) {
                continue;
            }
            if (in[next][0] > max[0]) {
                secondMax = max;
                max = new Integer[]{in[next][0], in[next][1]};
            } else if (in[next][0] > secondMax[0]) {
                secondMax = new Integer[]{in[next][0], in[next][1]};
            }
        }
        for (int next : adj[index]) {
        	if(next == parent) {
                continue;
            }
        	Integer[] use = in[next][0] == max[0] ? secondMax : max;
        	if(use[0] + 2 > out[index][0] + 1) {
        	    out[next] = new int[]{use[0]+2, use[1]};
            } else {
        	    out[next] = new int[]{out[index][0]+1, out[index][1]};
            }
            preorderDfs(adj, next, index);
        }
    }

	public static void main(String[] args) {
		int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {1, 6}, {2, 7}, {3, 10}, {7, 8}, {7, 9}, {10, 11}, {10, 12}, {10, 13}, {13, 14}, {13, 15}};
		FarthestLeafOfEveryNode obj = new FarthestLeafOfEveryNode();
		int[][] res = obj.findFarthestLeaf(connections, 16);
		for(int i = 0; i < res.length; i++) {
			System.out.println( i + " = " + res[i][0] + " leaf = " + res[i][1]);
		}
		System.out.println("******************************************");
		connections = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        res = obj.findFarthestLeaf(connections, 6);
        for(int i = 0; i < res.length; i++) {
            System.out.println( i + " = " + res[i][0] + " leaf = " + res[i][1]);
        }
	}
}
