package com.leetcode.hard;

import com.leetcode.hard.utils.TreeNode;

public class RecoverTreeFromPreorderTraversal {
    private static int traversal = 0;
    private static int newDepth = 0;

    public TreeNode recoverFromPreorder(String S) {
        TreeNode node =  recoverFromPreorder(S, 0);
        traversal = 0; newDepth = 0;
        return node;
    }

    private TreeNode recoverFromPreorder(String s, int depth) {

        TreeNode node = new TreeNode(findNumber(s));

        int count = 0;
        while(traversal < s.length() && s.charAt(traversal) == '-') {
            traversal++;
            count++;
        }

        newDepth = count;
        if(newDepth <= depth) {
            return node;
        }

        node.left = recoverFromPreorder(s, depth+1);

        if(newDepth > depth) {
            node.right = recoverFromPreorder(s, depth + 1);
        }

        return node;
    }

    private int findNumber(String s) {
        int number = 0;
        while(traversal < s.length() && s.charAt(traversal) >= 48 && s.charAt(traversal) <= 57) {
            number = number * 10 + (s.charAt(traversal) - 48);
            traversal++;
        }
        return number;
    }

}
