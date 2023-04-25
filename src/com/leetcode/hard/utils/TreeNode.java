package com.leetcode.hard.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int height;

    public TreeNode(int x) {
        this.left = this.right = null;
        val = x;
        this.height = 1;
    }

    public static TreeNode buildTree(Integer[] arr) {
        Queue<TreeNode> q = new LinkedList<>();
        int index = 0;
        TreeNode root = new TreeNode(arr[index]);
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(index < arr.length - 1 && arr[++index] != null) {
                TreeNode node = new TreeNode(arr[index]);
                curr.left = node;
                q.offer(node);
            } if(index < arr.length - 1 && arr[++index] != null) {
                TreeNode node = new TreeNode(arr[index]);
                curr.right = node;
                q.offer(node);
            }
        }
        return root;
    }

    public static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}