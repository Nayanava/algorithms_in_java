package com.leetcode.hard.matrices;

class MaxSquareSubmatrixWithSumLessEqualToK {
    int m, n;
    int res = 0;

    public int findMaxSqSubMatrix(int[][] grid, int K) {
        m = grid.length;
        n = grid[0].length;
        int sum[][] = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
            }
        }
        int low = 0, high = Math.min(m, n);
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (possible(sum, mid, K)) {
                low = mid + 1;
            } else {
                high = mid;
            }
            if (res == K) {
                break;
            }
        }
        return res;
    }

    private boolean possible(int[][] grid, int len, int K) {
        boolean found = false;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i >= len && j >= len) {
                    int temp = grid[i][j] - grid[i - len][j] - grid[i][j - len] + grid[i - len][j - len];
                    if (temp <= K) {
                        found = true;
                        res = Math.max(res, temp);
                    }
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
    	MaxSquareSubmatrixWithSumLessEqualToK mss = new MaxSquareSubmatrixWithSumLessEqualToK();
		int grid[][] = {{2, 1, 3, 4},
				{0, 2, 4, 2},
				{6, 3, 10, 2}};

		int K = 12;
    	System.out.println(mss.findMaxSqSubMatrix(grid, K));
	}
}
