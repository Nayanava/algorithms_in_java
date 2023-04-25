package com.codeforces.div.third.r629;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Carousel {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            int[] arr = new int[n];
            Set<Integer> seen = new HashSet<>();
            boolean adj = false;
            for (int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
                seen.add(arr[i]);
                if((i != 0 && arr[i] == arr[i-1]) || (i == n-1 && arr[i] == arr[0])) {
                    adj = true;
                }
            }
            if (seen.size() == 1) {
                System.out.println(1);
                for (int i = 0; i < n; i++) {
                    System.out.print(1 + " ");
                }
                System.out.println();
                continue;
            }
            int res[] = new int[n];
            if(n % 2 == 0) {
                System.out.println(2);
                int prev = 1;
                for(int i = 0; i < n; i++) {
                    System.out.print((prev+1) + " ");
                    prev ^= 1;
                }
                System.out.println();
            } else if(n%2 == 1 && adj) {
                System.out.println(2);
                int prev = 1, temp = 1;
                boolean merged = false;
                for(int i = 0; i < n; i++) {
                    if(i != 0 && arr[i-1] == arr[i] && !merged) {
                        System.out.print(temp + " ");
                        merged = true;
                    } else {
                        System.out.print((prev+1) + " ");
                        temp = prev+1;
                        prev ^= 1;
                    }
                }
                System.out.println();
            } else {
                System.out.println(3);
                int prev = 1;
                for(int i = 0; i < n-1; i++) {
                    System.out.print((prev+1) + " ");
                    prev ^= 1;
                }
                System.out.println(3);
            }
        }
    }
}
