package com.leetcode.hard.tree;

import java.util.*;

public class FindAncestors {
    int depth[];
    int D;
    List<Integer>[] adj;
    int[][] parent;
	int N;
    public int walk(int i, int k) {
        for (int d = 0; d <= D && i != -1; d++) {
            if (((1 << d) & k) > 0){
                i = parent[d][i];
            }
        }
        return i;
    }


    public void buildTree(int[][] edges, int N) {
        this.N = N;
        this.D = Integer.numberOfTrailingZeros(Integer.highestOneBit(N));
        parent = new int[D + 1][N];
        for (int d = 0; d <= D; d++) {
            Arrays.fill(parent[d], -1);
        }
        this.depth = new int[N];
        adj = new List[N];
        for(int i = 0; i < N; i++) {
        	adj[i] = new ArrayList<>();
		}
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
    }
    private int lca(int a, int b) {
    	if(depth[a] > depth[b]) {
    		a = walk(a, depth[a] - depth[b]);
		} if (depth[b] > depth[a]) {
    		b = walk(b, depth[b] - depth[a]);
		}
    	if(a == b) {
    		return b;
		}
    	for(int d = D; d>= 0; d--) {
    		if(parent[d][a] != parent[d][b]) {
    			a = parent[d][a];
    			b = parent[d][b];
			}
		}
    	return parent[0][a];
	}

    private void bfs() {
        boolean[] seen = new boolean[N];
		Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        seen[0] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : adj[i]) {
                if (!seen[j]) {
                    parent[0][j] = i;
                    depth[j] = depth[i] + 1;
                    seen[j] = true;
                    q.offer(j);
                }
            }
        }
    }

    private Map<Integer, List<Integer>> findAncestors() {
		bfs();
		binaryLifting();
        Map<Integer, List<Integer>> res = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int k = 1;
            List<Integer> list = new ArrayList<>();
            while (true) {
                int par = walk(i, k++);
                if (par == -1) {
					break;
				}
				list.add(par);
            }
            res.put(i, list);
        }
        return res;
    }

    private void binaryLifting() {
        for (int k = 1; k <= D; k++) {
            for (int i = 0; i < N; i++) {
                int mid = parent[k - 1][i];
                if (mid != -1) {
                    parent[k][i] = parent[k - 1][mid];
                }
            }
        }
    }

    public static void main(String[] args) {
    	FindAncestors fa = new FindAncestors();
    	int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {3, 8}, {4, 9}, {4, 10}, {4, 11}, {6, 12}};
    	int N = 13;
    	fa.buildTree(edges, 13);
		Map<Integer, List<Integer>> res = fa.findAncestors();
		for(int key : res.keySet()) {
			System.out.print(key + " -> ");
			for(Integer integer : res.get(key)) {
				System.out.print(integer + " ");
			}
			System.out.println();
		}

		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				System.out.println("LCA of " + i + " & " + j + " is " + fa.lca(i, j));
			}
		}
	}
}
