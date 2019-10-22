package com.leetcode.hard.utils;

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
}