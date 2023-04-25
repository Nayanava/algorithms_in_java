package com.codeforces.div.second.r628;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            Set<Integer> seen = new HashSet<>();
            for(int i = 0; i < n; i++) {
                seen.add(s.nextInt());
            }
            System.out.println(seen.size());
        }
    }
}
