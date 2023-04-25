package com.leetcode.hard.graph;

public class GraphColoring {

    private boolean dfs(int[][] graph, int v, int m, int color[]) {
        if(v == graph.length)
            return true;
        for(int c = 1; c <= m; c++) {
            if(isSafe(graph[v], c, color)) {
                color[v] = c;
                if(dfs(graph, v+1, m, color))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int[] graph, int c, int[] color) {
        for(int i = 0; i < graph.length; i++) {
            if(graph[i] == 1 && c == color[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };
        int m = 3;
        int color[] = new int[graph.length];
        GraphColoring gc = new GraphColoring();
        System.out.println(gc.dfs(graph, 0, m, color));
    }
}
