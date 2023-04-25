package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LCABinaryLifting {
    int N, D;
    int[][] parent;
    int[] depth;
    Map<Integer, TreeNode> map = new HashMap<>();
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        populateTree(root);
        populateAllParents();
        return map.get(LCA(p, q));
    }

    private int countNodes(TreeNode root) {
        if(null == root) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    private void populateTree(TreeNode root) {
        N = countNodes(root);
        D = Integer.numberOfTrailingZeros(Integer.highestOneBit(N));
        parent = new int[D+1][N];
        for(int i = 0; i <= D; i++) {
            Arrays.fill(parent[i], -1);
        }
        depth = new int[N];
        dfs(root);
    }

    private void populateAllParents() {
        for(int d = 1; d <= D; d++) {
            for(int i = 0; i < N; i++) {
                int mid = parent[d-1][i];
                if(mid != -1) {
                    parent[d][i] = parent[d-1][mid];
                }
            }
        }
    }

    private int walk(int i, int k) {
        for(int d = D; d >= 0 && i != -1; d--) {
            if(((1 << d) & k) > 0) {
                i = parent[d][i];
            }
        }
        return i;
    }

    private int LCA(int i, int j) {
        if(depth[i] < depth[j]) {
            i = walk(i, depth[j] - depth[i]);
        }
        if(depth[j] < depth[i]) {
            j = walk(j, depth[i] - depth[j]);
        }
        if(i == j) {
            return i;
        }
        for(int d = D; d >= 0; d--) {
            if(parent[d][i] != parent[d][j]) {
                i = parent[d][i];
                j = parent[d][j];
            }
        }
        return parent[0][i];
    }
    private int dfs(TreeNode root) {
        if(null == root) {
            return 0;
        }
        map.put(root.val, root);
        if(root.left != null)
            parent[0][root.left.val] = root.val;
        if(root.right != null)
            parent[0][root.right.val] = root.val;

        return depth[root.val] = Math.max(dfs(root.left), dfs(root.right)) + 1;


    }
    public static void main(String[] args) {
        Integer arr[] = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeNode.buildTree(arr);
        LCABinaryLifting bl = new LCABinaryLifting();
        System.out.println(bl.lowestCommonAncestor(root, 5, 1));
    }
}
