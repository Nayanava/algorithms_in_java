package com.leetcode.hard;

import com.leetcode.hard.utils.TreeNode;

import java.util.Stack;

public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        TreeNode node = stack.pop();
        root = node;
        while(!stack.isEmpty()) {
            node.left = stack.peek().right;
            node.right = stack.pop();
            node = node.right;
        }
        node.left = node.right = null;
        return root;
    }

    public TreeNode upsideDownBinaryTreeInPlace(TreeNode root) {

        TreeNode right, left, curr, prev, temp;
        right = left = prev = temp = null;
        curr = root;

        while(curr != null) {
            right = curr.right;
            curr.right = prev;
            left = curr.left;
            curr.left = temp;
            temp = right;
            prev = curr;
            curr = left;
        }

        return prev;
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);

        BinaryTreeUpsideDown binaryTreeUpsideDown = new BinaryTreeUpsideDown();
        //node = binaryTreeUpsideDown.upsideDownBinaryTree(node);
        node = binaryTreeUpsideDown.upsideDownBinaryTreeInPlace(node);

        binaryTreeUpsideDown.inorder(node);
    }

    public void inorder(TreeNode root) {

        if( root == null) {
            return;
        }

        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
}
