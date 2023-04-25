package com.leetcode.hard.tree;

import java.util.*;

public class DeleteNodesOfTree {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        Map<Integer, Set<Integer>> relationMap = new HashMap<>();
        int max = 0;
        Map<Integer, int[]> map = new HashMap<>();
        int n = parent.length;
        for (int i = 0; i < n; i++) {
            relationMap.putIfAbsent(parent[i], new HashSet<>());
            relationMap.get(parent[i]).add(i);
            max = Math.max(max, parent[i]);
        }

        for (int i = 0; i < n; i++) {
            if (value[i] == 0 && !relationMap.containsKey(i)) {
                relationMap.get(parent[i]).remove(i);
                nodes--;
            }
        }
        while (max >= 0) {
            if (!relationMap.containsKey(max)) {
                max--;
                continue;
            }
            int lastKey = max;
            max -= 1;
            int sum = value[lastKey];
            int nodeCount = 1;
            for (int k : relationMap.get(lastKey)) {
                if (map.containsKey(k)) {
                    sum += map.get(k)[0];
                    nodeCount += map.get(k)[1];
                } else {
                    sum += value[k];
                    nodeCount += 1;
                }
            }
            if (sum == 0) {
                nodes -= nodeCount;
                int parentKey = parent[lastKey];
                //treeMap.get(parentKey).remove(lastKey);
            }
            map.put(lastKey, new int[]{sum, sum == 0 ? 0 : nodeCount});
            relationMap.remove(lastKey);
        }

        return nodes;
    }

    public static void main(String[] args) {
        int nodes = 10;
        int parent[] = {-1,0,1,0,2,4,3,3,7,2};
        int value[] = {638,-966,-742,-361,-690,-399,-568,38,412,171};

        DeleteNodesOfTree dnt = new DeleteNodesOfTree();
        System.out.print(dnt.deleteTreeNodes(nodes, parent, value));
    }
}
