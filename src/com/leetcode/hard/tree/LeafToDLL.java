package com.leetcode.hard.tree;
/**
 * @author nayanava
 */

import com.leetcode.hard.utils.TreeNode;

import java.io.*;

public class LeafToDLL {

    private static TreeNode dllHead;
    private static void leafToDLL(TreeNode root) {
        if( null == root) {
            return;
        }

        if(TreeNode.isLeaf(root)) {
            root.right = dllHead;
            if(null != dllHead)
                dllHead.left = root;
            dllHead = root;
            return;
        }
        leafToDLL(root.right);
        leafToDLL(root.left);
        if(TreeNode.isLeaf(root.right)) {
            root.right = null;
        }
        if(TreeNode.isLeaf(root.left)) {
            root.left = null;
        }
    }

    private static void printDLL() {
        TreeNode temp = dllHead;
        while(temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.right;
        }
    }
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, null, 6, 7, 8, null, null, 9, 10};
        TreeNode root = TreeNode.buildTree(arr);
        leafToDLL(root);
        printDLL();
    }
}
