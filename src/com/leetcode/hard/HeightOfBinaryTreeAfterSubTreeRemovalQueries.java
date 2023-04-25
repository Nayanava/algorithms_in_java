package com.leetcode.hard;
/**
 * @author nayanava
 */

import com.leetcode.hard.utils.TreeNode;
import com.sun.source.tree.Tree;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HeightOfBinaryTreeAfterSubTreeRemovalQueries {
    int[] height = new int[100001];
    int[] depth = new int[100001];
    Map<Integer, int[]> cousinsMap = new HashMap<>();
    public int[] treeQueries(TreeNode root, int[] queries) {
        Arrays.fill(height, -1);
        Arrays.fill(depth, -1);
        int[] result = new int[queries.length];
        height(root, 0);
        populateCousins();
        for(int i = 0; i < queries.length; i++) {
            int[] cousins = cousinsMap.get(depth[queries[i]]);
            if(cousins[1] == 0) {
                result[i] = depth[queries[i]] - 1;
            } else {
                int ht = queries[i] == cousins[0] ? height[cousins[1]] : height[cousins[0]];
                result[i] = depth[queries[i]] + ht;
            }
        }
        return result;
    }

    private int height(TreeNode root, int level) {
        if(null == root) {
            return 0;
        }
        depth[root.val] = level;
        int lHeight = height(root.left, level + 1);
        int rHeight = height(root.right, level + 1);
        height[root.val] = Math.max(lHeight, rHeight) + 1;
        return height[root.val];
    }

    private void populateCousins() {
        for(int i = 1; i < depth.length && depth[i] != -1; i++) {
            cousinsMap.putIfAbsent(depth[i], new int[2]);
            int[] cousins = cousinsMap.get(depth[i]);
            if(height[i] > height[cousins[0]]) {
                cousins[1] = cousins[0];
                cousins[0] = i;
            } else if(height[i] > height[cousins[1]]) {
                cousins[1] = i;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.buildTree(new Integer[]{1,3,4,2,null,6,5,null,null,null,null,null,7});
        HeightOfBinaryTreeAfterSubTreeRemovalQueries obj = new HeightOfBinaryTreeAfterSubTreeRemovalQueries();
        obj.treeQueries(node, new int[] {4});
    }
}
