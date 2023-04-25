package com.codeforces.graphs;

import java.util.*;

public class FoxAndNames510C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String[] arr = new String[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.next();
        }
        List<Integer>[] adj = new List[26];
        int[] indegree = new int[26];
        for(int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int j = 0;
            for(; j < Math.min(arr[i].length(), arr[i+1].length()); j++) {
                if(arr[i].charAt(j) != arr[i+1].charAt(j)) {
                    adj[arr[i].charAt(j) - 'a'].add(arr[i+1].charAt(j)-'a');
                    indegree[arr[i+1].charAt(j) - 'a']++;
                    break;
                }
            }
            if(j == Math.min(arr[i].length(), arr[i+1].length()) && arr[i].length() > arr[i+1].length()) {
                System.out.println("Impossible");
                return;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < 26; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }
        char[] res = new char[26];
        int count = 0, index = 0;

        while(!q.isEmpty()) {
            int curr = q.poll();
            count++;
            res[index++] = (char)(curr + 'a');
            for(int next : adj[curr]) {
                if(--indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        if(count != 26) {
            System.out.println("Impossible");
            return;
        }
        System.out.println(String.valueOf(res));
    }
}
