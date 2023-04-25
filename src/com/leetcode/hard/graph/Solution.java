package com.leetcode.hard.graph;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private int row[] = {0, 0, -1, 1};
    private int col[] = {-1, 1, 0, 0};
    private Map<Integer, List<Integer>> map = new HashMap<>();
    
    private void DFSVisit(int[][] arr, int i, int j) {
        
        arr[i][j] = 0;
        map.putIfAbsent(i, new ArrayList<>());
        map.get(i).add(j);
        for(int k = 0; k < 4; k++) {
            int currRow = row[k] + i;
            int currCol = col[k] + j;
            if(isSafe(arr, currRow, currCol)) {
                DFSVisit(arr, currRow, currCol);
            }
        }
    }
    
    private int findShortestDistance(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        boolean flag = false;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] == 1) {
                    DFSVisit(arr, i, j);
                    flag = true;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        flag = false;
        int minDistance = m*n + 1;
        int i = 0, j = 0;
        for( i = 0; i < m; i++) {
            for(j = 0; j < n; j++) {
                if(arr[i][j] == 1) {
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }

        if(i == m && j == n) {
            //no second island found
            return -1;
        }
        int temp = i;

        while(!map.containsKey(temp)) {
            temp--;
        }
        List<Integer> list = map.get(temp);

        //i and temp exclusive because we found a 1 in both the rows, hence the height would the columns with connecting 0's
        int rowDist = i - temp - 1;
        for(int col = j; col < n && arr[i][col] == 1; col++) {
            int k = Collections.binarySearch(list, col);
            //the same col of the temp row has a 1 hence column distance will be 0;
            if (k > 0) {
                minDistance = Math.min(minDistance, rowDist);
            } else {
                int closestCol = 0;
                if (k < 0) {
                    k = -k - 1;
                }
                if (k == 0) {
                    closestCol = list.get(k);
                }
                else
                    closestCol = list.get(k-1);
                minDistance = Math.min(minDistance, rowDist + Math.abs(closestCol - col));
            }
        }
        return minDistance;
    }
    
    private boolean isSafe(int[][] arr, int i, int j) {
        
        if(i >= 0 && i < arr.length && j >= 0 && j < arr[0].length && arr[i][j] == 1) {
            return true;
        }
        return false;
    }
    public static void main(String args[] ) throws Exception {
        Solution sol = new Solution();
        int arr[][] = {{1,1,1,0},{0,0,0,0},{0,0,1,0}};
        System.out.println(sol.findShortestDistance(arr));
    }
}