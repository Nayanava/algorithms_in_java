package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTree {
    public List<TreeNode> allPossibleFBT(int N) {
        return findAllFBT(N);

    }

    public List<TreeNode> findAllFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if(n < 3) {
            return null;
        }
        if(n == 3) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            res.add(root);
            return res;
        }
        List<TreeNode> children = findAllFBT(n-2);
        if(children != null) {
            for(TreeNode node : children) {
                TreeNode root = new TreeNode(0);
                root.left = new TreeNode(0);
                root.right = node;
                res.add(root);
            }
            for(TreeNode node : children) {
                TreeNode root = new TreeNode(0);
                root.right = new TreeNode(0);
                root.left = node;
                res.add(root);
            }
        }
        List<TreeNode> leftChildren = findAllFBT((n-1)/2);
        List<TreeNode> rightChildren = findAllFBT((n-1)/2);
        if(leftChildren != null && rightChildren != null) {
            for(TreeNode left : leftChildren) {
                for(TreeNode right : rightChildren) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 15;
        AllPossibleFullBinaryTree apfbt = new AllPossibleFullBinaryTree();
        List<TreeNode> list = apfbt.allPossibleFBT(n);
    }
}
