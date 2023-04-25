package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class MaximumSumBST {
    int max = 0;
    public int maxSumBST(TreeNode root) {
        maxBST(root);
        return max;
    }

    private Info maxBST(TreeNode root) {
        if(null == root) {
            return null;
        }
        Info left = maxBST(root.left);
        Info right = maxBST(root.right);

        if(left == null && right == null) {
            this.max = Math.max(max, root.val);
            return new Info(root.val, root.val, root.val, true);
        }
        if(left != null && right != null && left.isBST && right.isBST) {
            int min = left.max;
            int max = right.min;
            if(left.isBST && right.isBST && root.val > min && root.val < max) {
                int total = left.sum + right.sum + root.val;
                this.max = Math.max(max, total);
                return new Info(left.max, right.min, total, true);
            }
        }
        if(left == null && right.isBST) {
            if(root.val < right.min) {
                this.max = Math.max(max, root.val + right.sum);
                return new Info(root.val, right.max, root.val + right.sum, true);
            }
        }
        if(right == null && left.isBST) {
            if(root.val > left.max) {
                this.max = Math.max(max, root.val + left.sum);
                return new Info(left.max, root.val, root.val + left.sum, true);
            }
        }
        return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, false);

    }
    class Info {
        int min, max;
        boolean isBST;
        int sum;
        Info(int min, int max, int sum) {
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
        Info(int min, int max, int sum, boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.isBST = isBST;
        }
    }
    public static void main(String[] args) {
        Integer[] arr = {4,8,null,6,1,9,null,-5,4,null,null,null,-3,null,10};
        TreeNode root = TreeNode.buildTree(arr);
        MaximumSumBST mss = new MaximumSumBST();
        System.out.println(mss.maxSumBST(root));
    }
}
