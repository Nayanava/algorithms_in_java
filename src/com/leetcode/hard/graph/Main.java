package com.leetcode.hard.graph;

import java.util.*;

public class Main {
    static String[] sliceAssignment(String host, String[][]cuts){
        Map<String, List<String>> adj = new HashMap<>();
        Map<String,Integer> namePosition = new HashMap<>();
        for(String[] cut : cuts){
            for(String name : cut){
                String other = name.equals(cut[0])?cut[1]:cut[0];
                adj.computeIfAbsent(name, n -> new ArrayList<>()).add(other);
            }
        }
        String[] ans = new String[adj.size()];
        ans[adj.size()-1] = host;
        namePosition.put(host,adj.size()-1);
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(host);
        while(!queue.isEmpty()){
            String name = queue.poll();
            for(String next : adj.get(name)){
                if (namePosition.containsKey(next)){ continue; }
                int pos = namePosition.get(name)-(1<<(adj.get(next).size()-1));
                ans[pos] = next;
                namePosition.put(next,pos);
                queue.offer(next);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
//        System.out.println(
//          Arrays.toString(
//            sliceAssignment(
//                "A",
//                new String[][]{
//                    {"A","B"},
//                    {"B","C"},
//                    {"C","D"},
//                    {"B","G"},
//                    {"A","E"},
//                    {"E","F"},
//                    {"A","H"}
//                })));//D C G B F E H A
        System.out.println(
          Arrays.toString(
            sliceAssignment(
                "A",
                new String[][]{
                    {"C","B"},
                    {"A","D"},
                    {"A","C"},
                })));//B C D A
    }    
}