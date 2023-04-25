package com.leetcode.hard.dynamic.programming;

import com.leetcode.hard.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BinaryTreeCameras {
    Map<String, Integer> map;
    public int minCameraCover(TreeNode root) {
        map = new HashMap<>();
        if(null == root) {
            return 0;
        }
        if(isLeaf(root)) {
            return 1;
        }
        return Math.min(minVertexCover(root, 0), minVertexCover(root, 1));
    }
    
    int minVertexCover(TreeNode root, int guard) {
        if(null == root) {
            return 0;
        }
        if(isLeaf(root)) {
            return guard;
        }
        String key = root.toString() + "_" + guard;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int sum = 0;
        if(guard == 0) {
            sum += minVertexCover(root.left, 1) + minVertexCover(root.right, 1);
        } else {
            sum += Math.min(minVertexCover(root.left, 1), minVertexCover(root.left, 0)) +
                        Math.min(minVertexCover(root.right, 1), minVertexCover(root.right, 0));
        }
        map.put(key, sum + guard);
        return sum + guard;
    }
    
    boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.left.right.left = new TreeNode(0);
        root.left.right.left.right = new TreeNode(0);
        root.left.right.left.right.left = new TreeNode(0);

        BinaryTreeCameras BTC = new BinaryTreeCameras();
        System.out.print(BTC.minCameraCover(root));
    }
}