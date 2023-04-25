package com.codeforces.sortings;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Queue303 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int totalTimeServed = 0, res = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        while(n-- > 0) {
            q.offer(s.nextInt());
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            if(curr >= totalTimeServed) {
                res++;
                totalTimeServed += curr;
            }
        }
        System.out.println(res);
    }
}
