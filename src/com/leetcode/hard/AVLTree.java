package com.leetcode.hard;

import com.leetcode.hard.utils.TreeNode;

public class AVLTree {

    //Util methods.
    //1. height of a node
    int height(TreeNode root) {
        if( root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null) {
            return root.right.height + 1;
        }

        if (root.right == null) {
            return root.left.height + 1;
        }

        return Math.max(root.left.height, root.right.height) + 1;
    }

    //2. balance of a node
    int getBalance(TreeNode node) {
        return height(node.right) - height(node.left);
    }

    //left rotation

    TreeNode leftRotation(TreeNode root) {
        TreeNode temp = root.right;
        root.right = temp.left;
        temp.left = root;

        root.height = height(root);
        temp.height = height(temp);

        return temp;
    }

    TreeNode rightRotation(TreeNode root) {
        TreeNode temp = root.left;
        root.left = temp.right;
        temp.right = root;

        root.height = height(root);
        temp.height = height(temp);

        return temp;
    }

    public TreeNode recursiveInsert(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.val) {
            root.left = recursiveInsert(root.left, data);
        } else {
            root.right = recursiveInsert(root.right, data);
        }

        root.height = height(root);

        int balance = getBalance(root);

        if (balance > 1 && data > root.right.val) {
            root = leftRotation(root);
        } else if (balance < -1 && data < root.left.val) {
            root = rightRotation(root);
        } else if (balance > 1 && data < root.right.val) {
            root.right = rightRotation(root.right);
            root = leftRotation(root);
        } else if (balance < -1 && data > root.left.val) {
            root.left = leftRotation(root.left);
            root = rightRotation(root);
        }

        return root;
    }


    void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        TreeNode root = null;
        root = tree.recursiveInsert(root, 12);
        root = tree.recursiveInsert(root, 10);
        root = tree.recursiveInsert(root, 18);
        root = tree.recursiveInsert(root, 16);
        root = tree.recursiveInsert(root, 19);
        root = tree.recursiveInsert(root, 20);
        tree.preOrder(root);
    }
}