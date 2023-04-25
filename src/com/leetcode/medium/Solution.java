package com.leetcode.medium;

import java.util.*;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        return subsetsWithDup(nums, 0, new HashSet<>());
    }

    private List<List<Integer>> subsetsWithDup(int[] arr, int index, Set<Integer> seen) {
        if(index == arr.length) {
            List<List<Integer>> result = new LinkedList<>();
            result.add(new LinkedList<>());
            return result;
        }

        List<List<Integer>> fromLower = subsetsWithDup(arr, index+1, seen);

        List<List<Integer>> result = new LinkedList<>();
        for(List<Integer> res : fromLower) {
            if(seen.add(arr[index]))
                result.add(new LinkedList<>(res));
            res.add(0, arr[index]);
            result.add(res);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sln = new Solution();
        sln.subsetsWithDup(new int[]{1, 2, 2});
    }
}