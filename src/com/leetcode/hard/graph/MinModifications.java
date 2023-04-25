package com.leetcode.hard.graph;

import java.util.*;

public class MinModifications {

    private static final int[] row = {0, 0, -1, 1};
    private static final int[] col = {-1, 1, 0, 0};

    public int minModifications(char[][] mat) {
        int r = mat.length, c = mat[0].length;
        final int N = r * c;
        Map<Character, int[]> map = new HashMap<>();
        map.put('L', new int[]{0, -1});
        map.put('R', new int[]{0, 1});
        map.put('U', new int[]{-1, 0});
        map.put('D', new int[]{1, 0});
        int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] settled = new boolean[N];
        Deque<Integer> q = new LinkedList<>();
        q.addFirst(0);
        dist[0] = 0;
        while (!q.isEmpty()) {
            int exit = q.poll();
            if (settled[exit]) {
                continue;
            }
            settled[exit] = true;
            int i = exit / c, j = exit % c;
            for (int k = 0; k < 4; k++) {
                int currRow = i + row[k];
                int currCol = j + col[k];
                if (isSafe(r, c, currRow, currCol)) {
                    int[] dir = map.get(mat[i][j]);
                    int weight = (row[k] == dir[0] && col[k] == dir[1]) ? 0 : 1;
                    int next = currRow * c + currCol;
                    if (dist[next] > dist[exit] + weight) {
                        dist[next] = dist[exit] + weight;
                        if (weight == 1) {
                            q.addLast(next);
                        } else {
                            q.addFirst(next);
                        }
                    }
                }
            }
        }
        return dist[N - 1];
    }

    private boolean isSafe(int r, int c, int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

    public static void main(String[] args) {
    	char[][] mat = {{'R', 'D', 'D'},
				{'R', 'L', 'L'},
				{'R', 'L', 'D'}};
    	MinModifications mm = new MinModifications();
    	System.out.println(mm.minModifications(mat));
	}
}
