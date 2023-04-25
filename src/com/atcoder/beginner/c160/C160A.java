package com.atcoder.beginner.c160;

import java.util.Scanner;

public class C160A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        System.out.println(str.charAt(2) == str.charAt(3) && str.charAt(4) == str.charAt(5) ? "Yes" : "No");
    }
}
