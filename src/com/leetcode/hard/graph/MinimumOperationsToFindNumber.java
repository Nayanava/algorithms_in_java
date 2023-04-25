package com.leetcode.hard.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumOperationsToFindNumber {
    public int minOperations(int num) {
        int low = Integer.numberOfTrailingZeros(Integer.highestOneBit(num));
        int high = low + 1;
        if (num == Math.pow(2, low) || num == Math.pow(2, low + 1)) {
            return 1;
        }
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer((int) Math.pow(2, low));
        q.offer((int) Math.pow(2, high));
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            while (size-- > 0) {
                int curr = q.poll();
                if (curr > num) {
                    for (int i = 0; i <= low - 1; i++) {
                        if (curr - Math.pow(2, i) >= num) {
                            if (curr - Math.pow(2, i) == num) {
                                return level + 1;
                            }
                            if(seen.add(curr - (int) Math.pow(2, i)))
                                q.offer(curr - (int)Math.pow(2, i));
                        }
                    }
                } else {
                    for (int i = 0; i <= low - 1; i++) {
                        if (curr + Math.pow(2, i) <= num) {
                            if (curr + Math.pow(2, i) == num) {
                                return level + 1;
                            }
                            if(seen.add(curr + (int)Math.pow(2, i)))
                                q.offer(curr + (int)Math.pow(2, i));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    	int num = Integer.MAX_VALUE - 3500;
        MinimumOperationsToFindNumber mofn = new MinimumOperationsToFindNumber();
        System.out.println(mofn.minOperations(num));
	}
}
