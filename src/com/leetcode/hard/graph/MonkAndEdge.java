package com.leetcode.hard.graph;

import java.util.*;

public class MonkAndEdge {
    /* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes

*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int subTreeSize[] = new int[n];
        int parent[] = new int[n];

        int Q = s.nextInt();
        List<Integer>[] adj = new List[n];
        List<int[]> edges = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n - 1; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            adj[u-1].add(v-1);
            adj[v-1].add(u-1);
            edges.add(new int[]{u-1, v-1});
        }
        dfs(adj, 0, subTreeSize, new boolean[n]);
        while (Q-- > 0) {
            int edgeNo = s.nextInt();
            int u = edges.get(edgeNo - 1)[0];
            int v = edges.get(edgeNo - 1)[1];
            int A = Math.max(subTreeSize[u], subTreeSize[v]);
            System.out.println((long)((n - A) * A));
        }
    }

    private static void bfs(List<Integer>[] adj, int n, int[] subTreeSize, int[] parent) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        parent[0] = 0;
        boolean visited[] = new boolean[n];
        visited[0] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if(visited[v])
                    continue;
                parent[v] = u;
                q.offer(v);
                visited[v] = true;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = i;
            do {
                subTreeSize[temp] += 1;
                temp = parent[temp];
            } while (temp != parent[temp]);
        }
    }

    private static int dfs(List<Integer>[] adj, int i, int[] subTreeSize, boolean[] visited) {
        if(visited[i] || i == adj.length)
            return 0;
        visited[i] = true;

        int count = 0;
        for(int node : adj[i]) {
            count += dfs(adj, node, subTreeSize, visited);
        }
        subTreeSize[i] = count;
        return count;
    }
}
