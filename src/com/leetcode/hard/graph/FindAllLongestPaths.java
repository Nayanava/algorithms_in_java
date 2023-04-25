package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllLongestPaths {
    int[] row = {0, 0, -1, 1};
    int[] col = {-1, 1, 0, 0};
    public List<List<Integer>> allPaths(int M, int N, int[][] mat) {
        boolean[][] visited = new boolean[M][N];
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(mat, M, N, 0, 0, visited, res, list);
        return res;
    }

    private void dfs(int[][] mat, int M, int N, int startI, int startJ, boolean[][] visited, List<List<Integer>> res, List<Integer> list) {
        boolean moved = false;
        visited[startI][startJ] = true;
        list.add(mat[startI][startJ]);
        for (int i = 0; i < 4; i++) {
            int currRow = row[i] + startI;
            int currCol = col[i] + startJ;
            if (isSafe(M, N, currRow, currCol, visited)) {
                moved = true;
                dfs(mat,M, N, currRow, currCol, visited, res, list);
            }
        }
        if (!moved) {
            res.add(new ArrayList<>(list));
        }
        visited[startI][startJ] = false;
        list.remove(list.size() - 1);
    }

    boolean isSafe(int M, int N, int i, int j, boolean[][] visited) {
        return i >= 0 && i < M && j >= 0 && j < N && !visited[i][j];
    }

    public static void main(String[] args) {
    	int M = 3, N = 3;
    	int[][] mat = new int[M][N];
    	for(int i = 0; i < M; i++) {
    	    for(int j = 0; j < N; j++){
    	        mat[i][j] = i * N + j;
            }
        }
    	FindAllLongestPaths falp = new FindAllLongestPaths();
    	List<List<Integer>> res = falp.allPaths(M, N, mat);
    	for(List<Integer> chs : res) {
    	    for(int c : chs) {
    	        System.out.print(c + " ");
            }
    	    System.out.println();
        }
	}
}

