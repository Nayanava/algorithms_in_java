package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

public class EqualSubtreeSum {
    int num = (int)1e9+7;
    public boolean checkEqualTree(TreeNode root) {
        int totalSum = postorderDfs(root, num).total;
        return postorderDfs(root, totalSum).found;
    }


    private Info postorderDfs(TreeNode root, int totalSum) {
        if(null == root) {
            return new Info(false, num);
        }
        Info left = postorderDfs(root.left, totalSum);
        Info right = postorderDfs(root.right, totalSum);

        int sum = left.total == num ? 0 : 1;
        sum += right.total == num ? 0 : 1;
        if(totalSum == Integer.MIN_VALUE) {
            return new Info(false, left.total + right.total + root.val);
        }
        if(left.found || right.found) {
            return new Info(true, 0);
        }
        Info info = new Info();
        if(left.total + right.total + root.val == totalSum/2)
            info.found = true;
        info.total = sum + root.val;
        return info;
    }

    class Info {
        boolean found;
        int total;
        Info(){}
        Info(boolean found, int total) {
            this.found = found;
            this.total = total;
        }
    }

    public static void main(String args[]) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(10);
        node.right = new TreeNode(10);
        node.right.left = new TreeNode(2);
        node.right.right = new TreeNode(3);
        EqualSubtreeSum ess = new EqualSubtreeSum();
        System.out.println(ess.checkEqualTree(node));
    }
}
//[5,10,10,null,null,2,3]