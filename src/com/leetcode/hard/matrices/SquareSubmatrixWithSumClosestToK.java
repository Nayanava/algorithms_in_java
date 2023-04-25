package com.leetcode.hard.matrices;

public class SquareSubmatrixWithSumClosestToK {

    public int findClosest(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m+1][n+1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];
            }
        }

        int len = Math.min(m, n);
        int closest = (int)1e9+7;
        for(int x = 1; x <= len; x++) {
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    if(i < x || j < x)
                        continue;
                    int val = sum[i][j] - sum[i-x][j] - sum[i][j-x] + sum[i-x][j-x];
                    if(Math.abs(K - closest) > Math.abs(K-val)) {
                        closest = val;
                    }
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        int mat[][] = {{2, 1, 3, 4},
                {-1, 2, 4, 2},
                {6, 3, -10, 2}};

        int k = 9;
        SquareSubmatrixWithSumClosestToK obj = new SquareSubmatrixWithSumClosestToK();
        System.out.print(obj.findClosest(mat,k));
    }
}
