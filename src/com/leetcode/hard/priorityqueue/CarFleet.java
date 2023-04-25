package com.leetcode.hard.priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        for(int i = 0; i < position.length; i++) {
            q.offer(new int[]{position[i], speed[i]});
        }
        int res = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            List<int[]> list = new ArrayList<>();
            int[] prev = null;
            while(size-- > 0) {
                int[] curr = q.poll();
                if(prev == null && curr[0] + curr[1] > target) {
                    res++;
                    continue;
                }
                if( prev != null && curr[0] + curr[1] >= prev[0]) {
                    prev[1] = Math.min(prev[1], curr[1]);
                } else {
                    curr[0] += curr[1];
                    list.add(curr);
                    prev = curr;
                }
            }
            q.addAll(list);
        }
        return res;
    }

    public static void main(String[] args) {
        int target = 21;
        int[] position = {1,15,6,8,18,14,16,2,19,17,3,20,5};
        int[] speed = {8,5,5,7,10,10,7,9,3,4,4,10,2};

        CarFleet cf = new CarFleet();
        System.out.print(cf.carFleet(target, position, speed));
    }
}