package com.leetcode.hard.slidingwindow;

import java.util.HashMap;
import java.util.Map;

class SubMatricesSumToTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
      int m = matrix.length, n = matrix[0].length;
      int[][] preSum = new int[m+1][n];
      for(int j = 0; j < n; j++) {
        for(int i = 0; i < m; i++) {
          preSum[i+1][j] = preSum[i][j] + matrix[i][j];
        }
      }

      int res = 0;
      Map<Integer, Integer> map = new HashMap<>();
      for(int rowStart = 0; rowStart < m; rowStart++) {
        map.clear();
        map.put(0, 1);
        int sum = 0;
        for(int row = rowStart; row < m; row++) {
          for(int col = 0; col < n; col++) {
            sum += (preSum[row+1][col] - preSum[rowStart][col]);
            res += map.getOrDefault(sum-target, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
          }
        }
      }
      return res;
    }
}
