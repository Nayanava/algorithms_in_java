package com.leetcode.hard.contest;

import java.util.*;

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        Set[] roadSet = new Set[n];
        for(int i = 0; i < n; i++) {
            roadSet[i] = new HashSet<>();
        }
        for(int road[] : roads) {
            roadSet[road[0]].add(road[1]);
            roadSet[road[1]].add(road[0]);
        }

        int res = 0;
        for(int i = 0; i < n-1; i++) {
            for(int j = i+1; j < n; j++) {
                int rank = roadSet[i].size()+roadSet[j].size();
                if(roadSet[i].contains(j)) {
                    rank -= 1;
                }
                res = Math.max(res, rank);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int[][] arr = {{0,1},{0,3},{1,2},{1,3}};
        String a = "abda", b = "acmc";
        System.out.println(sol.maximalNetworkRank(n, arr));
    }
}