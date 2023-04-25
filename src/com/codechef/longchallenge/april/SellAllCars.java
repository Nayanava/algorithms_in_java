package com.codechef.longchallenge.april;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SellAllCars {
    static int mod = 1_000_000_007;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int res = 0;
            PriorityQueue<Integer> q = new PriorityQueue(n);
            for(int i = 0; i < n; i++) {
                q.offer(s.nextInt());
            }
            while(!q.isEmpty()) {
                int curr = q.poll() - (--n);
                if(curr < 0) {
                    curr = 0;
                }
                res = (res + curr) % mod;
            }
            System.out.println(res);
        }
    }
}
