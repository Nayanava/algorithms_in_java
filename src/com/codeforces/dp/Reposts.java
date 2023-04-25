package com.codeforces.dp;

import java.util.*;

public class Reposts {
    static List<Integer>[] adj;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt() + 1;
        s.nextLine();
        adj = new List[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("polycarp", 0);
        for(int i = 0; i < n-1; i++) {
            String str = s.nextLine();
            String[] split = str.split(" ");
            int u = map.get(split[2].toLowerCase());
            map.put(split[0].toLowerCase(), ++max);
            int v = map.get(split[0].toLowerCase());
            adj[u].add(v);
        }
        System.out.println(postorderDfs(0));
    }
    private static int postorderDfs(int start) {
        int max = 1;
        for(int next : adj[start]) {
            max = Math.max(max, postorderDfs(next)+1);
        }
        return max;
    }
}
