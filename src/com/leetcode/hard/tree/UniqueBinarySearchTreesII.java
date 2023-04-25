package com.leetcode.hard.tree;

import com.leetcode.hard.utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueBinarySearchTreesII {
    int N;
    public List<TreeNode> generateTrees(int n) {
        this.N = n;
        List<TreeNode> res = new ArrayList<>();
        return generateTree(1, n);
    }
    private List<TreeNode> generateTree(int low, int high) {
        if(low > high) {
            return Collections.EMPTY_LIST;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        if(low == high) {
            treeNodes.add(new TreeNode(low));
            return treeNodes;
        }
        for(int i = low; i <= high; i++) {
            List<TreeNode> leftST = generateTree(low, i-1);
            List<TreeNode> rightST = generateTree(i+1, high);


            if(leftST.isEmpty()) {
                for(TreeNode right : rightST) {
                    TreeNode node = new TreeNode(i);
                    node.right = right;
                    treeNodes.add(node);
                }
            }
            if(rightST.isEmpty()) {
                for(TreeNode left : leftST) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    treeNodes.add(node);
                }
            } else {
                for(TreeNode left : leftST) {
                    for(TreeNode right : rightST) {
                        TreeNode node = new TreeNode(i);
                        node.left = left;
                        node.right = right;
                        treeNodes.add(node);
                    }
                }
            }
        }
        return treeNodes;
    }

    public static void main(String[] args) {
        int n = 3;
        UniqueBinarySearchTreesII ubst = new UniqueBinarySearchTreesII();
        ubst.generateTrees(n);
    }
}
