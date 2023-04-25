package com.leetcode.hard.subarray;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestSubarrayAtLeastK {
    public int shortestSubarray(int[] arr, int K) {
        int n = arr.length;
        int min = n+1, sum = 0;
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, -1});
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            while(!q.isEmpty() && sum - q.peekFirst()[0] >= K) {
                min = Math.min(min, i-q.pollFirst()[1]);
            }
            while(!q.isEmpty() && q.peekLast()[0] >= sum) {
                q.pollLast();
            }
            q.addLast(new int[]{sum, i});
            //System.out.println(sum + " " + i);
        }
        return min == n+1 ? -1 : min;
    }

    public static void main(String[] args) {
        int arr[] = {2,-1,2};
        int K = 3;
        ShortestSubarrayAtLeastK sslk = new ShortestSubarrayAtLeastK();
        System.out.println(sslk.shortestSubarray(arr, K));
    }

}
