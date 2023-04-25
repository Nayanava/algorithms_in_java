package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BinaryLiftingInBT {
    int D, N;
    Map<TreeNode, TreeNode[]> parent;
    Map<TreeNode, Integer> depth;

    int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    private void populateDepthAndParent(TreeNode root) {
        N = countNodes(root);
        D = Integer.numberOfTrailingZeros(Integer.highestOneBit(N));
        depth = new HashMap<>();
        parent = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        depth.put(root, 0);
        parent.putIfAbsent(root, new TreeNode[D+1]);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                depth.put(node.left, depth.get(node) + 1);
                parent.putIfAbsent(node.left, new TreeNode[D + 1]);
                parent.get(node.left)[0] = node;
                q.offer(node.left);
            }
            if (node.right != null) {
                depth.put(node.right, depth.get(node) + 1);
                parent.putIfAbsent(node.right, new TreeNode[D + 1]);
                parent.get(node.right)[0] = node;
                q.offer(node.right);
            }
        }
    }

    private void populateAllParents() {
        for (int d = 1; d <= D; d++) {
            for (TreeNode node : parent.keySet()) {
                TreeNode mid = parent.get(node)[d - 1];
                if (mid != null) {
                    parent.get(node)[d] = parent.get(mid)[d - 1];
                }
            }
        }
    }

    private TreeNode walk(TreeNode node, int k) {
        for (int d = 0; d <= D && node != null; d++) {
            if (((1 << d) & k) > 0){
                node = parent.get(node)[d];
            }
        }
        return node;
    }

    private TreeNode findLCA(TreeNode p, TreeNode q) {
        if (depth.get(p) > depth.get(q)) {
            p = walk(p, depth.get(p) - depth.get(q));
        }
        if (depth.get(q) > depth.get(p)) {
            q = walk(q, depth.get(q) - depth.get(p));
        }
        if (p == q) {
            return p;
        }
        for (int d = D; d >= 0; d--) {
            if (parent.get(p)[d] != parent.get(q)[d]) {
                p = parent.get(p)[d];
                q = parent.get(q)[d];
            }
        }
        return parent.get(p)[0];
    }

    private TreeNode findKthAncestor(TreeNode p, int k) {
        return walk(p, k);
    }

    private int dist(TreeNode p, TreeNode q) {
        return depth.get(p) + depth.get(q) - depth.get(findLCA(p, q));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left, q = root.right;
        BinaryLiftingInBT blbt = new BinaryLiftingInBT();
        blbt.populateDepthAndParent(root);
        blbt.populateAllParents();
        System.out.println(blbt.findLCA(p, q));
    }
}
