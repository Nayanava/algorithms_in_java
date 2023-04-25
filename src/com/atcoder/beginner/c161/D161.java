package com.atcoder.beginner.c161;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class D161 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        if(N <= 12) {
            System.out.println(N);
            return;
        }
        Queue<String> q = new LinkedList<>();
        int level = 0;
        for(int i = 1; i <= 9; i++) {
            q.offer(String.valueOf(i));
        }
        N-=9;
        while(true) {
            int size = q.size();
            while(size-- > 0) {
                String curr = q.poll();
                for (char i = (char)(curr.charAt(level)-1); i <= curr.charAt(level)+1;i++) {
                    if(i < '0' || i > '9') {
                        continue;
                    }
                    N--;
                    if(N == 0) {
                        System.out.println(curr + i);
                        return;
                    }
                    q.offer(curr+i);
                }
            }
            level++;
        }
    }
}
