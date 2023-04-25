package com.leetcode.hard.graph;

import java.lang.reflect.Array;
import java.util.*;

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> levelOfNode = new HashMap<>();
        for(List<Integer> list : seqs) {
            for(int i = 0; i < list.size()-1; i++) {
                map.putIfAbsent(list.get(i), new HashSet<>());
                map.get(list.get(i)).add(list.get(i+1));
            }
        }
        if(map.isEmpty() && org.length == 1)
            return true;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for(Integer i : map.keySet()){
            if(!visited.contains(i))
                if(!topologicalSort(map, i, stack, visited, seen))
                    return false;
        }
        int size = stack.size();
        int outdegree[] = new int[size];
        int index = size-1;
        int maxLevel = 0;
        while(!stack.isEmpty()) {
            outdegree[index--] = stack.pop();
        }

        for(int i = 0; i < size; i++) {
            Set<Integer> set = map.getOrDefault(outdegree[i], null);
            if(set == null) {
                levelOfNode.put(outdegree[i], 1);
                continue;
            }
            int seenLevel = 0;
            for(int integer : set) {
                seenLevel = Math.max(seenLevel, levelOfNode.getOrDefault(integer, -1) + 1);
            }
            levelOfNode.put(outdegree[i], seenLevel);
            maxLevel = Math.max(seenLevel, maxLevel);
        }
        System.out.println(maxLevel);
        if(maxLevel < size)
            return false;
        for(int i = 0; i < size; i++) {
            if(org[i] != outdegree[size-1-i])
                return false;
        }

        return true;
    }

    public boolean topologicalSort(Map<Integer, Set<Integer>> map, int i, Stack<Integer> stack, Set<Integer> visited, Set<Integer> seen) {
        if(seen.contains(i))
            return false;
        if(visited.contains(i))
            return true;
        visited.add(i);
        seen.add(i);
        if(!map.containsKey(i)) {
            stack.push(i);
            seen.remove(i);
            return true;
        }
        for(int j : map.get(i)) {
            if(!topologicalSort(map, j, stack, visited, seen))
                return false;
        }
        stack.push(i);
        seen.remove(i);
        return true;
    }

    public static void main(String[] args) {
        int[] org = {1};
        List<List<Integer>> seq = new ArrayList<>();
        seq.add(Arrays.asList(new Integer[]{1}));
        seq.add(Arrays.asList(new Integer[]{1}));
        seq.add(Arrays.asList(new Integer[]{1}));
        SequenceReconstruction sr = new SequenceReconstruction();
        System.out.print(sr.sequenceReconstruction(org, seq));
    }
}
