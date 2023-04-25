package com.leetcode.medium;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinMaxPair {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        PriorityQueue<Integer> pq = new PriorityQueue();
        int max = 0;
        for(int index = 0; index + 1 < nums.length; index ++) {
            pq.offer(nums[index+1] - nums[index]);
        }
        while(!pq.isEmpty() && --p >= 0) {
            max = Math.max(max, pq.poll());
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new FindMinMaxPair().minimizeMax(new int[]{4,2,1,2}, 1));
    }
}
