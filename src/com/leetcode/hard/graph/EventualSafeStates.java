package com.leetcode.hard.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventualSafeStates {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            List<Integer>[] outdegree = new List[n];
            Boolean dp[] = new Boolean[n];
            boolean visited[] = new boolean[n];
            int index = 0;
            for(int i = 0; i < n; i++) {
                int count = graph[i].length;
                if(outdegree[count] == null) {
                    outdegree[count] = new ArrayList<>();
                }
                outdegree[count].add(i);
            }

            if(outdegree[0].size() == 0) {
                return new ArrayList<>();
            }
            for(int node : outdegree[0]) {
                dp[node] = true;
            }
            for(int i = 0; i < n; i++) {
                if(outdegree[i] == null || outdegree[i].size() == 0)
                    continue;
                List<Integer> nodes = outdegree[i];
                for(int node : nodes) {
                    if(!visited[node])
                        dfs(graph, node, dp, visited);
                }
            }
            List<Integer> response = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                if(dp[i] != null && dp[i]) response.add(i);
            }
            return response;
        }

        private boolean dfs(int[][] graph, int i, Boolean[] dp, boolean[] visited) {
            if(dp[i] != null) {
                return dp[i];
            }

            if(visited[i])
                return false;
            visited[i] = true;
            for(int node : graph[i]) {
                if(!dfs(graph, node, dp, visited)) {
                    dp[i] = false;
                    return false;
                }
            }
            dp[i] = true;
            return true;
        }

    public static void main(String[] args) {
        int graph[][] = {{1,2},{2,3},{5},{0},{5},{},{}};
        EventualSafeStates ess = new EventualSafeStates();
        ess.eventualSafeNodes(graph).stream().forEach(node -> System.out.print(node + " "));

    }
}
