package com.codeforces.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StronglyConnectedCity475B {
    static int INF = (int)1e9+7;
    static Map<Character, int[]> map;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        map = new HashMap<>();
        map.put('<', new int[]{0, -1});
        map.put('>', new int[]{0, 1});
        map.put('^', new int[]{-1, 0});
        map.put('v', new int[]{1, 0});
        int n = s.nextInt(), m = s.nextInt();
        int len = n*m;
        int[][] adj = new int[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                adj[i][j] = INF;
            }
        }
        for(int i = 0; i < len; i++) {
            adj[i][i] = 0;
        }
        String lr = s.next();
        String ud = s.next();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int[] dir = map.get(lr.charAt(i));
                int nr = i + dir[0], nc = j + dir[1];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    adj[i*m + j][nr*m + nc] = 1;
                }
                dir = map.get(ud.charAt(j));
                nr = i + dir[0];
                nc = j + dir[1];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    adj[i*m + j][nr*m + nc] = 1;
                }
            }
        }

        for(int k = 0; k < len; k++) {
            for(int i = 0; i < len; i++) {
                for(int j = 0; j < len; j++) {
                    if(adj[i][j] > adj[i][k] + adj[k][j]) {
                        adj[i][j] = adj[i][k] + adj[k][j];
                    }
                }
            }
        }
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(adj[i][j] == INF) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}

/*
3 3
<><
v^v

 */