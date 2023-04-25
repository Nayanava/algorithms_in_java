package com.leetcode.medium;
/**
 * @author nayanava
 */

import com.leetcode.hard.utils.TreeNode;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<TreeNode> qNode = new LinkedList<>();
        Deque<Integer> q = new LinkedList<>();
        qNode.addFirst(root);
        q.add(root.val);
        int level = 0;
        while(!qNode.isEmpty()) {
            res.add(new LinkedList<>(q));
            q.clear();
            int size = qNode.size();
            while(--size >= 0) {
                if(level % 2 == 0) {
                    TreeNode popped = qNode.pollFirst();
                    if(popped.right != null) {
                        qNode.addLast(popped.right);
                        q.addLast(popped.right.val);
                    }
                    if(popped.left != null) {
                        qNode.addLast(popped.left);
                        q.addLast(popped.left.val);
                    }
                } else {
                    TreeNode popped = qNode.pollLast();
                    if(popped.left != null) {
                        qNode.addFirst(popped.left);
                        q.addLast(popped.left.val);
                    }
                    if(popped.right != null) {
                        qNode.addFirst(popped.right);
                        q.addLast(popped.right.val);
                    }
                }
            }
            level += 1;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{3,9,20,null,null,15,7});
        zigzagLevelOrder(root);
    }
}
