package com.codechef.longchallenge.april;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class UNITGCD {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        int arr[] = new int[T];
        PrintWriter out = new PrintWriter(System.out);
        for(int index = 0;index < T; index++) {
            arr[index] = s.nextInt();
        }
        for(int index = 0; index < T; index++) {
            int N = arr[index];
            out.println(N == 1 ? 1 : N/2);
            if(N == 1) {
                out.println(1 + " " + 1);
                continue;
            }
            for(int i = 2; i <= N; i += 2) {
                if(N%2 == 1 && i == 2) {
                    out.println("3 "+ (i-1) + " " + i + " " + N);
                } else {
                    out.println("2 " + (i-1) + " " + i);
                }
            }
        }
        out.close();
    }
}
