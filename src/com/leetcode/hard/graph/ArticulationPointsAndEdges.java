package com.leetcode.hard.graph;/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

import java.util.*;

class ArticulationPointsAndEdges {
    static int[] tin,low;
    static int timer = 0;
    static boolean[] visited;
    static List<int[]> res = new ArrayList<>();
    static List<Integer> ap = new ArrayList<>();
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner

        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        tin = new int[n];
        low = new int[n];
        visited = new boolean[n];
        timer = 0;
        List<Integer>[] adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int m = s.nextInt();
        for(int i = 0; i < m; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(adj, i, -1);
            }
        }
        System.out.println(ap.size());
        Collections.sort(ap);
        for(int node : ap) {
            System.out.println(node);
        }
        System.out.println(res.size());
        Collections.sort(res, (a, b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        for(int[] edge : res) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }

    private static void dfs(List<Integer>[] adj, int start, int parent) {
        int children = 0;
        low[start] = tin[start] = timer++;
        visited[start] = true;
        for(int next : adj[start]) {
            if(next == parent) {
                continue;
            }
            if(visited[next]) {
                low[start] = Math.min(low[start], tin[next]);
            } else {
                children += 1;
                dfs(adj, next, start);
                low[start] = Math.min(low[start], low[next]);
                if(low[next] > tin[start]) {
                    res.add(new int[]{start, next});
                }
                if(low[start] >= tin[start] && parent != -1) {
                    ap.add(start);
                }
            }
        }
        if(children >= 2 && parent == -1) {
            ap.add(start);
        }
    }
}
