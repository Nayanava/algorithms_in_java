package com.atcoder.beginner.c161;

import java.util.Scanner;

public class A161 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int A = s.nextInt(), B = s.nextInt(), C = s.nextInt();
        int temp = A;
        A = B; B = temp;
        temp = A; A = C; C = temp;
        System.out.println(A + " " + B + " " + C);
    }
}
