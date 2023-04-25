package com.atcoder.beginner.c162;

import java.util.Scanner;

public class B162 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long total = 0;
        for(int i = 1; i <= n; i++) {
            if( i % 3 != 0 && i % 5 != 0) {
                total += (long)i;
            }
        }
        System.out.println(total);
    }
}

/*
request -> //type
1. Swtich case
2. Visitor pattern;
cfass CreationOder {
//parameters.
//OnjectMapper -> serialize
}
Base Class
//parameters
//Base Class extend -> JsonSubTypes
 */