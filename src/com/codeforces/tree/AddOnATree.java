package com.codeforces.tree;

import java.util.Scanner;

public class AddOnATree {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int indegree[] = new int[n+1];
        for(int i = 0; i < n-1; i++) {
            int u = s.nextInt(), v = s.nextInt();
            indegree[u]++;
            indegree[v]++;
        }
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 2) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
