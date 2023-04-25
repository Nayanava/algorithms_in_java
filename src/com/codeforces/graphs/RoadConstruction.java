package com.codeforces.graphs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RoadConstruction {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Set<Integer> seen = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            seen.add(i);
        }
        int m = s.nextInt();
        for(int i = 0; i < m; i++) {
            seen.remove(s.nextInt());
            seen.remove(s.nextInt());
        }
        int center = seen.iterator().next();
        System.out.println(n-1);
        for(int i = 1; i <= n; i++) {
            if( i == center) continue;
            System.out.println(center + " " + i);
        }
    }
}
