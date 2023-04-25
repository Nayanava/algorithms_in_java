package com.codeforces.div.third.r629;

import java.io.*;
import java.util.*;

public class TreeQueriesLCA {
    static List<Integer>[] adj;
    static int N, D;
    static int[][] parent;
    static int[] depth;
    public static void main(String[] args) throws Exception{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int D = Integer.numberOfTrailingZeros(Integer.highestOneBit(N));
        adj = new List[N+1];
        depth = new int[N+1];
        parent = new int[N+1][D+1];
        for(int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        for(int i = 0; i <= N; i++) {
            for(int d = 0; d <= D; d++) {
                parent[i][d] = -1;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        Set<Integer> seen = new HashSet<>();
        seen.add(1);
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int next : adj[curr]) {
                if(seen.add(next)) {
                    depth[next] = depth[curr] + 1;
                    parent[next][0] = curr;
                    q.offer(next);
                }
            }
        }

        for(int d = 1; d <= D; d++) {
            for(int i = 1; i <= N; i++) {
                int mid = parent[i][d-1];
                if(mid != -1) {
                    parent[i][d] = parent[mid][d-1];
                }
            }
        }

        while(M-- > 0) {
            int rootParentCount = 0;
            st = new StringTokenizer(f.readLine());
            int k = Integer.parseInt(st.nextToken());
            int first = -1, second = -1;
            boolean found = true;
            while(k-- > 0) {
                int node = Integer.parseInt(st.nextToken());
                if(node == 1) {
                    continue;
                } if(parent[node][0] == 1) {
                    rootParentCount++;
                    continue;
                }
                first = parent[node][0];
                break;
            }
            while(k-- > 0) {
                int node = Integer.parseInt(st.nextToken());
                if(node == 1) {
                    continue;
                } if(parent[node][0] == 1) {
                    rootParentCount++;
                    continue;
                }
                second = parent[node][0];
                break;
            }
            if( k < 0 && rootParentCount == k) {
                System.out.println("YES");
                continue;
            }
            int lca = lca(first, second);
            if(lca == 1) {
                System.out.println("NO");
                continue;
            }
            while(k -- > 0) {
                int node = Integer.parseInt(st.nextToken());
                if(node == 1) {
                    continue;
                }
                if(parent[node][0] == 1) {
                    rootParentCount++;
                    continue;
                }
                node = parent[node][0];
                lca = lca(lca, node);
                if(lca == 1) {
                    found = false;
                    System.out.println("NO");
                    break;
                }
            }
            if(found) {
                if (rootParentCount > 1) {
                    if (parent[lca][0] != -1 && parent[parent[lca][0]][0] == 1) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
                System.out.println("YES");
            }
        }
    }
    private static int walk(int i, int k) {
        for(int d = 0; d <= D && i != -1; d++) {
            if(((1 << d) & k) > 0) {
                i = parent[i][d];
            }
        }
        return i;
    }

    private static int lca(int i, int j) {
        if(depth[i] > depth[j]) {
            i = walk(i, depth[i]-depth[j]);
        }
        if(depth[j] > depth[i]) {
            j = walk(j, depth[j] - depth[i]);
        }
        if(i == j) {
            return i;
        }
        for(int d = D; d >= 0; d--) {
            if(parent[i][d] != parent[j][d]) {
                i = parent[i][d];
                j = parent[j][d];
            }
        }
        return parent[i][0];
    }

}
