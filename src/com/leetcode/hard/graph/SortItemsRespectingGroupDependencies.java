package com.leetcode.hard.graph;

import java.lang.reflect.Array;
import java.util.*;

class SortItemsRespectingGroupDependencies {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> grps = new HashMap<>();
        Map<Integer, Integer> grpParent = new HashMap<>();
        for(int i = 0; i < group.length; i++) {
            grps.computeIfAbsent(group[i], x->new ArrayList<>()).add(i);
            grpParent.put(i, group[i]);
        }
        for(List<Integer> grp : grps.values()) {
            if(!sortIntraGroup(grp, beforeItems, grpParent)) {
                return new int[0];
            }
        }
        List<Integer> grpSort = new ArrayList<>(grps.keySet());
        if(!sortInterGroup(grpParent, beforeItems, grpSort)) {
            return new int[0];
        }
        int index = 0;
        int res[] = new int[group.length];
        for(int key : grpSort) {
            for(int val : grps.get(key)) {
                res[index++] = val;
            }
        }
        return res;
    }

    private boolean sortIntraGroup(List<Integer> grp, List<List<Integer>> beforeItems, Map<Integer, Integer> parent) {
        Map<Integer, Set<Integer>> adj = new HashMap();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int idx : grp) {
            for(int dep : beforeItems.get(idx)) {
                if(parent.get(idx) != parent.get(dep)) {
                    continue;
                }
                if(adj.computeIfAbsent(dep, x-> new HashSet<>()).add(idx))
                    indegree.put(idx, indegree.getOrDefault(idx, 0)+1);
            }
        }
        return topSort(adj, indegree, grp);
    }

    private boolean sortInterGroup(Map<Integer, Integer> parent, List<List<Integer>> beforeItems, List<Integer> grpSort) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i = 0; i < beforeItems.size(); i++) {
            for(int dep : beforeItems.get(i)) {
                if(parent.get(i) == parent.get(dep)) {
                    continue;
                }
                if(adj.computeIfAbsent(parent.get(dep), x-> new HashSet<>()).add(parent.get(i)))
                    indegree.put(parent.get(i), indegree.getOrDefault(parent.get(i), 0) + 1);
            }
        }
        return topSort(adj, indegree, grpSort);
    }

    private boolean topSort(Map<Integer, Set<Integer>> adj, Map<Integer, Integer> indegree, List<Integer> grp) {

        int index = 0, count = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int idx : grp) {
            if(!indegree.containsKey(idx) || indegree.get(idx) == 0) {
                q.offer(idx);
                count++;
            }
        }
        if(q.isEmpty()) {
            return false;
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            grp.set(index++, curr);
            for(int next : adj.get(curr)) {
                if(next == 346 || next == 413) {
                    System.out.println(next + " " + indegree.get(next));
                }
                indegree.put(next, indegree.get(next)-1);
                if(indegree.get(next) == 0) {
                    count++;
                    q.offer(next);
                }
            }
        }
        return count == grp.size();
    }

    public static void main(String[] args) {
        int n = 8, m = 2;
        int[] grp = new int[]{-1,-1,1,0,0,1,0,-1};
        List<List<Integer>> beforeItems = new ArrayList<>();
        beforeItems.add(new ArrayList<>());
        beforeItems.add(Arrays.asList(6));
        beforeItems.add(Arrays.asList(5));
        beforeItems.add(Arrays.asList(6));
        beforeItems.add(Arrays.asList(3, 6));
        beforeItems.add(Arrays.asList());
        beforeItems.add(Arrays.asList());
        beforeItems.add(Arrays.asList());

        SortItemsRespectingGroupDependencies sirgd = new SortItemsRespectingGroupDependencies();
        Arrays.stream(sirgd.sortItems(n, m, grp, beforeItems)).forEach(val -> System.out.print(val + " "));
    }
}