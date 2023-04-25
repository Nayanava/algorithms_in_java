package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class MaxTimeToBurnBush {
    public int findMaxTimeToBurnBush(TreeNode root, TreeNode desired) {
        postOrder(root, desired);
        return max;
    }

    int max = 0;

    private Info postOrder(TreeNode root, TreeNode desired) {
        if (null == root) {
            return new Info(false, 0);
        }
        if (isLeaf(root) && root == desired) {
            return new Info(true, 1);
        }
        Info left = postOrder(root.left, desired);
        Info right = postOrder(root.right, desired);

        if (left.found || right.found) {
            max = Math.max(left.count + right.count + 1, max);
        }

        return new Info(left.found || right.found, Math.max(left.count, right.count) + 1);
    }

    boolean isLeaf(TreeNode root) {
    	return root.left == null && root.right == null;
	}
    class Info {
    	boolean found;
    	int count;
    	Info(boolean found, int count) {
    		this.found = found;
    		this.count = count;
		}
	}
}
