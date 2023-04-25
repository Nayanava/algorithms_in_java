package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class LongestZigZagTree {
    int max;
    public int longestZigZag(TreeNode root) {
        if(null == root) {
            return 0;
        }
        longestZigZag(root.left, 0, true);
        longestZigZag(root.right, 0, false);
        return max-1;

    }

    private void longestZigZag(TreeNode root, int count, boolean isLeft) {
        if(null == root) {
            max = Math.max(max, count);
            return;
        }
        if(isLeft) {
             longestZigZag(root.right, count+1, false);
             longestZigZag(root.left, 1, true);
        } else {
            longestZigZag(root.left, count+1, true);
            longestZigZag(root.right, 1, false);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1};
        TreeNode node = TreeNode.buildTree(arr);
        LongestZigZagTree lzzt = new LongestZigZagTree();
        System.out.println(lzzt.longestZigZag(node));
    }

}
