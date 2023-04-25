package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class CheckNodeExistence {
    public boolean findNode(TreeNode root, int val) {
        StringBuilder sb = new StringBuilder();
        while (val > 1) {
            int dir = val % 2;
            sb.append(dir);
            val /= 2;
        }
        sb.reverse();
        return findNode(root, sb, 0);
    }

    private boolean findNode(TreeNode root, StringBuilder sb, int index) {
        if (null == root) {
            return false;
        }
        if (index == sb.length()) {
            return true;
        }
        if (sb.charAt(index) == '0') {
            return findNode(root.left, sb, index + 1);
        }
		return findNode(root.right, sb, index + 1);
    }

    public static void main(String[] args) {
    	Integer[] arr = new Integer[] {1, 2, 3, 4, null, 6, null};
    	TreeNode root = TreeNode.buildTree(arr);
    	CheckNodeExistence cne = new CheckNodeExistence();
    	System.out.println(cne.findNode(root, 6));
	}
}
