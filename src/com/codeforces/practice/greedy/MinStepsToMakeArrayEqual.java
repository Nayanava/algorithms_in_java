package com.codeforces.practice.greedy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinStepsToMakeArrayEqual {

    // Complete the equal function below.
    static int equal(int[] arr) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b-a);
        int min = 10000, n = arr.length;
        for (int i = 0; i < n; i++) {
            q.offer(arr[i]);
            min = Math.min(arr[i], min);
        }
        int res = 0;
        int div[] = {5, 2, 1};
        while (!q.isEmpty()) {
            int num = q.poll();
            if (num != min) {
                for (int i = 0; i < 3; i++) {
                    while (num - div[i] >= min) {
                        num -= div[i];
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int T = scanner.nextInt();
        int n = scanner.nextInt();
        int []arr = new int[n];
        while(T-- > 0) {
            for(int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            System.out.println(equal(arr));
        }
    }
}
