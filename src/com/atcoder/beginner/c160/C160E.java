package com.atcoder.beginner.c160;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int A = s.nextInt(), B = s.nextInt(), X = s.nextInt(), Y = s.nextInt(), C = s.nextInt();
        Long a[] = new Long[X];
        Long b[] = new Long[Y];
        Long c[] = new Long[C];
        long sum = 0;
        for(int i = 0; i < X; i++) {
            a[i] = s.nextLong();
        }
        for(int i = 0; i < Y; i++) {
            b[i] = s.nextLong();
        }
        for(int i = 0; i < C; i++) {
            c[i] = s.nextLong();
        }
        Arrays.sort(a, Comparator.reverseOrder());
        Arrays.sort(b, Comparator.reverseOrder());
        Arrays.sort(c, Comparator.reverseOrder());
        PriorityQueue<Long> q = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < A; i++) {
            q.offer(a[i]);
        }
        for(int j = 0; j < B; j++) {
            q.offer(b[j]);
        }
        for(int k = 0; k < C; k++) {
            q.offer(c[k]);
        }
        int total = A+B;
        while(total-- > 0) {
            sum += q.poll();
        }
        System.out.println(sum);
    }
}
