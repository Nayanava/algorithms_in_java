package com.leetcode.hard.backtracking;

import java.util.Arrays;

class FindMaxWords {
    int m, n;
    private static final int[] row = {1, -1, 0, 0};
    private static final int[] col = {0, 0, -1, 1};

    public int countMaxWordsInDict(char[][] mat, String[] words) {
        m = mat.length;
        n = mat[0].length;

        boolean[][] visited = new boolean[m][n];
        return backtrack(words, 0, visited, mat);
    }

    int backtrack(String[] words, int index, boolean[][] visited, char[][] mat) {
        if (index == words.length) {
            return 0;
        }

        int max = 0;
        for (int i = index; i < words.length; i++) {
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (visited[r][c] || mat[r][c] != words[i].charAt(0)) {
                        continue;
                    }
                    boolean[][] snapVisited = new boolean[m][n];
                    for (int x = 0; x < m; x++) {
                        snapVisited[x] = Arrays.copyOf(visited[x], n);
                    }
                    visited[r][c] = true;
                    if (dfs(words[i], 1, mat, visited, r, c)) {
                        max = Math.max(max, backtrack(words, i + 1, visited, mat) + 1);
                        visited = snapVisited;
                    }
                }
            }
        }
        return max;
    }

    boolean dfs(String word, int index, char[][] mat, boolean[][] visited, int r, int c) {
        if (word.length() == index) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int newR = r + row[i];
            int newC = c + col[i];

            if (isSafe(newR, newC, visited, mat, word.charAt(index))) {
                visited[r][c] = true;
                if (dfs(word, index + 1, mat, visited, newR, newC)) {
                    return true;
                }
                visited[r][c] = false;
            }
        }
        return false;
    }

    private boolean isSafe(int r, int c, boolean[][] visited, char[][] mat, char letter) {
        return r >= 0 && r < m && c >= 0 && c < n && !visited[r][c] && mat[r][c] == letter;
    }

    public static void main(String[] args) {
    	String[] dict = {"eat", "rain", "in", "rat"};
    	char[][] mat = {{'e','a', 'n'},
				{'t','t','i'},
				{'a','r','a'}};

    	FindMaxWords fmw = new FindMaxWords();
    	System.out.print(fmw.countMaxWordsInDict(mat, dict));
	}
}